package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.MathUtils;
import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Colour;

public class StripePattern implements Pattern {
    private Colour firstColour;
    private Colour secondColour;

    public StripePattern(final Colour a, final Colour b) {
        firstColour = a;
        secondColour = b;
    }

    public Colour getFirstColour() {
        return firstColour;
    }

    public Colour getSecondColour() {
        return secondColour;
    }

    @Override
    public Colour colourAt(final Point point) {
        return MathUtils.compare(0.0, Math.floor(point.getX()) % 2) ? firstColour : secondColour;
    }

}
