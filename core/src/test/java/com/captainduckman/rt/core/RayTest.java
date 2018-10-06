package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.shapes.MockShape;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class RayTest {

    private Ray ray;
    private Point point;
    private Vector direction;

    @BeforeEach
    void before() {
    }

    @Test
    void getOrigin() {
        point = new Point(1, 2, 3);
        direction = new Vector(4, 5, 6);
        ray = new Ray(point, direction);

        assertEquals(point, ray.getOrigin());
    }

    @Test
    void getDirection() {
        point = new Point(1, 2, 3);
        direction = new Vector(4, 5, 6);
        ray = new Ray(point, direction);

        assertEquals(direction, ray.getDirection());
    }

    @Test
    void position() {
        point = new Point(2, 3, 4);
        direction = new Vector(1, 0, 0);
        ray = new Ray(point, direction);

        Point expected = new Point(2, 3, 4);
        Point result = ray.position(0);
        assertEquals(expected, result);

        expected = new Point(3, 3, 4);
        result = ray.position(1);
        assertEquals(expected, result);

        expected = new Point(1, 3, 4);
        result = ray.position(-1);
        assertEquals(expected, result);

        expected = new Point(4.5, 3, 4);
        result = ray.position(2.5);
        assertEquals(expected, result);
    }

    @Test
    void intersect() {
        point = new Point(2, 3, 4);
        direction = new Vector(1, 0, 0);
        ray = new Ray(point, direction);
        Point point = new Point(1, 2, 3);
        MockShape shape = new MockShape(point);
        Intersection[] result = ray.intersect(shape);
        assertEquals(42, result[0].gettValue());
    }

    @Test
    void translate() {
        point = new Point(1, 2, 3);
        direction = new Vector(0, 1, 0);
        ray = new Ray(point, direction);
        TranslationMatrix translationMatrix = new TranslationMatrix(3, 4, 5);
        Ray result = ray.translate(translationMatrix);
        assertNotNull(result);
        Point expectedPoint = new Point(4, 6, 8);
        assertEquals(expectedPoint, result.getOrigin());
        assertEquals(direction, result.getDirection());
    }

    @Test
    void scale() {
        point = new Point(1, 2, 3);
        direction = new Vector(0, 1, 0);
        ray = new Ray(point, direction);
        ScalingMatrix scalingMatrix = new ScalingMatrix(2, 3, 4);
        Ray result = ray.scale(scalingMatrix);
        assertNotNull(result);
        Point expectedPoint = new Point(2, 6, 12);
        Vector expectedVector = new Vector(0, 3, 0);
        assertEquals(expectedPoint, result.getOrigin());
        assertEquals(expectedVector, result.getDirection());
    }

    @Test
    void translate2() {
        point = new Point(1, 2, 3);
        TranslationMatrix translationMatrix = new TranslationMatrix(3, 4, 5);
        Point result = point.translate(translationMatrix);
        assertNotNull(result);
        Point expectedPoint = new Point(4, 6, 8);
        assertEquals(expectedPoint, result);
    }

    @Test
    void reflect() {
        Vector v = new Vector(1, -1, 0);
        Vector n = new Vector(0, 1, 0);
        Vector r = v.reflect(n);
        Vector expected = new Vector(1, 1, 0);
        assertEquals(expected, r);
    }

    @Test
    void testSlant() {
        Vector v = new Vector(0, -1, 0);
        Vector n = new Vector(Math.sqrt(2) / 2, Math.sqrt(2) / 2, 0);
        Vector r = v.reflect(n);
        Vector expected = new Vector(1, 0, 0);
        assertEquals(expected, r);
    }

}