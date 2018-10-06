package com.captainduckman.rt.core;

import com.captainduckman.math.MutationMatrix;
import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.shapes.Shape;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class World {
    private static final Logger LOGGER = LoggerFactory.getLogger(World.class);
    private List<LightSource> lights;
    private List<Shape> objects;
    private MutationMatrix viewTransformationMatrix;

    public World() {
        lights = new ArrayList<>();
        objects = new ArrayList<>();
    }

    public void addLight(final LightSource light) {
        lights.add(light);
    }

    public void addObject(final Shape object) {
        objects.add(object);
    }

    public List<Intersection> intersect(final Ray ray) {
        List<Intersection> intersections = new ArrayList<>();
        objects.forEach(object -> {
            Intersection[] intersect = object.intersect(ray);
            intersections.addAll(Arrays.asList(intersect));
        });
        intersections.sort(new Intersection.IntersectionComparator());
        return intersections;
    }

    public Shape getObject(final int objectIndex) {
        return objects.get(objectIndex);
    }

    public Colour shade(final Intersection hit, final Ray ray) {
        hit.prepareHit(ray);
        boolean shadowed = isInShadow(hit.getPoint());
        final Colour colour = new Colour(0, 0, 0);
        lights.forEach(light -> colour.add(hit.getShape().getMaterial().lighting(light, hit.getPoint(), hit.getCamera(), hit.getNormal(), shadowed)));
        return colour;
    }

    public Colour colourAt(final Ray ray) {
        List<Intersection> intersect = intersect(ray);
        if (intersect.size() == 0) {
            return Colour.BLANK;
        }
        Intersection hit = intersect.stream().filter(intersection -> intersection.gettValue() > 0).findFirst().orElse(intersect.get(0));
        return shade(hit, ray);
    }

    public boolean isInShadow(final Point point) {
        LightSource light = lights.get(0);
        Vector vector = light.getPosition().subtract(point);
        double distance = vector.magnitude();
        vector = vector.normalise();
        Ray ray = new Ray(point, vector);
        List<Intersection> intersections = intersect(ray);
        Intersection hit = IntersectionHelper.hit(intersections.toArray(new Intersection[0]));
        return hit != null && hit.gettValue() < distance;
    }
}
