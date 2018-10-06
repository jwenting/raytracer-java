package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PointLightTest extends LightSourceTest {

    @BeforeEach
    void before() {
        lightSource = new PointLight(colour, position);
    }

    @Test
    void intensity() {
        assertEquals(colour, lightSource.getIntensity());
        colour = new Colour(2, 3, 3);
        lightSource.setIntensity(colour);
        assertEquals(colour, lightSource.getIntensity());
    }

    @Test
    void position() {
        assertEquals(position, lightSource.getPosition());
        position = new Point(1, 1, 1);
        lightSource.setPosition(position);
        assertEquals(position, lightSource.getPosition());
    }
}