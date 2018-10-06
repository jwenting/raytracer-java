package com.captainduckman.rt.core.phong;

import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.pattern.Pattern;
import com.captainduckman.rt.core.pattern.Solid;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

class MaterialTest {

    @Test
    void colour() {
        Material material = new Material();
        Material material1 = new Material(new Colour(2, 2, 2), 1, 1, 1, 1);
        assertEquals(new Solid(new Colour(1, 1, 1)), material.getPattern());
        assertEquals(new Solid(new Colour(2, 2, 2)), material1.getPattern());

        Pattern newColour = new Solid(new Colour(1, 2, 3));
        assertNotEquals(newColour, material.getPattern());
        material.setPattern(newColour);
        assertEquals(newColour, material.getPattern());
    }

    @Test
    void ambient() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertEquals(0.1, material.getAmbient());
        assertEquals(1, material1.getAmbient());
        material.setAmbient(2);
        assertEquals(2, material.getAmbient());
    }

    @Test
    void diffuse() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertEquals(0.9, material.getDiffuse());
        assertEquals(1, material1.getDiffuse());
        material.setDiffuse(2);
        assertEquals(2, material.getDiffuse());
    }

    @Test
    void specular() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertEquals(0.9, material.getSpecular());
        assertEquals(1, material1.getSpecular());
        material.setSpecular(2);
        assertEquals(2, material.getSpecular());
    }

    @Test
    void shininess() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertEquals(200, material.getShininess());
        assertEquals(1, material1.getShininess());
        material.setShininess(2);
        assertEquals(2, material.getShininess());
    }

    @Test
    void testEquals() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        Material material2 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertEquals(material1, material2);
        assertNotEquals(material, material1);
        assertNotEquals(material, null);
        assertNotEquals(material, new StringIndexOutOfBoundsException());
    }

    @Test
    void testHashCode() {
        Material material = new Material();
        Material material1 = new Material(new Colour(1, 1, 1), 1, 1, 1, 1);
        assertNotEquals(material.hashCode(), material1.hashCode());
    }

    @Test
    void testToString() {
        assertTrue(new Material().toString().startsWith("Material{"));
    }
}