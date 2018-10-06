package com.captainduckman.samples.tracer1;

import com.captainduckman.math.Point;
import com.captainduckman.math.RotationMatrixZ;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.ShearingMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Canvas;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.LightSource;
import com.captainduckman.rt.core.PointLight;
import com.captainduckman.rt.core.PpmWriter;
import com.captainduckman.rt.core.Ray;
import com.captainduckman.rt.core.phong.Material;
import com.captainduckman.rt.core.shapes.Sphere;

import static com.captainduckman.rt.core.IntersectionHelper.hit;

public class Tracer1 {
    private static final int CANVAS_SIZE = 100;
    private Canvas canvas;
    private Point lightSource;
    private Sphere sphere;
    private double wallZ;
    private double wallSize;
    private double pixelSize;
    private Colour colour;
    private LightSource light;

    Tracer1() {
        canvas = new Canvas(CANVAS_SIZE, CANVAS_SIZE);
        sphere = new Sphere(new Point(0, 0, 0));
        ScalingMatrix scalingMatrix = new ScalingMatrix(1, .5, 1);
        RotationMatrixZ rotationMatrix = new RotationMatrixZ(Math.PI / 4);
        ShearingMatrix shearingMatrix = new ShearingMatrix(1, 0, 0, 0, 0, 0);
//        sphere.transform(shearingMatrix).transform(rotationMatrix).transform(scalingMatrix);
        lightSource = new Point(0, 0, -5);
        light = new PointLight(new Colour(1, 1, 1), new Point(-10, 10, -10));
        Material material = new Material(new Colour(1, 0.2, 1), 0.1, 0.9, 0.9, 200);
        sphere.setMaterial(material);
        // some values to define the projection of the sphere on the canvas/canvas position in world coordinates
        wallZ = 10;
        wallSize = 7;
        pixelSize = wallSize / 100;

        colour = new Colour(1, 0, 0);
    }

    public static void main(String[] args) {
        Tracer1 tracer = new Tracer1();
        tracer.project();
    }

    private void project() {
        for (int y = 0; y < CANVAS_SIZE - 1; y++) {
            double worldY = wallSize / 2 - pixelSize * y;
            for (int x = 0; x < CANVAS_SIZE - 1; x++) {
                double worldX = -wallSize / 2 + pixelSize * x;
                Point position = new Point(worldX, worldY, wallZ);
                Ray ray = new Ray(lightSource, position.subtract(lightSource).normalise());
                Intersection[] intersections = sphere.intersect(ray);
                Intersection hit = hit(intersections);
                if (hit != null) {
                    Point point = ray.position(hit.gettValue());
                    Vector normal = hit.getShape().normal(point);
                    Vector camera = ray.getDirection().negate();
                    Colour plotColour = hit.getShape().getMaterial().lighting(light, position, camera, normal, false);
                    canvas.setColourAt(x, y, plotColour);
                }
            }
        }
        PpmWriter ppmWriter = new PpmWriter(256);
        ppmWriter.writePpmFile("d:/tmp/test5.ppm", canvas);
    }
}
