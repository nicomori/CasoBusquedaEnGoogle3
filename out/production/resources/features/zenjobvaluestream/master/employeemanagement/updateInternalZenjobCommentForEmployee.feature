Feature: Update internal zenjob comment for employee

Background: 
Given I have created a new employee

@tid401
Scenario: Add a comment in 'Internal zenjob comment' section
Given I am on the 'Employee Details' page of an employee
When I click on 'Update Comment' button for 'Internal zenjob comment' section
And I fill in the form with the following data
|Internal zenjob comment| test comment|
And I click on 'Update Comment' button
Then I should be on the 'Employee Details' page
And The comment should be displayed correct in 'Internal zenjob comment' section
|Internal zenjob comment| test comment|

@tid402
Scenario: Edit the comment in 'Internal zenjob comment' section
Given I am on the 'Employee Details' page of an employee
When I click on 'Update Comment' button for 'Internal zenjob comment' section
And I fill in the form with the following data
|Internal zenjob comment| test comment: add|
And I click on 'Update Comment' button
And I click on 'Update Comment' button for 'Internal zenjob comment' section
And I fill in the form with the following data
|Internal zenjob comment| test comment: update|
And I click on 'Update Comment' button
Then The comment should be displayed correct in 'Internal zenjob comment' section
|Internal zenjob comment| test comment: update|

@tid403
Scenario: Delete the comment in 'Internal Zenjob comment'
Given I am on the 'Employee Details' page of an employee
When I click on 'Update Comment' button for 'Internal zenjob comment' section
And I fill in the form with the following data
|Internal zenjob comment| none|
And I click on 'Update Comment' button
Then I should be on the 'Employee Details' page
And There is no any comment in 'Internal zenjob comment' section


