Feature: E2E - Order placement from company app

  @e2e
  Scenario Outline: Order placement from company app

    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data
    And I click on the login button
    When I create an order by selecting one job location and one shift all mandatory information
    Then access to the '<secondUrl>'
    Then Created order from company app should display in Order management module of core application

    Examples:
      | browserName  | url                                      | secondUrl                       |
      | chrome       | https://company-release.zenjob.org/auto  | https://staging-main.zenjob.org |