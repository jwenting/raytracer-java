package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.TranslationMatrix;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;
import com.captainduckman.rt.core.phong.Material;

public interface Shape<E extends Shape> {
    Intersection[] intersect(Ray ray);

    E translate(TranslationMatrix matrix);

    Vector normal(Point p);

    Point getPosition();

    void setPosition(Point position);

    Material getMaterial();

    void setMaterial(Material material);

    E transform(MutationMatrix matrix);

    MutationMatrix getTransformation();
}
