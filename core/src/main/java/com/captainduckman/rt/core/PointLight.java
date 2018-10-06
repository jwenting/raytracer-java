package com.captainduckman.rt.core;

import com.captainduckman.math.Point;

public class PointLight extends LightSource {
    public PointLight(final Colour intensity, final Point position) {
        super(intensity, position);
    }
}
