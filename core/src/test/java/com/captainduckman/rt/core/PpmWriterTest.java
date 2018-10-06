package com.captainduckman.rt.core;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;

class PpmWriterTest {

    @Test
    void canvasToPpm() {
        Canvas canvas = new Canvas(5, 3);
        canvas.setColourAt(0, 0, new Colour(1.5, 0, 0));
        canvas.setColourAt(2, 1, new Colour(0, 0.5, 0));
        canvas.setColourAt(4, 2, new Colour(-.5, 0, 1));
        int colourDepth = 255;
        PpmWriter writer = new PpmWriter(colourDepth);
        StringBuilder builder = writer.canvasToPpm(canvas);
        assertTrue(builder.toString().startsWith("P3\n5 3\n255\n"));
        System.out.println(builder);
    }

    @Test
    void splitLines() {
        int width = 10;
        int height = 2;
        Canvas canvas = new Canvas(width, height);
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                canvas.setColourAt(w, h, new Colour(1, .8, .6));
            }
        }
        int colourDepth = 255;
        PpmWriter writer = new PpmWriter(colourDepth);
        StringBuilder builder = writer.canvasToPpm(canvas);
        assertTrue(builder.toString().startsWith("P3\n10 2\n255\n"));
        List<String> split = Arrays.asList(builder.toString().split("\n"));
        split.forEach(line -> assertTrue(line.length() <= 70));
        System.out.println(builder);
    }

}