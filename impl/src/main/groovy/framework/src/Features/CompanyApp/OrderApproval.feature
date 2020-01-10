Feature: Order creation

  @c663
  Scenario Outline: Create valid order with all required data

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a store manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I click new order button
    Then I am on orders page
    And I choose location
    And I fill all fields with valid values
    And I fill all data for shift details
    And In the order creation I click submit button
    Then I make the logout
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a global manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I am rejecting order
    Then order become rejected

    Examples:
      | browserName  | url                                      |
      | chrome       | https://company-release.zenjob.org/auto  |


  @c664
  Scenario Outline: Resubmit order by store manager

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a store manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I click new order button
    Then I am on orders page
    And I choose location
    And I fill all fields with valid values
    And I fill all data for shift details
    And In the order creation I click submit button
    Then I make the logout
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a global manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I am rejecting order
    Then order become rejected
    Then I make the logout
    When I fill email and the password fields with valid data like a store manager
    And I click on the login button
    Then Follow the menu to the orders page
    And I am on rejected order overview page
    When I am editing order
    And submiting order
    Then order get resubmited
    And submit event is tracked in activities

    Examples:
      | browserName  | url                                      |
      | chrome       | https://company-release.zenjob.org/auto  |


  @c665
  Scenario Outline: Resubmit order by store manager

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a store manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I click new order button
    Then I am on orders page
    And I choose location
    And I fill all fields with valid values
    And I fill all data for shift details
    And In the order creation I click submit button
    Then I make the logout
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a global manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I am approving order
    Then order become approved
    And approval is tracked in activities

    Examples:
      | browserName  | url                                      |
      | chrome       | https://company-release.zenjob.org/auto  |