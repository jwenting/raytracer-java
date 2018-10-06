package com.captainduckman.samples.shooter;

import com.captainduckman.math.Vector;

public class World {
    private Vector gravity;
    private Vector wind;

    public World(final Vector gravity, final Vector wind) {
        this.gravity = gravity;
        this.wind = wind;
    }

    public Vector getGravity() {
        return gravity;
    }

    public void setGravity(final Vector gravity) {
        this.gravity = gravity;
    }

    public Vector getWind() {
        return wind;
    }

    public void setWind(final Vector wind) {
        this.wind = wind;
    }
}
