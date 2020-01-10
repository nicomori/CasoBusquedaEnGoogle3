Feature: Shift requirements management

Background:
Given I select the browser 'chrome'
And I am logged in as global manager
And I go to new order page
And I choose location
And I fill all fields with valid values

@tid666
Scenario: Create requirements template with all fields filled
Given I am on shift details form
When I fill all fields for job requirement
And I click save new template button
Then job requirement template was saved

@tid679
Scenario: Create contact template with all fields filled
Given I am on shift details form
When I fill all fields for contact person
And I click save new template button
Then contact person template was saved