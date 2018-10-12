package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.phong.Material;

public abstract class AbstractShape<E extends Shape> implements Shape<E> {

    protected Point position;
    protected MutationMatrix transformationMatrix;
    protected Material material;

    public AbstractShape(final Point position) {
        this.position = new Point(position);
        this.material = new Material();
        transformationMatrix = new MutationMatrix();
        material = new Material();
    }

    @Override
    public Point getPosition() {
        return position;
    }

    @Override
    public void setPosition(final Point position) {
        this.position = position;
    }

    @Override
    public Material getMaterial() {
        return material;
    }

    @Override
    public void setMaterial(final Material material) {
        this.material = material;
    }

    @Override
    public E translate(final TranslationMatrix matrix) {
        position = position.translate(matrix);
        return (E) this;
    }

    @Override
    public Vector normal(final Point p) {
        Point worldSpacePoint = new Point(p);
        Vector worldSpaceNormal = worldSpacePoint.subtract(position);
        if (transformationMatrix != null) {
            MutationMatrix matrix = new MutationMatrix(transformationMatrix);
            MutationMatrix inverse = matrix.inverse();
            Point objectSpacePoint = inverse.multiply(worldSpacePoint);
            Vector objectSpaceNormal = objectSpacePoint.subtract(position);
            worldSpaceNormal = inverse.transpose().multiply(objectSpaceNormal);
        }
        return worldSpaceNormal.normalise();
    }

    @Override
    public E transform(final MutationMatrix matrix) {
        if (transformationMatrix == null) {
            transformationMatrix = new MutationMatrix(matrix);
        } else {
            transformationMatrix.setMatrix(transformationMatrix.getMatrix().multiply(matrix.getMatrix()));
        }
        return (E) this;
    }

    @Override
    public MutationMatrix getTransformation() {
        return transformationMatrix;
    }

    public Point pointToPatternSpace(final Point point) {
        MutationMatrix worldToObjectSpace = new MutationMatrix(getTransformation()).inverse();
        Point objectSpace = new Point(point).transform(worldToObjectSpace);
        MutationMatrix patternTransformation = new MutationMatrix(material.getPattern().getTransformation());
        MutationMatrix worldToPatternSpace = patternTransformation.inverse();
        return objectSpace.transform(worldToPatternSpace);
    }
}
