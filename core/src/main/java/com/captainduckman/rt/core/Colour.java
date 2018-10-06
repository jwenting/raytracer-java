package com.captainduckman.rt.core;

import com.captainduckman.math.Tuple;

import static com.captainduckman.math.Tuple.TYPE_VECTOR;

public class Colour {

    public static final Colour BLANK = new Colour(0, 0, 0);
    private Tuple tuple;

    public Colour(final double r, final double g, final double b) {
        tuple = new Tuple(r, g, b, TYPE_VECTOR);
    }

    public Colour(final Tuple tuple) {
        this.tuple = tuple;
        this.tuple.setW(TYPE_VECTOR);
    }

    public Colour(final Colour intensity) {
        this(intensity.getRed(), intensity.getGreen(), intensity.getBlue());
    }

    public double getRed() {
        return tuple.getX();
    }

    public void setRed(final double red) {
        tuple.setX(red);
    }

    public double getGreen() {
        return tuple.getY();
    }

    public void setGreen(final double green) {
        tuple.setY(green);
    }

    public double getBlue() {
        return tuple.getZ();
    }

    public void setBlue(final double blue) {
        tuple.setZ(blue);
    }

    public Colour add(final Colour colour2) {
        tuple = tuple.add(colour2.tuple);
        return this;
    }

    public Colour subtract(final Colour colour2) {
        tuple = tuple.subtract(colour2.tuple);
        return this;
    }

    public Colour multiply(final double factor) {
        tuple = tuple.multiply(factor);
        return this;
    }

    public Colour multiply(final Colour colour) {
        setRed(getRed() * colour.getRed());
        setGreen(getGreen() * colour.getGreen());
        setBlue(getBlue() * colour.getBlue());
        return this;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Colour colour = (Colour) o;
        return tuple.equals(colour.tuple);
    }

    @Override
    public int hashCode() {
        return tuple.hashCode();
    }

    @Override
    public String toString() {
        return "Colour{" +
                "tuple=" + tuple +
                '}';
    }
}
