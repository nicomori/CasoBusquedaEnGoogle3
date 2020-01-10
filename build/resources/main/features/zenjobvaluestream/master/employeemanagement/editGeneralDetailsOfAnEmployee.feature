Feature: Edit general details of an employee

Background:
Given I have created a new employee

@tid301
Scenario: Edit 'General details' section in employee details page - mandatory fields
Given I am on the 'Employee Details' page of an employee
When I click on the 'Edit' button for 'General Details' section
And I fill in the form with the following data
| birthday | 01/01/1999 |
And I submit the form
Then I should be on the 'Employee Details' page
And 'General Details' section should have correct data
|Birthday | 1999-01-01 |

@tid331
Scenario: Edit 'General details' section in employee details page - no mandatory fields
Given I am on the 'Employee Details' page of an employee
When I click on the 'Edit' button for 'General Details' section
  And I fill in the form with the following data
| birthday |  |
| email |  |
And I submit the form
Then I should see an error message

@tid330
Scenario: Edit 'General details' section in employee details page - all fields
Given I am on the 'Employee Details' page of an employee
And I click on the 'Edit' button for 'General Details' section
When I fill in the form with the following data
| firstName | firstname_update |
| lastName | lastname_update |
| gender | MALE |
| birthday | 01/01/1999 |
| mobileNumber | +4917678749111 |
And I submit the form
Then 'General Details' section should have correct data
  | Name | firstname_update |
  | Lastname | lastname_update |
  | Gender | MALE |
  | Birthday | 1999-01-01 |
  | Mobile Number | +4917678749111 |
