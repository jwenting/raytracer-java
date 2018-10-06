package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.Point;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.phong.Material;
import cucumber.api.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternRunSteps implements En {

    private StripePattern pattern;
    private Material material;

    public PatternRunSteps() {
        Given("a stripe pattern with colours [{double},{double},{double}] and [{double},{double},{double}]",
                (Double c1r, Double c1g, Double c1b, Double c2r, Double c2g, Double c2b) -> {
                    Colour a = new Colour(c1r, c1g, c1b);
                    Colour b = new Colour(c2r, c2g, c2b);
                    pattern = new StripePattern(a, b);
                });
        Then("pattern colour a is [{double},{double},{double}]", (Double c1r, Double c1g, Double c1b) -> {
            Colour a = new Colour(c1r, c1g, c1b);
            assertEquals(a, pattern.getFirstColour());
        });
        And("pattern colour b is [{double},{double},{double}]", (Double c1r, Double c1g, Double c1b) -> {
            Colour a = new Colour(c1r, c1g, c1b);
            assertEquals(a, pattern.getSecondColour());
        });
        Then("stripe colour at [{double},{double},{double}] is [{double},{double},{double}]",
                (Double x, Double y, Double z, Double cr, Double cg, Double cb) -> {
                    Point point = new Point(x, y, z);
                    Colour a = new Colour(cr, cg, cb);
                    assertEquals(a, pattern.colourAt(point));
                });
        Given("stripe pattern with colours stripe colour at [{double},{double},{double}] and stripe colour at [{double},{double},{double}] set to material",
                (Double c1r, Double c1g, Double c1b, Double c2r, Double c2g, Double c2b) -> {
                    Colour a = new Colour(c1r, c1g, c1b);
                    Colour b = new Colour(c2r, c2g, c2b);
                    pattern = new StripePattern(a, b);
                    material = new Material();
                    material.setPattern(pattern);
                });
    }
}
