package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;

import static com.captainduckman.math.MathUtils.PRECISION;


public class Plane extends AbstractShape<Plane> {

    public Plane() {
        super(new Point(0, 0, 0));
    }

    public Plane(final Point position) {
        super(position);
    }

    @Override
    public Intersection[] intersect(final Ray ray) {
        MutationMatrix inverseMutationMatrix = new MutationMatrix(transformationMatrix).inverse();
        Ray transformedRay = new Ray(ray).transform(inverseMutationMatrix);
        if (Math.abs(transformedRay.getDirection().getY()) < PRECISION) {
            return new Intersection[0];
        }
        double t = -transformedRay.getOrigin().getY() / transformedRay.getDirection().getY();
        return new Intersection[]{new Intersection<>(t, this)};
    }

    @Override
    public Vector normal(final Point p) {
        return new Vector(0, 1, 0);
    }
}
