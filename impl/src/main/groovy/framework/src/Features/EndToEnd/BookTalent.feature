Feature: E2E - Match a talent

  @e2e
  Scenario Outline: Match a talent

    Given I select the browser '<browserName>'
    Then access to the '<secondUrl>'
    When I log into ops dashboard with valid login credentials
    And I click on "Job Management" module
    And I go to the Job Details for the job
    Then I should see the user in the 'Applied' tab
    When I book this talent
    Then This talent should be in 'Booked' tab
    Given access to the '<url>'
    And I am logged in as global manager
    Then I see booked talent on Booked shifts tab


    Examples:
      | browserName  | url                                      | secondUrl                       |
      | chrome       | https://company-release.zenjob.org/auto  | https://staging-main.zenjob.org |