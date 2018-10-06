package com.captainduckman.rt.core;

import com.captainduckman.math.Point;
import org.junit.jupiter.api.BeforeEach;

abstract class LightSourceTest {
    protected Colour colour;
    protected Point position;
    protected LightSource lightSource;

    @BeforeEach
    void doBeforeEach() {
        colour = new Colour(1, 1, 1);
        position = new Point(0, 0, 0);
    }

}