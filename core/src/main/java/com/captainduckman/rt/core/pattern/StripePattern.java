package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.AbstractMutationMatrix;
import com.captainduckman.math.MathUtils;
import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Colour;

public class StripePattern implements Pattern {
    private Colour firstColour;
    private Colour secondColour;
    private MutationMatrix transformation;

    public StripePattern(final Colour a, final Colour b) {
        firstColour = a;
        secondColour = b;
        transformation = new MutationMatrix();
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

    @Override
    public void transform(final MutationMatrix mutationMatrix) {
        transformation = transformation.cross(mutationMatrix);
        System.out.println("transformation now: " + transformation);
    }

    @Override
    public AbstractMutationMatrix getTransformation() {
        return new MutationMatrix(transformation);
    }

}
