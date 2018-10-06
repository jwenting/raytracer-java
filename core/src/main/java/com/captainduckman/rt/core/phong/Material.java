package com.captainduckman.rt.core.phong;

import com.captainduckman.math.MathUtils;
import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.LightSource;
import com.captainduckman.rt.core.pattern.Pattern;
import com.captainduckman.rt.core.pattern.Solid;

import java.util.Objects;

public class Material {
    private Pattern pattern;
    private double ambient;
    private double diffuse;
    private double specular;
    private double shininess;

    public Material(final Colour colour, final double ambient, final double diffuse, final double specular, final double shininess) {
        this.pattern = new Solid(colour);
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public Material(final Pattern pattern, final double ambient, final double diffuse, final double specular, final double shininess) {
        this.pattern = pattern;
        this.ambient = ambient;
        this.diffuse = diffuse;
        this.specular = specular;
        this.shininess = shininess;
    }

    public Material() {
        this.pattern = new Solid(new Colour(1, 1, 1));
        this.ambient = .1;
        this.diffuse = .9;
        this.specular = .9;
        this.shininess = 200;
    }

    public Pattern getPattern() {
        return pattern;
    }

    public void setPattern(final Pattern pattern) {
        this.pattern = pattern;
    }

    public double getAmbient() {
        return ambient;
    }

    public void setAmbient(final double ambient) {
        this.ambient = ambient;
    }

    public double getDiffuse() {
        return diffuse;
    }

    public void setDiffuse(final double diffuse) {
        this.diffuse = diffuse;
    }

    public double getSpecular() {
        return specular;
    }

    public void setSpecular(final double specular) {
        this.specular = specular;
    }

    public double getShininess() {
        return shininess;
    }

    public void setShininess(final double shininess) {
        this.shininess = shininess;
    }

    @Override
    public boolean equals(final Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Material material = (Material) o;
        return MathUtils.compare(material.ambient, ambient) &&
                MathUtils.compare(material.diffuse, diffuse) &&
                MathUtils.compare(material.specular, specular) &&
                MathUtils.compare(material.shininess, shininess) &&
                pattern.equals(material.pattern);
    }

    @Override
    public int hashCode() {
        return Objects.hash(pattern, ambient, diffuse, specular, shininess);
    }

    @Override
    public String toString() {
        return "Material{" +
                ", ambient=" + ambient +
                ", diffuse=" + diffuse +
                ", specular=" + specular +
                ", shininess=" + shininess +
                ", pattern=" + pattern +
                '}';
    }

    public Colour lighting(final LightSource light, final Point position, final Vector camera, final Vector normal, final boolean inShadow) {
        Colour intensity = new Colour(light.getIntensity());
        Colour materialColour = new Colour(pattern.colourAt(position));
        Colour effectiveColour = materialColour.multiply(intensity);
        Vector lightV = new Point(light.getPosition()).subtract(position).normalise();
        double lightDotNormal = lightV.dot(normal);
        Colour ambientColour = new Colour(effectiveColour).multiply(ambient);
        Colour diffuseColour = new Colour(0, 0, 0);
        Colour specularColour = new Colour(0, 0, 0);
        if (lightDotNormal > 0) {
            diffuseColour = new Colour(effectiveColour).multiply(diffuse).multiply(lightDotNormal);
            Vector reflectV = new Vector(lightV).negate().reflect(normal);
            double reflectDotCamera = Math.pow(reflectV.dot(camera), shininess);
            if (reflectDotCamera > 0) {
                specularColour = new Colour(intensity).multiply(specular).multiply(reflectDotCamera);
            }
        }

        return inShadow ? ambientColour : specularColour.add(diffuseColour).add(ambientColour);
    }
}
