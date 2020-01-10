Feature: Edit personnel master data of an employee

Background:
  Given I select the browser 'chrome'
Given I have created a new employee

@tid391
Scenario: Filling all fields of Personnel master data form


Given I am on the 'Employee Details' page of an employee
And I edit 'Payment Details' section on the 'Employee Details' page
When I fill in the form with the following data
| employmentStatus | Auszubildender/in |
| university | @{RANDOM_ALPHANUMERIC} |
| studySubject | @{RANDOM_ALPHANUMERIC} |
| multipleEmploymentStatus | Nein |
| employmentStatusUnemployed | True |
| employmentStatusBenefits | False |
| birthLocation | @{RANDOM_ALPHANUMERIC} |
| countryOfBirth | Belgien |
| birthName | @{RANDOM_ALPHANUMERIC} |
| employmentNationality | Deutschland |
| maritalStatus | Geschieden |
| children | @{RANDOM_INTEGER} |
| hasCar | No |
| driversLicenses | B |
| clothingSize | M |
| taxIdentNumber | @{RANDOM_INTEGER} |
| taxClassification | 6 |
| denomination | @{RANDOM_ALPHANUMERIC} |
| socialInsuranceNumber | @{RANDOM_INTEGER} |
| healthInsuranceName | @{RANDOM_ALPHANUMERIC} |
| healthInsuranceNumber | @{RANDOM_INTEGER} |
And I submit the form
Then I am on the 'Employee Details' page of an employee

@tid393
Scenario: Fill invalid children number of Personnel master data form
Given I am on the 'Employee Details' page of an employee
And I edit 'Payment Details' section on the 'Employee Details' page
When I fill in the form with the following data
| children | @{RANDOM_ALPHANUMERIC} |
And I submit the form
Then I should see the error message "Property children must be a valid number"

@tid394
Scenario: Fill Payment Details in Personnel Master Data form
Given I am on the 'Employee Details' page of an employee
And I edit 'Payment Details' section on the 'Employee Details' page
When I fill in the form with the following data
| iban | DE89 3704 0044 0532 0130 00 |
| bic | AABSDE31 |
And I submit the form
Then I am on the 'Employee Details' page of an employee


@tid395
Scenario: Fields validation warning in Personnel Master Data form
Given I am on the 'Employee Details' page of an employee
  And I edit 'Payment Details' section on the 'Employee Details' page
When I fill in the form with the following data
  | taxIdentNumber | @{RANDOM_ALPHANUMERIC} |
  | socialInsuranceNumber | @{RANDOM_ALPHANUMERIC} |
  | iban | @{RANDOM_ALPHANUMERIC} |
  | bic | @{RANDOM_ALPHANUMERIC} |
And I submit the form
Then I should see warning for those fields

