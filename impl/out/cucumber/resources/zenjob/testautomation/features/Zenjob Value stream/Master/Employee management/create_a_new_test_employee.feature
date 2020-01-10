Feature: Create a new test employee

Background: 
Given I log into ops dashboard with valid login credentials 
When I navigate to the "employee management" module
And I click on the create test employee link

@tid297
Scenario: Create employee without mandatory fields
Given I am on the create employee page
When I fill in the mandatory fields with the following data
| birthday | none |
| email | none |
And I submit the form 
Then I should see error message for not filling mandatory fields


@tid298
Scenario: Create employee with only mandatory fields
Given I am on the create employee page
When I fill in the mandatory fields with the following data
| birthday | 01/01/1999 |
| email | daniel.podstavek+@{RANDOM_INTEGER}@zenjob.com |
And I submit the form 
Then I should be on the employee overview page 

@tid299
Scenario: Create employee with invalid Email address
Given I am on the create employee page
When I fill in the mandatory fields with the following data
| birthday | 01/01/1999 |
| email | azerty |
And I submit the form 
Then I should see the error message "The value 'azerty' does not seems to be a valid email address."

