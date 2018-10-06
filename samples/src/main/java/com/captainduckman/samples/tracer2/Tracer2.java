package com.captainduckman.samples.tracer2;

import com.captainduckman.math.Point;
import com.captainduckman.math.RotationMatrixX;
import com.captainduckman.math.RotationMatrixY;
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
import com.captainduckman.rt.core.shapes.Shape;
import com.captainduckman.rt.core.shapes.Sphere;

import static com.captainduckman.math.MathUtils.HALF_PI;
import static java.lang.Math.PI;

public class Tracer2 {
    public static void main(String[] args) {
        World world = new World();
        Shape floor = new Sphere();
        floor.transform(new ScalingMatrix(10, .01, 10));
        floor.setMaterial(new Material());
        floor.getMaterial().setPattern(new Solid(new Colour(1, 0.9, .9)));
        floor.getMaterial().setSpecular(0);
        world.addObject(floor);
        Shape leftWall = new Sphere();
        leftWall.transform(new TranslationMatrix(0, 0, 5).cross(new RotationMatrixY(-PI / 4)).cross(new RotationMatrixX(HALF_PI)).cross(new ScalingMatrix(10, .01, 10)));
        leftWall.setMaterial(floor.getMaterial());
        world.addObject(leftWall);
        Shape rightWall = new Sphere();
        rightWall.transform(new TranslationMatrix(0, 0, 5).cross(new RotationMatrixY(PI / 4)).cross(new RotationMatrixX(HALF_PI)).cross(new ScalingMatrix(10, .01, 10)));
        rightWall.setMaterial(floor.getMaterial());
        world.addObject(rightWall);

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

        Camera camera = new Camera(900, 450, PI / 3);
        camera.transformView(new Point(0, 1.5, -5), new Point(0, 1, 0), new Vector(0, 3, 0));
        Canvas canvas = camera.render(world);

        PpmWriter ppmWriter = new PpmWriter(256);
        ppmWriter.writePpmFile("d:/tmp/test7.ppm", canvas);

    }
}
