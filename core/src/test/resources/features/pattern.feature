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
    Then stripe colour at [-1.0,0.0,0.0]is [0.0,0.0,0.0]
    Then stripe colour at [-1.1,0.0,1.0] is [1.0,1.0,1.0]

  Scenario: Lighting with an applied pattern
    Given: stripe pattern with colours [1.0,1.0,1.0] and [0.0,0.0,0.0] set to material
    And material has ambient 1.0
    And material has diffuse 0.0
    And material has specular 0.0
    And camera at [0.0, 0.0, -1.0]
    And normal at [0.0, 0.0, -1.0]
    And light is point light at point[0.0, 0.0, -10.0] and colour[1.0, 1.0, 1.0]
    When lighting at point [0.9, 0.0, 0.0]
    Then colour is [1.0, 1.0, 1.0]
    When lighting at point [1.1, 0.0, 0.0]
    Then colour is [0.0, 0.0, 0.0]