Feature: Create a new test employee

Background:
  Given I select the browser 'chrome'
Given I log into ops dashboard with valid login credentials 
When I click on "Employee Management" module
And I click on the 'Create Test Employee' link


@tid297
Scenario: Create employee without mandatory fields
Given I am on the 'Create Employee' page
When I fill in the mandatory fields with the following data
| birthday | none |
| email | none |
And I click on 'Create Employee' button
Then I should see an error message


@tid298
Scenario: Create employee with only mandatory fields
Given I am on the 'Create Employee' page
When I fill in the mandatory fields with the following data
| birthday | 01/01/1999 |
| email | daniel.podstavek+@{RANDOM_INTEGER}@zenjob.com |
And I click on 'Create Employee' button
Then I should be on the 'Employee Overview' page


@tid299
Scenario: Create employee with invalid Email address
Given I am on the 'Create Employee' page
When I fill in the mandatory fields with the following data
| birthday | 01/01/1999 |
| email | azerty |
And I click on 'Create Employee' button
Then I should see the error message "The value 'azerty' does not seems to be a valid email address."

