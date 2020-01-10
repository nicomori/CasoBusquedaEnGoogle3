Feature: Update primary or other jobs for an employee

Background:
  Given I select the browser 'chrome'
Given I have created a new employee

@tid411
Scenario: Update Job Comment
Given I am on the 'Employee Details' page of an employee
When I click on update comment button in Primary or other jobs section
And I navigate to Employee comment update page
And I entered or update comment with below data
  | comment | yes 30 years |
And I click on Update comment button
Then I navigate to Employee details page
Then Comment should be displayed under Primary or other jobs section
  | Primary / other jobs | yes 30 years |

