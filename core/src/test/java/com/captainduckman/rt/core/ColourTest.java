package com.captainduckman.rt.core;

import com.captainduckman.math.AbstractMathTest;
import com.captainduckman.math.Tuple;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@DisplayName("Colour Test")
class ColourTest extends AbstractMathTest {

    @Test
    void testCreateColour() {
        Colour colour = new Colour(-.5, .4, 1.7);
        assertTrue(compare(-.5, colour.getRed()));
        assertTrue(compare(.4, colour.getGreen()));
        assertTrue(compare(1.7, colour.getBlue()));
    }

    @Test
    void testCreate() {
        Tuple tuple = new Tuple(1.0, 2.0, 3.0, 0.0);
        Colour colour = new Colour(tuple);
        assertTrue(compare(1, colour.getRed()));
        assertTrue(compare(2, colour.getGreen()));
        assertTrue(compare(3, colour.getBlue()));
    }

    @Test
    void testEquals() {
        Tuple tuple = new Tuple(1.0, 2.0, 3.0, 0.0);
        Colour colour = new Colour(tuple);
        boolean b = colour.equals(null);
        assertFalse(b);
        b = colour.equals(new Object());
        assertFalse(b);
    }

    @Test
    void testToString() {
        Tuple tuple = new Tuple(1.0, 2.0, 3.0, 0.0);
        Colour colour = new Colour(tuple);

        assertTrue(colour.toString().startsWith("Colour{tuple="));
    }

    @Test
    void testAdd() {
        Colour colour = new Colour(.9, .6, .75);
        Colour colour2 = new Colour(.7, .1, .25);
        Colour expected = new Colour(1.6, .7, 1);
        Colour result = colour.add(colour2);
        assertEquals(expected, result);
    }

    @Test
    void testSubtract() {
        Colour colour = new Colour(.9, .6, .75);
        Colour colour2 = new Colour(.7, .1, .25);
        Colour expected = new Colour(.2, .5, .5);
        Colour result = colour.subtract(colour2);
        assertEquals(expected, result);
    }

    @Test
    void testMultiply() {
        Colour colour = new Colour(.2, .3, .4);
        Colour expected = new Colour(.4, .6, .8);
        Colour result = colour.multiply(2);
        assertEquals(expected, result);
        colour = new Colour(.2, .3, .4);
        Colour colour2 = new Colour(.7, .1, .2);
        result = colour.multiply(colour2);
        expected = new Colour(.14, .03, .08);
        assertEquals(expected, result);
    }

    @Test
    void getSet() {
        Colour colour = new Colour(.2, .3, .4);
        assertTrue(compare(.2, colour.getRed()));
        assertTrue(compare(.3, colour.getGreen()));
        assertTrue(compare(.4, colour.getBlue()));

        colour.setBlue(.6);
        colour.setGreen(.7);
        colour.setRed(.3);
        assertTrue(compare(.6, colour.getBlue()));
        assertTrue(compare(.7, colour.getGreen()));
        assertTrue(compare(.3, colour.getRed()));
    }

    @Test
    void testHashcode() {
        Colour colour = new Colour(.2, .3, .4);
        Tuple tuple = Tuple.vector(.2, .3, .4);
        assertTrue(compare(tuple.hashCode(), colour.hashCode()));
    }
}