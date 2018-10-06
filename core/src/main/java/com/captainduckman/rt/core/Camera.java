package com.captainduckman.rt.core;

import com.captainduckman.math.Matrix;
import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Camera {
    private static final Logger LOGGER = LoggerFactory.getLogger(Camera.class);
    private final double hSize;
    private final double vSize;
    private final double fieldOfView;
    private MutationMatrix transformation;
    private double halfWidth;
    private double halfHeight;
    private double pixelSize;

    public Camera(final double hSize, final double vSize, final double fieldOfView) {
        this.hSize = hSize;
        this.vSize = vSize;
        this.fieldOfView = fieldOfView;
        transformation = new MutationMatrix();
        pixelSize = pixelSize();
    }

    public double gethSize() {
        return hSize;
    }

    public double getvSize() {
        return vSize;
    }

    public double getFieldOfView() {
        return fieldOfView;
    }

    public MutationMatrix getTransformation() {
        return transformation;
    }

    public MutationMatrix transformView(final Point from, final Point to, final Vector up) {
        MutationMatrix matrix = new MutationMatrix();
        Vector forward = to.subtract(from).normalise();
        Vector left = forward.cross(new Vector(up).normalise());
        Vector trueUp = left.cross(forward);
        Matrix orientation = new Matrix(4);
        orientation.set(0, 0, left.getX());
        orientation.set(0, 1, left.getY());
        orientation.set(0, 2, left.getZ());
        orientation.set(0, 3, 0);
        orientation.set(1, 0, trueUp.getX());
        orientation.set(1, 1, trueUp.getY());
        orientation.set(1, 2, trueUp.getZ());
        orientation.set(1, 3, 0);
        orientation.set(2, 0, -forward.getX());
        orientation.set(2, 1, -forward.getY());
        orientation.set(2, 2, -forward.getZ());
        orientation.set(2, 3, 0);
        orientation.set(3, 0, 0);
        orientation.set(3, 1, 0);
        orientation.set(3, 2, 0);
        orientation.set(3, 3, 1);
        TranslationMatrix translationMatrix = new TranslationMatrix(-from.getX(), -from.getY(), -from.getZ());
        matrix.setMatrix(orientation.multiply(translationMatrix.getMatrix()));
        transformation = matrix;
        return matrix;
    }

    private final double pixelSize() {
        double halfView = Math.tan(fieldOfView / 2);
        double aspectRatio = hSize / vSize;
        halfWidth = 0;
        halfHeight = 0;

        if (vSize < hSize) {
            halfHeight = halfView / aspectRatio;
            halfWidth = halfView;
        } else {
            halfHeight = halfView;
            halfWidth = halfView * aspectRatio;
        }
        return halfWidth * 2 / hSize;
    }

    public Ray getRay(final int px, final int py) {
        double xOffset = (px + .5) * pixelSize;
        double yOffset = (py + .5) * pixelSize;
        double worldX = halfWidth - xOffset;
        double worldY = halfHeight - yOffset;
        Point worldCoordinate = new Point(worldX, worldY, -1);
        MutationMatrix inverseTransformation = new MutationMatrix(transformation).inverse();
        Point transformedWorldCoordinate = new MutationMatrix(inverseTransformation).multiply(worldCoordinate);
        Point cameraLocation = new MutationMatrix(inverseTransformation).multiply(new Point(0, 0, 0));
        Vector direction = transformedWorldCoordinate.subtract(cameraLocation).normalise();
        return new Ray(cameraLocation, direction);
    }

    public void transform(final MutationMatrix mutationMatrix) {
        transformation = mutationMatrix;
    }

    public Canvas render(final World world) {
        Canvas canvas = new Canvas((int) hSize, (int) vSize);
        LOGGER.info("Rendering world {} to canvas {}", world, canvas);
        for (int y = 0; y < vSize; y++) {
            for (int x = 0; x < hSize; x++) {
                Ray ray = getRay(x, y);
                Colour colour = world.colourAt(ray);
                canvas.setColourAt(x, y, colour);
                LOGGER.debug("Rendered ({},{}) at {}", x, y, colour);
            }
        }
        return canvas;
    }
}
