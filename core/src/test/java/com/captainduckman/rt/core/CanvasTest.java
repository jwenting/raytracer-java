package com.captainduckman.rt.core;

import com.captainduckman.math.AbstractMathTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.captainduckman.rt.core.Colour.BLANK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

@DisplayName("Canvas Test")
class CanvasTest extends AbstractMathTest {

    private Canvas canvas;
    private int width = 10;
    private int height = 20;


    @BeforeEach
    void before() {
        canvas = new Canvas(width, height);
    }

    @Test
    void testCreate() {

        assertEquals(width, canvas.getWidth());
        assertEquals(height, canvas.getHeight());

        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                assertEquals(BLANK, canvas.getColourAt(w, h));
            }
        }
    }

    @Test
    void colourSetOutsideBounds() {
        canvas = new Canvas(width, height);
        canvas.setColourAt(1, 1, new Colour(1, 1, 1));

        canvas.setColourAt(-1, 1, new Colour(1, 1, 1));
        canvas.setColourAt(1, -1, new Colour(1, 1, 1));
        canvas.setColourAt(width, 1, new Colour(1, 1, 1));
        canvas.setColourAt(-1, height, new Colour(1, 1, 1));

        assertNull(canvas.getColourAt(-1, 1));
        assertNull(canvas.getColourAt(1, -1));
        assertNull(canvas.getColourAt(width, 1));
        assertNull(canvas.getColourAt(1, height));
    }

}