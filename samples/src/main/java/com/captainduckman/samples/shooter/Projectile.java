package com.captainduckman.samples.shooter;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;

public class Projectile {
    private Point position;
    private Vector velocity;

    public Projectile(final Point position, final Vector velocity) {
        this.position = position;
        this.velocity = velocity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(final Point position) {
        this.position = position;
    }

    public Vector getVelocity() {
        return velocity;
    }

    public void setVelocity(final Vector velocity) {
        this.velocity = velocity;
    }

    @Override
    public String toString() {
        return "Projectile{" +
                "position=" + position +
                ", velocity=" + velocity +
                '}';
    }
}
