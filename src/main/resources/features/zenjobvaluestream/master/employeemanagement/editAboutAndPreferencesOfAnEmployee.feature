Feature: Edit about and preferences of an employee

Background: 
Given I have created a new employee

@tid382
Scenario: Successfully edit all fields
    Given I am on the 'Employee Details' page of an employee
    And I edit 'About and Preferences' section on the 'Employee Details' page
    When I fill in the form with the following data
      |Profession | Hauskeeper |
      |Employment City| Berlin|
      |Employment City (if selected other)|other|
      |About Me | sehr freundlich, zuverlässig, würde seinen Job sicher sehr gut machen, motiviert |
      |Job Categories| Helper|
      |Tags| tag1, tag2|
    And I click on 'Update Employee' button
    Then I should not see any error messages
    And I should be on the 'Employee Details' page
    And 'About and Preferences' data should be correct
    |Profession | Hauskeeper |
    |Employment City| Berlin|
    |About Me | sehr freundlich, zuverlässig, würde seinen Job sicher sehr gut machen, motiviert |
    |Job Categories| Helper|
    |Tags| tag1, tag2|

@tid390
Scenario: Successfully edit cities fields when employment city = andere
Given I am on the 'Employee Details' page of an employee
And I edit 'About and Preferences' section on the 'Employee Details' page
When I fill in the form with the following data
    |Employment City| andere|
    |Employment City (if selected other)|otherCity|
And I click on 'Update Employee' button
Then I should not see any error messages
And I should be on the 'Employee Details' page
And 'About and Preferences' data should be correct
    |Employment City| andere (otherCity)|

@tid384
Scenario: All fields are empty
Given I am on the 'Employee Details' page of an employee
And I edit 'About and Preferences' section on the 'Employee Details' page
When I don't fill in the form with any data
And I click on 'Update Employee' button
Then I should not see any error messages
And I should be on the 'Employee Details' page

