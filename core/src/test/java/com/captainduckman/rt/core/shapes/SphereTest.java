package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class SphereTest extends AbstractShapeTest {

    @Test
    void size() {
        Point point = new Point(1, 2, 3);
        Sphere sphere = new Sphere(point);
        assertEquals(point, sphere.position);
    }

    @Test
    void intersect() {
        Point rayStart = new Point(0, 0, -5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(4, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(6, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect2() {
        Point rayStart = new Point(0, 1, -5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(5, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(5, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect5() {
        Point rayStart = new Point(0, 0, 5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(-6, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(-4, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect2b() {
        Point rayStart = new Point(0, 0, 5);
        Vector rayDirection = new Vector(0, 0, -1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(4, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(6, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect3() {
        Point rayStart = new Point(0, 2, -5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(0, intersections.length);
    }

    @Test
    void intersect4() {
        Point rayStart = new Point(0, 0, 0);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(-1, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(1, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect6() {
        Point rayStart = new Point(0, 0, -5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));
        ScalingMatrix scalingMatrix = new ScalingMatrix(2, 2, 2);
        sphere.transform(scalingMatrix);

        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(2, intersections.length);
        assertEquals(3, intersections[0].gettValue(), 0.0001);
        assertEquals(sphere, intersections[0].getShape());
        assertEquals(7, intersections[1].gettValue(), 0.0001);
        assertEquals(sphere, intersections[1].getShape());
    }

    @Test
    void intersect7() {
        Point rayStart = new Point(0, 0, -5);
        Vector rayDirection = new Vector(0, 0, 1);
        Ray ray = new Ray(rayStart, rayDirection);
        Sphere sphere = new Sphere(new Point(0, 0, 0));
        TranslationMatrix translationMatrix = new TranslationMatrix(5, 0, 0);
        sphere.transform(translationMatrix);
        Intersection[] intersections = sphere.intersect(ray);
        assertNotNull(intersections);
        assertEquals(0, intersections.length);
    }

    @Test
    @DisplayName("normal on X axis")
    void normalXAxis() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        Point p = new Point(1, 0, 0);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(1, 0, 0);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }

    @Test
    @DisplayName("normal on Y axis")
    void normalYAxis() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        Point p = new Point(0, 1, 0);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(0, 1, 0);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }


    @Test
    @DisplayName("normal on Z axis")
    void normalZAxis() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        Point p = new Point(0, 0, 1);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(0, 0, 1);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }


    @Test
    @DisplayName("normal off axis")
    void normalOffAxis() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        Point p = new Point(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(Math.sqrt(3) / 3, Math.sqrt(3) / 3, Math.sqrt(3) / 3);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }

    @Test
    @DisplayName("translated normal")
    void translatedNormal() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        sphere.transform(new TranslationMatrix(0, 1, 0));
        Point p = new Point(0, 1.70711, -0.70711);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(0, 0.70711, -0.70711);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }

    @Test
    @DisplayName("scaled normal")
    void scaledNormal() {
        Point origin = new Point(0, 0, 0);
        Sphere sphere = new Sphere(origin);
        sphere.transform(new ScalingMatrix(1, 0.5, 1));
        Point p = new Point(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        Vector normal = sphere.normal(p);
        Vector expected = new Vector(0, 0.97014, -0.24254);
        assertEquals(expected, normal);
        Vector temp = new Vector(normal);
        Vector result = temp.normalise();
        assertEquals(normal, result);
    }

}