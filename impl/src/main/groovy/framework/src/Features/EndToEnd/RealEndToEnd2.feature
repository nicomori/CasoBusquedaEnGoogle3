Feature: E2E - Order placement from company app

  @wip
  Scenario Outline: End to End - Creating a order, approving

    #Company creating the order
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
#    And In the order creation I click submit button
#    Then I make the logout
#    #Company - approving the new order created
#    Then access to the '<url>'
#    When I fill email and the password fields with valid data like a global manager
#    And I click on the login button
#    Then Follow the menu to the orders page
#    When I am approving order
#    Then order become approved
#    And approval is tracked in activities
#    #Ops part
#    Then access to the '<secondUrl>'
#    Then Created order from company app should display in Order management module of core application
#    And I access to the order where I make click in the button create job to access to start to create the job
#    And In the detail page of the detail job created, I can write the job description Title
#    Then In the page of the Job Overview, I make click in the botton Start Marching, and click in the popup to accept
#    Then in the page to match a talent I include the name of the talent and the last name to find the talent
#    #Mobile part
#    Given I am accessing ios
#    Given I am on welcome page
#    When I click on I already have a login
#    And I insert my login and click on login
#    And I dismiss the promotion message
#    Then I should enter the app
#    And I click on the first offer
#    And I click on the apply button
#    And I sign the screen
#    And I send the signed contract
#    #Ops part
#    Given I select the browser '<browserName>'
#    Then access to the '<secondUrl>'
#    When I log into ops dashboard with valid login credentials
#    And I click on "Job Management" module
#    And I go to the Job Details for the job
#    Then I should see the user in the 'Applied' tab
#    When I book this talent
#    Then This talent should be in 'Booked' tab
#    #Company Part
#    Given access to the '<url>'
#    And I am logged in as global manager
#    Then I see booked talent on Booked shifts tab


    Examples:
      | browserName  | url                                      | secondUrl                       |
      | chrome       | https://company-release.zenjob.org/auto  | https://staging-main.zenjob.org |