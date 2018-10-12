package com.captainduckman.rt.core.pattern;

import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.LightSource;
import com.captainduckman.rt.core.PointLight;
import com.captainduckman.rt.core.phong.Material;
import com.captainduckman.rt.core.shapes.Sphere;
import cucumber.api.java8.En;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PatternRunSteps implements En {

    private StripePattern pattern;
    private Material material;
    private LightSource lightSource;
    private Vector camera;
    private Point position;
    private Vector normal;
    private Colour lighting;
    private Sphere sphere;

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
        Given("stripe pattern with colours [{double},{double},{double}] and [{double},{double},{double}] set to material with ambient {double} with diffuse {double} with specular {double}",
                (Double c1r, Double c1g, Double c1b, Double c2r, Double c2g, Double c2b, Double ambient, Double diffuse, Double specular) -> {
                    Colour a = new Colour(c1r, c1g, c1b);
                    Colour b = new Colour(c2r, c2g, c2b);
                    pattern = new StripePattern(a, b);
                    material = new Material();
                    material.setPattern(pattern);
                    material.setAmbient(ambient);
                    material.setSpecular(specular);
                    material.setDiffuse(diffuse);
                });
        And("camera at [{double},{double},{double}]", (Double x, Double y, Double z) -> camera = new Vector(x, y, z));
        And("normal at [{double},{double},{double}]", (Double x, Double y, Double z) -> normal = new Vector(x, y, z));
        And("light is point light at position[{double},{double},{double}] and colour[{double},{double},{double}]", (Double x, Double y, Double z, Double r, Double g, Double b) -> {
            Point position = new Point(x, y, z);
            Colour colour = new Colour(r, g, b);
            lightSource = new PointLight(colour, position);
        });
        When("lighting at point [{double},{double},{double}]", (Double x, Double y, Double z) -> {
            position = new Point(x, y, z);
            lighting = material.lighting(lightSource, position, camera, normal, false);
        });
        Then("colour is [{double},{double},{double}]", (Double x, Double y, Double z) -> {
            Colour expected = new Colour(x, y, z);
            assertEquals(expected, lighting);
        });
        Given("a sphere with default dimensions", () -> sphere = new Sphere());
        And("has a stripe patterns with colours [{double},{double},{double}] and [{double},{double},{double}]",
                (Double r1, Double g1, Double b1, Double r2, Double g2, Double b2) -> {
                    pattern = new StripePattern(new Colour(r1, g1, b1), new Colour(r2, g2, b2));
                    material = new Material();
                    material.setPattern(pattern);
                    sphere.setMaterial(material);
                });
        And("the sphere is transformed using scaling[{double},{double},{double}]", (Double x, Double y, Double z) -> {
            ScalingMatrix mutationMatrix = new ScalingMatrix(x, y, z);
            sphere.transform(mutationMatrix);
        });
        And("the pattern is transformed using scaling[{double},{double},{double}]", (Double x, Double y, Double z) -> {
            ScalingMatrix mutationMatrix = new ScalingMatrix(x, y, z);
            pattern.transform(mutationMatrix);
        });
        And("the pattern is transformed using translation[{double},{double},{double}]", (Double x, Double y, Double z) -> {
            TranslationMatrix mutationMatrix = new TranslationMatrix(x, y, z);
            pattern.transform(mutationMatrix);
        });
        When("we get the colour at point [{double},{double},{double}]", (Double x, Double y, Double z) -> {
            Point point = new Point(x, y, z);
            lighting = pattern.colourAt(sphere.pointToPatternSpace(point));
        });

    }
}
