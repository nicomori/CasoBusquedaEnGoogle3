Feature: Login

Background:
  Given I select the browser 'chrome'

@tid614
Scenario: Login with valid data
  Given I am on existing company page
  When I fill email and the password fields with valid data
  And I click on the login button
  Then I am logged in to the company app

@tid618
Scenario: Login with invalid credentials
  Given I am on existing company page
  When I fill email and the password fields with invalid data
  And I click on the login button
  Then I am not logged in to the company app



