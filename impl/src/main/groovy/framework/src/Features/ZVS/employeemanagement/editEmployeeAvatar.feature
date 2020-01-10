Feature: Edit employee avatar

Background:
  Given I select the browser 'chrome'
Given I have created a new employee

@tid409 @fail
Scenario: Edit Employee Avatar - successful
Given I am on the 'Employee Details' page of an employee
When I click on 'Edit' button in 'Documents & Media' section
And I am on the 'Update Employee Media Files' page
And I upload an image in the correct format
Then I should see selected image on 'Update Employee Media Files' page
And I should see selected image in the 'Documents & Media' panel on the 'Employee Details' page


