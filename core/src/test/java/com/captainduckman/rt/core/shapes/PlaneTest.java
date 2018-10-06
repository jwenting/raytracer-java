package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PlaneTest {

    @Test
    @DisplayName("normal of a plane is the same everywhere")
    void normal() {
        Plane plane = new Plane();
        Point point1 = new Point(0, 0, 0);
        Vector normal1 = plane.normal(point1);
        Point point2 = new Point(10, 0, -10);
        Vector normal2 = plane.normal(point2);
        Point point3 = new Point(-5, 0, 150);
        Vector normal3 = plane.normal(point3);
        Vector expected = new Vector(0, 1, 0);
        assertEquals(expected, normal1);
        assertEquals(expected, normal2);
        assertEquals(expected, normal3);
    }
}