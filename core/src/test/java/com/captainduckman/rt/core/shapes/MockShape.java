package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;

public class MockShape extends AbstractShape<MockShape> {
    public MockShape() {
        super(new Point(0, 0, 0));
    }

    public MockShape(final Point position) {
        super(position);
    }

    @Override
    public Intersection[] intersect(final Ray ray) {
        Intersection<MockShape> intersection = new Intersection<>(42, this);
        return new Intersection[]{intersection};
    }

}
