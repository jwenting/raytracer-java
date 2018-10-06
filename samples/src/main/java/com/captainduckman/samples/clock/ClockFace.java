package com.captainduckman.samples.clock;

import com.captainduckman.math.Point;
import com.captainduckman.math.RotationMatrixZ;
import com.captainduckman.rt.core.Canvas;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.PpmWriter;

public class ClockFace {

    public static void main(String[] args) {
        Canvas canvas = new Canvas(100, 100);
        Point point = new Point(0, 20, 0);
        canvas.setColourAt((int) point.getX() + 50, (int) point.getY() + 50, new Colour(1, 0, 0));
        // 2PI/12
        double hourRot = Math.PI / 6;
        for (int i = 0; i < 12; i++) {
            RotationMatrixZ matrix = new RotationMatrixZ(hourRot);
            point = point.rotateZ(matrix);
            canvas.setColourAt((int) point.getX() + 50, (int) point.getY() + 50, new Colour(1, 0, 0));
        }
        StringBuilder builder = new PpmWriter(255).canvasToPpm(canvas);
        System.out.println(builder);
        new PpmWriter(255).writePpmFile("d:\\tmp\\test.ppm", canvas);

    }
}
