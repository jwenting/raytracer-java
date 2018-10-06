package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.shapes.MockShape;
import com.captainduckman.rt.core.shapes.Sphere;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static junit.framework.TestCase.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;

class IntersectionTest {

    @Test
    void get() {
        Point location = new Point(0, 0, 0);
        MockShape shape = new MockShape(location);
        double t = 3.5;

        Intersection<MockShape> intersection = new Intersection<>(t, shape);

        assertEquals(t, intersection.gettValue());
        assertEquals(shape, intersection.getShape());
    }

    @Test
    void prepHit() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Intersection<Sphere> intersection = new Intersection<>(4, sphere);
        intersection.prepareHit(ray);
        assertEquals(new Point(0, 0, -1), intersection.getPoint());
        assertEquals(new Vector(0, 0, -1), intersection.getCamera());
        assertEquals(new Vector(0, 0, -1), intersection.getNormal());
    }

    @Test
    @DisplayName("Intersection on the outside")
    void prepHit2() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Intersection<Sphere> intersection = new Intersection<>(4, sphere);
        intersection.prepareHit(ray);
        assertFalse(intersection.isInside());
    }

    @Test
    @DisplayName("Intersection on the inside")
    void prepHit3() {
        Ray ray = new Ray(new Point(0, 0, 0), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Intersection<Sphere> intersection = new Intersection<>(1, sphere);
        intersection.prepareHit(ray);
        assertTrue(intersection.isInside());
        assertEquals(new Point(0, 0, 1), intersection.getPoint());
        assertEquals(new Vector(0, 0, -1), intersection.getCamera());
        assertEquals(new Vector(0, 0, -1), intersection.getNormal());
    }

    @Test
    @DisplayName("offset point")
    void prepHit4() {
        Ray ray = new Ray(new Point(0, 0, -5), new Vector(0, 0, 1));
        Sphere sphere = new Sphere();
        Intersection<Sphere> intersection = new Intersection<>(4, sphere);
        intersection.prepareHit(ray);
        boolean ok = intersection.getPoint().getZ() <= -1 && intersection.getPoint().getZ() > -1.1;
        assertTrue(ok);
    }

}