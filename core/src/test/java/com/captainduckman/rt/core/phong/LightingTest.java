package com.captainduckman.rt.core.phong;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.LightSource;
import com.captainduckman.rt.core.PointLight;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LightingTest {
    private Material material;
    private Point position;

    @BeforeEach
    void lighting() {
        material = new Material();
        position = new Point(0, 0, 0);
    }

    @Test
    @DisplayName("camera between light source and object")
    void scenario1() {
        Vector camera = new Vector(0, 0, -1);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 0, -10));

        Colour result = material.lighting(light, position, camera, normal, false);
        Colour expected = new Colour(1.9, 1.9, 1.9);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("in shadow")
    void shadow1() {
        Vector camera = new Vector(0, 0, -1);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 0, -10));

        Colour result = material.lighting(light, position, camera, normal, true);
        Colour expected = new Colour(.1, .1, .1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("camera between light source and object, offset 45 degrees")
    void scenario2() {
        Vector camera = new Vector(0, Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 0, -10));

        Colour result = material.lighting(light, position, camera, normal, false);
        Colour expected = new Colour(1, 1, 1);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("camera between light source and object, light offset 45 degrees")
    void scenario3() {
        Vector camera = new Vector(0, 0, -1);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 10, -10));

        Colour result = material.lighting(light, position, camera, normal, false);
        Colour expected = new Colour(.7364, .7364, .7364);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("camera between light source and object, light offset 45 degrees, camera looking at reflection")
    void scenario4() {
        Vector camera = new Vector(0, -Math.sqrt(2) / 2, -Math.sqrt(2) / 2);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 10, -10));

        Colour result = material.lighting(light, position, camera, normal, false);
        Colour expected = new Colour(1.6364, 1.6364, 1.6364);
        assertEquals(expected, result);
    }

    @Test
    @DisplayName("light behind the surface")
    void scenario5() {
        Vector camera = new Vector(0, 0, -1);
        Vector normal = new Vector(0, 0, -1);
        LightSource light = new PointLight(new Colour(1, 1, 1), new Point(0, 0, 10));

        Colour result = material.lighting(light, position, camera, normal, false);
        Colour expected = new Colour(.1, .1, .1);
        assertEquals(expected, result);
    }

}
