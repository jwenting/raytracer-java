package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Colour;

/**
 * A pattern for texturing a material.
 */
public interface Pattern {
    /**
     * Calculate the colour at a given point.
     *
     * @param point the point
     * @return the colour for the given point
     */
    Colour colourAt(Point point);

}
