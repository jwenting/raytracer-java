package com.captainduckman.rt.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import static com.captainduckman.rt.core.Colour.BLANK;

public class Canvas {
    private static final Logger LOGGER = LoggerFactory.getLogger(Canvas.class);

    private int width;
    private int height;

    private Colour[][] grid;

    public Canvas(final int width, final int height) {
        this.width = width;
        this.height = height;
        grid = new Colour[width][height];
        for (int w = 0; w < width; w++) {
            for (int h = 0; h < height; h++) {
                grid[w][h] = BLANK;
            }
        }

    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public Colour getColourAt(final int w, final int h) {
        if (w < 0 || h < 0 || w >= grid.length || h >= grid[w].length) {
            LOGGER.info("attempting to access outside of canvas boundaries");
            return null;
        }
        return grid[w][h];
    }

    public void setColourAt(final int w, final int h, Colour colour) {
        if (w < 0 || h < 0 || w >= grid.length || h >= grid[w].length) {
            LOGGER.info("attempting to plot outside of canvas boundaries");
            return;
        }
        grid[w][h] = colour;
    }

}
