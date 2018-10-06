package com.captainduckman.rt.core;

import com.captainduckman.math.Matrix;
import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.RotationMatrixY;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.phong.Material;
import com.captainduckman.rt.core.shapes.Shape;
import com.captainduckman.rt.core.shapes.Sphere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.captainduckman.math.MathUtils.HALF_PI;
import static com.captainduckman.math.MathUtils.HALF_SQRT2;
import static com.captainduckman.math.MathUtils.QUARTER_PI;
import static java.lang.Math.PI;
import static org.junit.jupiter.api.Assertions.assertEquals;

class CameraTest {

    private Camera camera;

    @BeforeEach
    void before() {
        camera = new Camera(1, 1, 45);
    }

    @Test
    @DisplayName("create camera")
    void create() {
        camera = new Camera(160, 120, HALF_PI);
        assertEquals(160, camera.gethSize());
        assertEquals(120, camera.getvSize());
        assertEquals(PI / 2, camera.getFieldOfView());
        assertEquals(new MutationMatrix(), camera.getTransformation());
    }

    @Test
    @DisplayName("default orientation view transformation")
    void defaultViewTransformation() {
        Point from = new Point(0, 0, 0);
        Point to = new Point(0, 0, -1);
        Vector up = new Vector(0, 1, 0);
        MutationMatrix matrix = camera.transformView(from, to, up);
        assertEquals(Matrix.getIdentityMatrix(4), matrix.getMatrix());
    }

    @Test
    @DisplayName("looking positive Z view transformation")
    void positiveZViewTransformation() {
        Point from = new Point(0, 0, 0);
        Point to = new Point(0, 0, 1);
        Vector up = new Vector(0, 1, 0);
        MutationMatrix matrix = camera.transformView(from, to, up);
        assertEquals(new ScalingMatrix(-1, 1, -1), matrix);
    }

    @Test
    @DisplayName("translating view transformation")
    void translatingViewTransformation() {
        Point from = new Point(0, 0, 8);
        Point to = new Point(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        MutationMatrix matrix = camera.transformView(from, to, up);
        assertEquals(new TranslationMatrix(0, 0, -8), matrix);
    }

    @Test
    @DisplayName("arbitrary view transformation")
    void arbitraryViewTransformation() {
        Point from = new Point(1, 3, 2);
        Point to = new Point(4, -2, 8);
        Vector up = new Vector(1, 1, 0);
        MutationMatrix matrix = camera.transformView(from, to, up);
        Matrix m = new Matrix(4);
        m.set(0, 0, -.50709);
        m.set(0, 1, .50709);
        m.set(0, 2, .67612);
        m.set(0, 3, -2.36643);
        m.set(1, 0, .76772);
        m.set(1, 1, .60609);
        m.set(1, 2, .12122);
        m.set(1, 3, -2.82843);
        m.set(2, 0, -.35857);
        m.set(2, 1, .59761);
        m.set(2, 2, -.71714);
        m.set(2, 3, 0);
        m.set(3, 0, 0);
        m.set(3, 1, 0);
        m.set(3, 2, 0);
        m.set(3, 3, 1);
        assertEquals(m, matrix.getMatrix());
    }

    @Test
    @DisplayName("Ray through camera center")
    void testRayThroughCameraCenter() {
        camera = new Camera(201, 101, HALF_PI);
        Ray ray = camera.getRay(100, 50);
        assertEquals(new Point(0, 0, 0), ray.getOrigin());
        assertEquals(new Vector(0, 0, -1), ray.getDirection());
    }

    @Test
    @DisplayName("Ray through canvas corner")
    void testRayThroughCanvasCorner() {
        camera = new Camera(201, 101, HALF_PI);
        Ray ray = camera.getRay(0, 0);
        assertEquals(new Point(0, 0, 0), ray.getOrigin());
        assertEquals(new Vector(0.66519, 0.33259, -0.66851), ray.getDirection());
    }

    @Test
    @DisplayName("Ray with camera transformation")
    void testRayWithCameraTransformation() {
        camera = new Camera(201, 101, HALF_PI);
        RotationMatrixY rotationMatrixY = new RotationMatrixY(QUARTER_PI);
        TranslationMatrix translationMatrix = new TranslationMatrix(0, -2, 5);
        MutationMatrix mutationMatrix = new MutationMatrix(rotationMatrixY.cross(translationMatrix));
        camera.transform(mutationMatrix);
        Ray ray = camera.getRay(100, 50);
        assertEquals(new Point(0, 2, -5), ray.getOrigin());
        assertEquals(new Vector(HALF_SQRT2, 0, -HALF_SQRT2), ray.getDirection());
    }

    @Test
    @DisplayName("full render demonstration")
    void renderTest() {
        World world = new World();
        LightSource defaultLight = new PointLight(new Colour(1, 1, 1), new Point(-10, 10, -10));
        Shape defaultShape = new Sphere();
        defaultShape.setMaterial(new Material(new Colour(.8, 1, .6), .1, .7, .2, 200));
        world.addLight(defaultLight);
        world.addObject(defaultShape);

        Camera camera = new Camera(11, 11, HALF_PI);
        Point from = new Point(0, 0, -5);
        Point to = new Point(0, 0, 0);
        Vector up = new Vector(0, 1, 0);
        camera.transformView(from, to, up);
        Canvas canvas = camera.render(world);
        Colour result = canvas.getColourAt(5, 5);
        assertEquals(new Colour(.38066, .47583, .2855), result);
    }

}