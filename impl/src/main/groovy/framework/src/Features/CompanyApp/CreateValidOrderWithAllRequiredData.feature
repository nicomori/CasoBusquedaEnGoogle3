
Feature: Order creation

  @c648
  Scenario Outline: Create valid order with all required data

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data
    And I click on the login button
    Then Follow the menu to the orders page
    When I click new order button
    Then I am on orders page
    And I choose location
    And I fill all fields with valid values
    And I fill all data for shift details
    And In the order creation I click submit button
    Then new order is created

    Examples:
      | browserName  | url                                              |
      | chrome       | https://company-release.zenjob.org/auto  |

  @c649
  Scenario Outline: Create valid order with several shifts

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data
    And I click on the login button
    Then Follow the menu to the orders page
    When I click new order button
    Then I am on orders page
    And I choose location
    And I fill all fields with valid values
    And I fill all data for shift details
    And I press in the button to add a new shift
    And I fill all fields of the second order with valid values
    And I fill all data for shift details
    And In the order creation I click submit button
    Then new order is created

    Examples:
      | browserName  | url                                              |
      | chrome       | https://company-release.zenjob.org/auto  |

