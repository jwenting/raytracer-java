package com.captainduckman.samples.shooter;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Canvas;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.PpmWriter;

public class Shooter {

    public static Projectile tick(World w, Projectile p) {
        return new Projectile(p.getPosition().add(p.getVelocity()), p.getVelocity().add(w.getGravity()).add(w.getWind()));
    }

    public static void main(String[] args) {
        Projectile projectile = new Projectile(new Point(0, 1, 0), new Vector(1, 1.8, 0).normalise().multiply(5.25));
        World world = new World(new Vector(0, -.1, 0), new Vector(-.01, 0, 0));

        Canvas canvas = new Canvas(250, 150);

        while (projectile.getPosition().getY() > 0.0) {
            canvas.setColourAt((int) projectile.getPosition().getX(), (int) projectile.getPosition().getY(), new Colour(1, 0, 0));
            System.out.println(projectile);
            projectile = tick(world, projectile);
        }


        StringBuilder builder = new PpmWriter(255).canvasToPpm(canvas);
        System.out.println(builder);
        new PpmWriter(255).writePpmFile("d:\\tmp\\test2.ppm", canvas);

        System.out.println(projectile);
    }
}
