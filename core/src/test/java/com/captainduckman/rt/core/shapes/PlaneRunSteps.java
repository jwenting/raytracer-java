package com.captainduckman.rt.core.shapes;

import com.captainduckman.math.Point;
import com.captainduckman.math.Vector;
import com.captainduckman.rt.core.Intersection;
import com.captainduckman.rt.core.Ray;
import cucumber.api.java8.En;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.IsEqual.equalTo;

public class PlaneRunSteps implements En {
    private Plane plane;
    private Vector normal;
    private Ray ray;
    private Intersection[] intersections;

    public PlaneRunSteps() {
        Given("a plane", () -> plane = new Plane());
        When("normal from plane at point {double}, {double}, {double}", (Double x, Double y, Double z) -> {
            normal = plane.normal(new Point(x, y, z));
            System.out.println("generated normal: " + normal);
        });

        Then("the result should be vector {double}, {double}, {double}", (Double x, Double y, Double z) -> {
            // Write code here that turns the phrase above into concrete actions
            Vector expected = new Vector(x, y, z);
            System.out.println("got normal: " + normal);
            assertThat(normal, equalTo(expected));
        });

        And("ray from point at {double}, {double}, {double} and vector at {double}, {double}, {double}", (Double arg0, Double arg1, Double arg2, Double arg3, Double arg4, Double arg5) -> ray = new Ray(new Point(arg0, arg1, arg2), new Vector(arg3, arg4, arg5)));

        When("^intersect at plane and ray$", () -> intersections = ray.intersect(plane));

        Then("^intersect is empty$", () -> assertThat(0, equalTo(intersections.length)));

        Then("intersection count is {int}", (Integer arg0) -> assertThat(arg0, equalTo(intersections.length)));

        And("intersection tvalue {int} is {double}", (Integer arg0, Double arg1) -> assertThat(arg1, equalTo(intersections[arg0].gettValue())));

        And("intersection {int} is with plane", (Integer arg0) -> assertThat(plane, equalTo(intersections[arg0].getShape())));

    }


}
