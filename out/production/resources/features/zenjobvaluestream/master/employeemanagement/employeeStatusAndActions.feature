Feature: Employee status and actions

Background: 
Given I have created a new employee

@tid737
Scenario: Edit 'Status and Actions' section in employee details page
Given  I am on the 'Employee Details' page of an employee
When I click on <Button> 
And I select 'OK' from popup
And <Action> should happen
Then <Status> should be changed and button text be renemed to <New Button Text>
| Unmark as activated | User should become inactive | NO | Mark as activated |
| Mark as deleted | User should be deleted | YES | Unmark as deleted |
| Mark as blacklisted | User should blaklisted | YES | Unmark as blacklisted |
| Mark as test account | User should have Test account | YES | Unmark as test account |
| Unmark as available | User should be not available for matching | NO | Mark as available |
| First job call received | User should marked for job received | YES | First job call not yet received |

@tid739
Scenario: Set an Employee to onboard before - with only Immatrikulation
Given I am on the 'Employee Details' page of an employee
And I have an existing immatrikulation and no active contract
When I click on 'Onboarded before' button
And I click on 'Mark Employee as Onboarded' in the pop up 
Then I should be directed back to the 'Employee Overview' page
And I should see the error message "Employee cannot be onboarded. No active contract found."

@tid740
Scenario: Set an Employee to onboarded before - with only Contract
Given I am on the 'Employee Details' page of an employee
And I have an existing contract and no immatrikulation
When I click on 'Onboarded before' button
And I click on 'Mark Employee as Onboarded' in the pop up 
Then I should be directed back to the 'Employee Overview' page
And I should see the error message "Employee employee.id could not be Onboarded: No active Immatriculation found"

@tid738
Scenario: Set a Not-EU  Employee to onboarded before - with Immatrikulation and Contract and without Working Permit
Given I am on the 'Employee Details' page of an employee
And I edit 'About and Preferences' section on the 'Employee Details' page
And I fill in the form with the following data
|Employment City| Berlin|
And I click on 'Update Employee' button
And I edit 'Payment Details' section on the 'Employee Details' page
And I fill in the form with the following data
|employmentStatus | Auszubildender/in |
|employmentNationality | China |
And I submit the form
And I am on the 'Employee Details' page of an employee
And I have an existing contact and immatrikulation
When I click on 'Onboarded before' button
And I click on 'Mark Employee as Onboarded' in the pop up 
Then I should be directed back to the 'Employee Overview' page
And I should see the error message "Employee employee.id could not be Onboarded: No active working permit found"

@tid910
Scenario: Set a Not-EU Employee to onboarded before - with Immatrikulation, Contract and Working permit
Given I am on the 'Employee Details' page of an employee
And I edit 'About and Preferences' section on the 'Employee Details' page
And I fill in the form with the following data
    |Employment City| Berlin|
And I click on 'Update Employee' button
And I edit 'Payment Details' section on the 'Employee Details' page
And I fill in the form with the following data
    |employmentStatus | Auszubildender/in |
    |employmentNationality | China |
And I submit the form
And I am on the 'Employee Details' page of an employee
And I have an existing contact, immatrikulation and working permit
When I click on 'Onboarded before' button
And I click on 'Mark Employee as Onboarded' in the pop up 
Then I should be directed back to the 'Employee Overview' page
And The user should be onboarded

