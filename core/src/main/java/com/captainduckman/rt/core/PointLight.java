package com.captainduckman.rt.core;

import com.captainduckman.math.Point;

public class PointLight extends LightSource {
    public PointLight(final Colour colour, final Point position) {
        super(colour, position);
    }
}
