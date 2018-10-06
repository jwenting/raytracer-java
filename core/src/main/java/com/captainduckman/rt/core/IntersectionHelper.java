package com.captainduckman.rt.core;

public class IntersectionHelper {

    private IntersectionHelper() {
        // nothing to see here, move along citizen
    }

    public static Intersection hit(Intersection... intersections) {
        Intersection intersection = null;

        for (Intersection i : intersections) {
            if (i.gettValue() >= 0 && (intersection == null || i.gettValue() < intersection.gettValue())) {
                intersection = i;
            }
        }
        return intersection;
    }
}
