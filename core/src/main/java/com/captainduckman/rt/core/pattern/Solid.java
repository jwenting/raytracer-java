package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Colour;

public class Solid implements Pattern {

    private final Colour colour;

    public Solid(final Colour colour) {
        this.colour = colour;
    }

    @Override
    public Colour colourAt(final Point point) {
        return colour;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        Solid solid = (Solid) o;

        return colour != null ? colour.equals(solid.colour) : solid.colour == null;
    }

    @Override
    public int hashCode() {
        return colour != null ? colour.hashCode() : 0;
    }
}
