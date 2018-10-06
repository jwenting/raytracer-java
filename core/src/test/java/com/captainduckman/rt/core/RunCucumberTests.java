package com.captainduckman.rt.core;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(plugin = {"pretty"},
        features = {"classpath:features/planes.feature",
                "classpath:features/pattern.feature"},
        glue = {"com.captainduckman.rt.core.shapes",
                "com.captainduckman.rt.core.pattern"})
public class RunCucumberTests {
}
