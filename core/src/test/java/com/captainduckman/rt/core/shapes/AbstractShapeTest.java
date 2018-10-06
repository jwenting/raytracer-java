package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.ScalingMatrix;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Colour;
import com.captainduckman.rt.core.phong.Material;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.captainduckman.math.MathUtils.HALF_SQRT2;
import static org.junit.jupiter.api.Assertions.assertEquals;

class AbstractShapeTest {

    @Test
    @DisplayName("test position")
    void position() {
        Point point = new Point(1, 2, 3);
        MockShape shape = new MockShape(point);

        assertEquals(point, shape.position);

        Point newPoint = new Point(2, 4, 56);
        shape.setPosition(newPoint);
        assertEquals(newPoint, shape.getPosition());
    }

    @Test
    @DisplayName("test material")
    void material() {
        Point point = new Point(1, 2, 3);
        MockShape mockShape = new MockShape(point);
        Material expected = new Material();
        assertEquals(expected, mockShape.getMaterial());
        Material newMaterial = new Material(new Colour(0, 0.5, 0.5), 0.3, 0.4, 0.76, 150);
        mockShape.setMaterial(newMaterial);
        assertEquals(newMaterial, mockShape.getMaterial());
    }

    @Test
    @DisplayName("test create")
    void create() {
        MockShape mockShape = new MockShape();
        assertEquals(new Point(0, 0, 0), mockShape.getPosition());
        assertEquals(new Material(), mockShape.getMaterial());
        assertEquals(new MutationMatrix(), mockShape.getTransformation());
    }

    @Test
    @DisplayName("test transformation")
    void transform() {
        MutationMatrix transformation = new TranslationMatrix(2, 3, 4);
        MockShape shape = new MockShape();
        shape.transform(transformation);
        assertEquals(transformation, shape.getTransformation());
    }

    @Test
    @DisplayName("normal on translated shape")
    void normalTranslated() {
        MockShape shape = new MockShape();
        MutationMatrix mutationMatrix = new TranslationMatrix(0, 1, 0);
        shape.transform(mutationMatrix);
        Vector normal = shape.normal(new Point(0, 1.70711, -0.70711));
        assertEquals(new Vector(0, 0.70711, -0.70711), normal);
    }

    @Test
    @DisplayName("normal on scaled shape")
    void normalScaled() {
        MockShape shape = new MockShape();
        MutationMatrix mutationMatrix = new ScalingMatrix(1.0, 0.5, 1.0);
        shape.transform(mutationMatrix);
        Vector normal = shape.normal(new Point(0, HALF_SQRT2, -HALF_SQRT2));
        assertEquals(new Vector(0, .97014, -.24254), normal);
    }


    @Test
    void translate() {
        Point point = new Point(1, 2, 3);
        MockShape sphere = new MockShape(point);
        TranslationMatrix translationMatrix = new TranslationMatrix(2, 3, 4);
        sphere = sphere.translate(translationMatrix);
        Point expected = new Point(3, 5, 7);
        assertEquals(expected, sphere.position);
    }


}