package com.captainduckman.rt.core;

import com.captainduckman.math.AbstractMutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.shapes.Shape;

public class Ray {
    private Point origin;
    private Vector direction;

    public Ray(final Point origin, final Vector direction) {
        this.origin = origin;
        this.direction = direction;
    }

    public Ray(final Ray ray) {
        this.origin = new Point(ray.origin);
        this.direction = new Vector(ray.direction);
    }

    public Point getOrigin() {
        return origin;
    }

    public Vector getDirection() {
        return direction;
    }

    public Point position(final double distance) {
        Vector multiply = new Vector(direction.getX(), direction.getY(), direction.getZ()).multiply(distance);
        return multiply.add(origin);
    }

    public Intersection[] intersect(final Shape shape) {
        return shape.intersect(this);
    }

    public Ray translate(final TranslationMatrix matrix) {
        origin = origin.translate(matrix);
        return this;
    }

    public Ray scale(final ScalingMatrix matrix) {
        origin = origin.scale(matrix);
        direction = direction.scale(matrix);
        return this;
    }

    public Ray transform(final AbstractMutationMatrix matrix) {
        origin = origin.transform(matrix);
        direction = direction.transform(matrix);
        return this;
    }
}
