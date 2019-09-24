@SmokeTest
Feature: Cucumber run test

    I want to run a sample feature file.

  Scenario: Add a measurement with valid (numeric) values
    # POST /measurements
    When I submit a new measurement as follows:
      | timeStamp                  | temperature | dewPoint | precipitation |
      | "2015-09-01T16:00:00.000Z" | 27.1        | 16.7     | 0             |
    Then the response has a status code of 201

 Scenario: Cannot add a measurement with invalid values
    # POST /measurements
    When I submit a new measurement as follows:
      | timestamp                  | temperature    | dewPoint | precipitation |
      | "2015-09-01T16:00:00.000Z" | "not a number" | 16.7     | 0             |
    Then the response has a status code of 400

  Scenario: Cannot add a measurement without a timestamp
    # POST /measurements
    When I submit a new measurement as follows:
      | temperature | dewPoint | precipitation |
      | 27.1        | 20       | 0             |
    Then the response has a status code of 400

