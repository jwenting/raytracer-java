package com.captainduckman.rt.core;

import com.captainduckman.math.Point;

public class LightSource {
    protected Colour intensity;
    protected Point position;

    public LightSource(final Colour intensity, final Point position) {
        this.intensity = intensity;
        this.position = position;
    }

    public Colour getIntensity() {
        return intensity;
    }

    public void setIntensity(final Colour intensity) {
        this.intensity = intensity;
    }

    public Point getPosition() {
        return position;
    }

    public void setPosition(final Point position) {
        this.position = position;
    }
}
