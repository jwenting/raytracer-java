package com.captainduckman.samples.tracer3;

import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Camera;
import com.captainduckman.rt.core.Canvas;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.PointLight;
import com.captainduckman.rt.core.PpmWriter;
import com.captainduckman.rt.core.World;
import com.captainduckman.rt.core.pattern.Solid;
import com.captainduckman.rt.core.phong.Material;
import com.captainduckman.rt.core.shapes.Plane;
import com.captainduckman.rt.core.shapes.Shape;
import com.captainduckman.rt.core.shapes.Sphere;

import static java.lang.Math.PI;

public class Tracer3 {
    public static void main(String[] args) {
        World world = new World();
        Material wallMaterial = new Material();
        wallMaterial.setPattern(new Solid(new Colour(1, 0.8, .1)));
        wallMaterial.setSpecular(0);
        Material floorMaterial = new Material();
        floorMaterial.setPattern(new Solid(new Colour(.4, 0.9, .9)));
        floorMaterial.setSpecular(0);

        Shape<Plane> floor = new Plane();
        floor.setMaterial(floorMaterial);

        Sphere middle = new Sphere();
        middle.transform(new TranslationMatrix(-.5, 1, .5));
        Material material = new Material();
        material.setPattern(new Solid(new Colour(.1, 1, .5)));
        material.setDiffuse(.7);
        material.setSpecular(.3);
        middle.setMaterial(material);

        Sphere right = new Sphere();
        right.transform(new TranslationMatrix(1.5, .5, -.5).cross(new ScalingMatrix(.5, .5, .5)));
        right.setMaterial(material);

        Sphere left = new Sphere();
        left.transform(new TranslationMatrix(-1.5, .33, -.75).cross(new ScalingMatrix(.33, .33, .33)));
        Material m2 = new Material();
        m2.setPattern(new Solid(new Colour(1, .8, .1)));
        m2.setDiffuse(.7);
        m2.setSpecular(.3);
        left.setMaterial(m2);


        world.addObject(left);
        world.addObject(middle);
        world.addObject(right);
        world.addObject(floor);

        world.addLight(new PointLight(new Colour(1, 1, 1), new Point(-10, 10, -10)));

        Camera camera = new Camera(900, 450, PI / 3);
        camera.transformView(new Point(0, 3.5, -10), new Point(0, 1, 0), new Vector(0, 1, 0));
        Canvas canvas = camera.render(world);

        PpmWriter ppmWriter = new PpmWriter(256);
        ppmWriter.writePpmFile("d:\\tmp\\test8.ppm", canvas);

    }
}
