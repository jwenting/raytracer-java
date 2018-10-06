package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;

/**
 * Represents a sphere in the world.
 */
public class Sphere extends AbstractShape<Sphere> {
    public Sphere(final Point position) {
        super(position);
    }

    public Sphere() {
        this(new Point(0, 0, 0));
    }

    public Intersection[] intersect(final Ray ray) {
        Ray copyOfRay = new Ray(ray);
        Ray transformedRay = transformationMatrix == null ? copyOfRay : copyOfRay.transform(new MutationMatrix(transformationMatrix).inverse());
        Vector sphereToRay = new Vector(transformedRay.getOrigin()).subtract(position);
        Vector rayDirection = new Vector(transformedRay.getDirection());
        double a = rayDirection.dot(rayDirection);
        double b = 2 * rayDirection.dot(sphereToRay);
        double c = sphereToRay.dot(sphereToRay) - 1;
        double discriminant = (b * b) - 4 * (a * c);
        if (discriminant < 0) {
            return new Intersection[0];
        }
        double t1 = (-b - Math.sqrt(discriminant)) / (2 * a);
        double t2 = (-b + Math.sqrt(discriminant)) / (2 * a);
        Intersection[] result = new Intersection[2];
        if (t1 > t2) {
            result[0] = new Intersection<>(t2, this);
            result[1] = new Intersection<>(t1, this);
        } else {
            result[0] = new Intersection<>(t1, this);
            result[1] = new Intersection<>(t2, this);
        }
        return result;
    }

    @Override
    public String toString() {
        return "Sphere{" +
                ", position=" + position +
                '}';
    }
}
