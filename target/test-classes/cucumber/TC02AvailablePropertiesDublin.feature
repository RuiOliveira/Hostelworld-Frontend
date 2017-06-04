Feature: Calculator
  As a user I search available properties in Dublin, Ireland sorted by Name

  Scenario: Search available properties in Dublin, Ireland sorted by Name with medium resolution
    Given I am in Hostelworld home page with "medium"
    Then search "Dublin, Ireland" in input search
    And I press search button
    Then I expected to be in results page
    And I sort the results by "Name"
    Then I close the browser