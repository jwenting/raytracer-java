package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.shapes.Shape;

import java.util.Comparator;

import static com.captainduckman.math.MathUtils.PRECISION;

/**
 * Maps intersections to the objects they intersect.
 *
 * @param <E> shape type
 */
public class Intersection<E extends Shape> {
    private double tValue;

    private E shape;
    private Point point;
    private Vector camera;
    private Vector normal;
    private boolean inside;

    public Intersection(final double tValue, final E shape) {
        this.tValue = tValue;
        this.shape = shape;
    }

    public double gettValue() {
        return tValue;
    }

    public E getShape() {
        return shape;
    }

    public void prepareHit(final Ray ray) {
        point = ray.position(tValue);
        camera = new Vector(ray.getDirection()).negate();
        normal = shape.normal(point);
        point = point.add(new Vector(this.normal).multiply(PRECISION));
        if (normal.dot(camera) < 0) {
            inside = true;
            normal = normal.negate();
        } else {
            inside = false;
        }
    }

    @Override
    public String toString() {
        return "Intersection{" +
                "tValue=" + tValue +
                ", shape=" + shape +
                '}';
    }

    public Point getPoint() {
        return point;
    }

    public Vector getCamera() {
        return camera;
    }

    public Vector getNormal() {
        return normal;
    }

    public boolean isInside() {
        return inside;
    }

    public static class IntersectionComparator implements Comparator<Intersection> {
        @Override
        public int compare(final Intersection o1, final Intersection o2) {
            int result = 0;
            if (o1.tValue > o2.tValue) {
                result = 1;
            } else {
                if (o1.tValue < o2.tValue) {
                    result = -1;
                }
            }
            return result;
        }
    }

}
