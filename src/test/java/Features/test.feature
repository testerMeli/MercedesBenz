Feature: Price
  Scenario: Validate A Class models price are between 15.000 and 60.000
    Given the user goes to the Mercedes website
    When select the model "Hatchbacks"
    And Build your car
    Then find the prices list
