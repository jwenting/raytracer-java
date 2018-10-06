package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import com.captainduckman.rt.core.shapes.MockShape;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class IntersectionHelperTest {

    @Test
    void hit() {
        Point point = new Point(0, 0, 0);
        MockShape shape = new MockShape(point);
        Intersection<MockShape> intersection = new Intersection<>(1, shape);
        Intersection<MockShape> intersection2 = new Intersection<>(3, shape);
        Intersection result = IntersectionHelper.hit(intersection, intersection2);
        assertEquals(intersection, result);
    }

    @Test
    void hit2() {
        Point point = new Point(0, 0, 0);
        MockShape shape = new MockShape(point);
        Intersection<MockShape> intersection = new Intersection<>(-1, shape);
        Intersection<MockShape> intersection2 = new Intersection<>(1, shape);
        Intersection result = IntersectionHelper.hit(intersection, intersection2);
        assertEquals(intersection2, result);
    }

    @Test
    void hit3() {
        Point point = new Point(0, 0, 0);
        MockShape shape = new MockShape(point);
        Intersection<MockShape> intersection = new Intersection<>(-1, shape);
        Intersection<MockShape> intersection2 = new Intersection<>(-21, shape);
        Intersection result = IntersectionHelper.hit(intersection, intersection2);
        assertNull(result);
    }

    @Test
    void hit4() {
        Point point = new Point(0, 0, 0);
        MockShape shape = new MockShape(point);
        Intersection<MockShape> intersection = new Intersection<>(5, shape);
        Intersection<MockShape> intersection2 = new Intersection<>(7, shape);
        Intersection<MockShape> intersection3 = new Intersection<>(-3, shape);
        Intersection<MockShape> intersection4 = new Intersection<>(2, shape);
        Intersection result = IntersectionHelper.hit(intersection, intersection2, intersection3, intersection4);
        assertEquals(intersection4, result);
    }

}