Feature: plane tests

  Scenario:  a plane's normal is the same everywhere
    Given a plane
    When normal from plane at point 0.0, 0.0, 0.0
    And normal from plane at point 10.0, 0.0, -10.0
    And normal from plane at point -5.0, 0.0, 150.0
    Then the result should be vector 0.0, 1.0, 0.0
    And the result should be vector 0.0, 1.0, 0.0
    And the result should be vector 0.0, 1.0, 0.0

    Given a plane
    And ray from point at 0.0, 10.0, 0.0 and vector at 0.0, 0.0, 1.0
    When intersect at plane and ray
    Then intersect is empty

    Given a plane
    And ray from point at 0.0, 0.0, 0.0 and vector at 0.0, 0.0, 1.0
    When intersect at plane and ray
    Then intersect is empty

  Scenario: a ray intercepting a plane from above
    Given a plane
    And ray from point at 0.0, 1.0, 0.0 and vector at 0.0, -1.0, 0.0
    When intersect at plane and ray
    Then intersection count is 1
    And intersection tvalue 0 is 1.0
    And intersection 0 is with plane

  Scenario: a ray intercepting a plane from below
    Given a plane
    And ray from point at 0.0, -1.0, 0.0 and vector at 0.0, 1.0, 0.0
    When intersect at plane and ray
    Then intersection count is 1
    And intersection tvalue 0 is 1.0
    And intersection 0 is with plane