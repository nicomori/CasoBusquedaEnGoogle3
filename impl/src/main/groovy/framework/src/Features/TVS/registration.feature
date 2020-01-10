Feature: registration
@ioslocaltest
Scenario: registration german berlin
Given I am on welcome page
When I click on new user
  And I am over eighteen
  And I am a Student
  And I am German
  And I am from Berlin
  And My name is Roberto Roboto
  And I check terms
  And My email is x
  And I press Continue
Then I should see email verification