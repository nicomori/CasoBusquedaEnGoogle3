Feature: E2E - Order placement from company app

  @setTimes @wip
  Scenario Outline: End to End - Creating a order, approving

    # Company creating the order
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

    # Company - approving the new order created
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a global manager
    And I click on the login button
    Then Follow the menu to the orders page
    When I am approving order
    Then order become approved
    And approval is tracked in activities
    Then I make the logout

    # Ops part
    Then access to the '<secondUrl>'
    Then Created order from company app should display in Order management module of core application
    And I access to the order where I make click in the button create job to access to start to create the job
    And In the detail of the job created, I am starting to set the title and the specials date and times for auto effective
    Then In the page of the Job Overview, I make click in the botton Start Marching, and click in the popup to accept
    Then in the page to match a talent I include the name of the talent and the last name to find the talent

    # Mobile part
    Given I am accessing android
    And I am in the welcome page and I press the button to go to the login page
    And In the login page I make a success login, with a user used for auto effective
    Then I am in the Dashboard page and I accept the first job of the list
    Then In the job details page, I make an scroll down and I accept the job
    Then In the signature page I make a signature and I press the button accept the job

    # Ops part
    Given I select the browser '<browserName>'
    Then access to the '<secondUrl>'
    When I log into ops dashboard with valid login credentials
    And I click on "Job Management" module
    And I go to the Job Details for the job
    Then I should see the user in the 'Applied' tab for E2E
    When I book this talent

    # Company App
    Given I select the browser '<browserName>'
    Then access to the '<url>'
    When I fill email and the password fields with valid data like a global manager
    And I click on the login button



    And Local temporary automation company app


#    And I am on page with ongoind and finished orders
#    And I choose finished shift with times stated by talent
#    When I state different talents times
#    Then company times for shift are not equal to talent ones

    Examples:
      | browserName  | url                                      | secondUrl                       |
      | chrome       | https://company-release.zenjob.org/auto  | https://staging-main.zenjob.org |