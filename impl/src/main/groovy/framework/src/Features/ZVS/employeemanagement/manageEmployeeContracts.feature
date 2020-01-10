Feature: Manage employee contracts

Background:
  Given I select the browser 'chrome'
Given I have created a new employee

@tid376
Scenario: Successfully add a contract with a past date that is not active anymore

Given I am on the 'Employee Details' page of an employee
And There are no inactive contracts for that employee
When I add a new contract for that employee with start date "@{A_YEAR_AGO}"
Then The employee contract should be in signed state under inactive contracts

@tid377
Scenario: Successfully add a contract with a past date that is still active
Given I am on the 'Employee Details' page of an employee
And There are no inactive contracts for that employee
And There is no active contract for that employee
When I add a new contract for that employee with start date "@{HALF_A_YEAR_AGO}"
Then The employee contract should be in signed state under active contracts

@tid378
Scenario: Successfully add a contract with a present date
Given I am on the 'Employee Details' page of an employee
And There is no active contract for that employee
When I add a new contract for that employee with start date "@{NOW}"
Then The employee contract should be in signed state under active contracts

@tid379
Scenario: Successfully add a contract with a future date
Given I am on the 'Employee Details' page of an employee
When I add a new contract for that employee with start date "@{HALF_A_YEAR_FROM_NOW}"
Then The employee contract should be in signed state under scheduled contracts

