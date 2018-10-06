package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.pattern.Solid;
import com.captainduckman.rt.core.phong.Material;
import com.captainduckman.rt.core.shapes.Plane;
import com.captainduckman.rt.core.shapes.Shape;
import com.captainduckman.rt.core.shapes.Sphere;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class WorldTest {

    private World world;
    private LightSource defaultLight;
    private Sphere defaultShape;
    private Sphere defaultShape2;

    @BeforeEach
    void setUp() {
        world = new World();
        defaultLight = new PointLight(new Colour(1, 1, 1), new Point(-10, 10, -10));
        defaultShape = new Sphere(new Point(0, 0, 0));
        defaultShape.setMaterial(new Material(new Colour(.8, 1, .6), .1, .7, .2, 200));
        defaultShape2 = new Sphere(new Point(0, 0, 0));
        defaultShape2.transform(new ScalingMatrix(.5, .5, .5));
        world.addLight(defaultLight);
        world.addObject(defaultShape);
        world.addObject(defaultShape2);
    }

    @Test
    @DisplayName("Getting intersections")
    void testSomething() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        List<Intersection> intersections = world.intersect(ray);
        assertEquals(4, intersections.size());
        assertEquals(4, intersections.get(0).gettValue());
        assertEquals(4.5, intersections.get(1).gettValue());
        assertEquals(5.5, intersections.get(2).gettValue());
        assertEquals(6, intersections.get(3).gettValue());
    }


    @Test
    @DisplayName("Shading an intersection")
    void testHitShading() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Shape shape = world.getObject(0);
        Intersection intersection = new Intersection<>(4, shape);
        Colour c = world.shade(intersection, ray);
        assertEquals(new Colour(0.38066, 0.47583, 0.2855), c);
    }


    @Test
    @DisplayName("Shading an intersection from the inside")
    void testHitShading2() {
        defaultLight = new PointLight(new Colour(1, 1, 1), new Point(0, 0.25, 0));
        world = new World();
        world.addObject(defaultShape);
        world.addObject(defaultShape2);
        world.addLight(defaultLight);
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Intersection<Sphere> intersection = new Intersection<>(0.5, defaultShape2);
        Colour c = world.shade(intersection, ray);
        assertEquals(new Colour(0.1, 0.1, 0.1), c);
    }

    @Test
    @DisplayName("Ray missed")
    void rayMissed() {
        Ray ray = new Ray(new Point(0, 0, 5), new Vector(0, 1, 0));
        Colour colour = world.colourAt(ray);
        assertEquals(new Colour(0, 0, 0), colour);
    }

    @Test
    @DisplayName("Ray hit")
    void rayHit() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Colour colour = world.colourAt(ray);
        assertEquals(new Colour(0.38066, 0.47583, 0.2855), colour);
    }

    @Test
    @DisplayName("no shadow for collinear point and light")
    void noShadow1() {
        Point p = new Point(0, 10, 0);
        boolean inShadow = world.isInShadow(p);
        assertFalse(inShadow);
    }

    @Test
    @DisplayName("in shadow when light opposite object")
    void shadow1() {
        Point p = new Point(10, -10, 10);
        boolean inShadow = world.isInShadow(p);
        assertTrue(inShadow);
    }

    @Test
    @DisplayName("no shadow when light between point and object")
    void noShadow2() {
        Point p = new Point(-20, 20, -20);
        boolean inShadow = world.isInShadow(p);
        assertFalse(inShadow);
    }


    @Test
    @DisplayName("no shadow when light between point and object")
    void noShadow3() {
        Point p = new Point(-2, 2, -2);
        boolean inShadow = world.isInShadow(p);
        assertFalse(inShadow);
    }

    @Test
    @DisplayName("intersections")
    void testIntersect() {
        world = new World();
        Material wallMaterial = new Material();
        wallMaterial.setPattern(new Solid(new Colour(1, 0.8, .1)));
        wallMaterial.setSpecular(0);
        Material floorMaterial = new Material();
        floorMaterial.setPattern(new Solid(new Colour(.4, 0.9, .9)));
        floorMaterial.setSpecular(0);
        Shape floor = new Plane();
        floor.setMaterial(floorMaterial);
        world.addObject(floor);

        Sphere middle = new Sphere();
        middle.transform(new TranslationMatrix(-.5, 1, .5));
        Material material = new Material();
        material.setPattern(new Solid(new Colour(.1, 1, .5)));
        material.setDiffuse(.7);
        material.setSpecular(.3);
        middle.setMaterial(material);
        world.addObject(middle);

        Sphere right = new Sphere();
        right.transform(new TranslationMatrix(1.5, .5, -.5).cross(new ScalingMatrix(.5, .5, .5)));
        right.setMaterial(material);
        world.addObject(right);

        Sphere left = new Sphere();
        left.transform(new TranslationMatrix(-1.5, .33, -.75).cross(new ScalingMatrix(.33, .33, .33)));
        Material m2 = new Material();
        m2.setPattern(new Solid(new Colour(1, .8, .1)));
        m2.setDiffuse(.7);
        m2.setSpecular(.3);
        left.setMaterial(m2);
        world.addObject(left);

        world.addLight(new PointLight(new Colour(1, 1, 1), new Point(-10, 10, -10)));

        Ray ray = new Ray(new Point(0, 1, 0), new Vector(1, 1, 1));
        List<Intersection> intersections = world.intersect(ray);
        System.out.println(intersections);

    }
}