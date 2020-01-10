@ioslocaltest
Feature: login

Scenario: login into android app
Given I am on welcome page
When I click on I already have a login
And I insert my login and click on login
  And I dismiss the promotion message
Then I should enter the app