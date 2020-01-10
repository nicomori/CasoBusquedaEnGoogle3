Feature: Import employee data from json

Background: 
Given I have created a new employee

@tid412
Scenario: Import Data - successful
Given I am on the 'Employee Details' page of an employee
When I click on 'Import Employee Data' button
And I am on the 'Import Data' page
And I fill in valid employee data in correct format
And I click on 'Import from Json' button
Then I should not see any error messages
And I should be on the 'Employee Details' page
And All imported data should be displayed in respective panels

@tid415
Scenario: Import Data - unsuccessful
Given I am on the 'Employee Details' page of an employee
When I click on 'Import Employee Data' button
And I am on the 'Import Data' page
And I fill in the form with invalid employee data
And I click on 'Import from Json' button
Then I should see an error message

