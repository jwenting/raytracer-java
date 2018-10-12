Feature: pattern tests

  Scenario: stripe pattern
    Given a stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0]
    Then pattern colour a is [1.0,1.0,1.0]
    And pattern colour b is [0.0,0.0,0.0]
    Given a stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0]
    Then stripe colour at [0.0,0.0,0.0] is [1.0,1.0,1.0]
    Then stripe colour at [0.0,1.0,0.0] is [1.0,1.0,1.0]
    Then stripe colour at [0.0,2.0,0.0] is [1.0,1.0,1.0]
    Given a stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0]
    Then stripe colour at [0.0,0.0,.0] is [1.0,1.0,1.0]
    Then stripe colour at [0.0,0.0,1.0] is [1.0,1.0,1.0]
    Then stripe colour at [0.0,0.0,2.0] is [1.0,1.0,1.0]
    Given a stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0]
    Then stripe colour at [0.0,0.0,.0] is [1.0,1.0,1.0]
    Then stripe colour at [0.9,0.0,1.0] is [1.0,1.0,1.0]
    Then stripe colour at [1.0,0.0,2.0] is [0.0,0.0,0.0]
    Then stripe colour at [-0.1,0.0,2.0] is [0.0,0.0,0.0]
    Then stripe colour at [-1.0,0.0,0.0] is [0.0,0.0,0.0]
    Then stripe colour at [-1.1,0.0,1.0] is [1.0,1.0,1.0]

  Scenario: material with pattern
    Given stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0] set to material with ambient 1.0 with diffuse 0.0 with specular 0.0
    And camera at [0.0,0.0,-1.0]
    And normal at [0.0,0.0,-1.0]
    And light is point light at position[0.0,0.0,-10.0] and colour[1.0,1.0,1.0]
    When lighting at point [0.9,0.0,0.0]
    Then colour is [1.0,1.0,1.0]
    When lighting at point [1.1,0.0,0.0]
    Then colour is [0.0,0.0,0.0]

  Scenario: stripes with object transformation
    Given a sphere with default dimensions
    And the sphere is transformed using scaling[2.0,2.0,2.0]
    And has a stripe patterns with colours [0.0,0.0,0.0] and [1.0,1.0,1.0]
    When we get the colour at point [1.5,0.0,0.0]
    Then colour is [0.0,0.0,0.0]

  Scenario: stripes with pattern transformation
    Given a sphere with default dimensions
    And has a stripe patterns with colours [0.0,0.0,0.0] and [1.0,1.0,1.0]
    And the pattern is transformed using scaling[2.0,2.0,2.0]
    When we get the colour at point [1.5,0.0,0.0]
    Then colour is [0.0,0.0,0.0]

  Scenario: stripes with pattern and object transformation
    Given a sphere with default dimensions
    And the sphere is transformed using scaling[2.0,2.0,2.0]
    And has a stripe patterns with colours [0.0,0.0,0.0] and [1.0,1.0,1.0]
    And the pattern is transformed using translation[0.5,0.0,0.0]
    When we get the colour at point [2.5,0.0,0.0]
    Then colour is [0.0,0.0,0.0]
