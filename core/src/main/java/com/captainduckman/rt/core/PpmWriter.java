package com.captainduckman.rt.core;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.FileWriter;
import java.io.IOException;

public class PpmWriter {

    private static final Logger LOGGER = LoggerFactory.getLogger(PpmWriter.class);

    private static final int MAX_LINE_LENGTH = 70;

    private final int valueLength;
    private final int colourDepth;

    public PpmWriter(final int colourDepth) {
        this.colourDepth = colourDepth;
        valueLength = Integer.toString(colourDepth).length() * 3 + 3;
    }

    public StringBuilder canvasToPpm(final Canvas canvas) {
        LOGGER.info("Converting canvas to PPM");
        StringBuilder builder = new StringBuilder();
        addFileHeader(builder, canvas);

        int width = canvas.getWidth();
        int height = canvas.getHeight();

        for (int w = 0; w < height; w++) {
            buildLine(canvas, builder, width, w);
            if (builder.charAt(builder.length() - 1) != '\n') {
                builder.append('\n');
            }
        }

        return builder;
    }

    public void writePpmFile(final String fileName, final Canvas canvas) {
        LOGGER.info("Starting export of canvas to PPM file {}", fileName);
        StringBuilder builder = canvasToPpm(canvas);
        try (FileWriter fileWriter = new FileWriter(fileName)) {
            fileWriter.append(builder);
        } catch (IOException e) {
            LOGGER.error("Failed to export to file", e);
        }
    }

    private void buildLine(final Canvas canvas, final StringBuilder builder, final int width, final int h) {
        StringBuilder line = new StringBuilder();
        for (int w = 0; w < width; w++) {
            addPixel(line, canvas.getColourAt(w, h));
            if (line.length() + valueLength > MAX_LINE_LENGTH) {
                builder.append(line).append('\n');
                line = new StringBuilder();
            }
        }
        builder.append(line);
    }


    private void addPixel(final StringBuilder builder, final Colour colour) {
        int red = mapToColourDepth(colour.getRed());
        int green = mapToColourDepth(colour.getGreen());
        int blue = mapToColourDepth(colour.getBlue());
        builder.append(red).append(' ').append(green).append(' ').append(blue).append(' ');
    }

    private int mapToColourDepth(final double value) {
        int normalised;
        if (value < 0) {
            normalised = 0;
        } else {
            normalised = (int) Math.round(value * colourDepth);
        }
        if (normalised > colourDepth) {
            normalised = colourDepth;
        }
        return normalised;
    }

    private void addFileHeader(final StringBuilder builder, final Canvas canvas) {
        builder.append("P3").append('\n');
        builder.append(canvas.getWidth()).append(' ').append(canvas.getHeight()).append('\n');
        builder.append(colourDepth).append('\n');
    }
}
