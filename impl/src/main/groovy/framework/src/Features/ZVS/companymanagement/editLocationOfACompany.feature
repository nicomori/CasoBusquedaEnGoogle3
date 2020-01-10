Feature: Edit location of a company

Background:
  Given I select the browser 'chrome'
Given I log into ops dashboard with valid login credentials
And I click on "Company Management" module
And I click on the 'Create Company' link

@tid870
Scenario: Add a new location - mandatory data
Given I am on the 'Company Overview' page
And I click 'details' button
When I click on 'Add' button on the 'Location' section of company
And I fill in the mandatory fields with the following data
| companyLocationName | companylocation|
| locationDisplayName | displaylocation |
| businessRegion | BERLIN |
| street | STREET |
| streetNumber | 1 |
| postalCode | 10587 |
| city | city@(RANDOM_INTEGER) |
| district | Mitte |
| locationname | Alexanderplatz, Berlin, Germany  |
And I submit the form
Then I should be redirected to the location update page
Then I click on 'company name'
And I should see the 'location name', 'Edit' and 'Show' buttons in the 'Location' section of company
| locationname | companylocation|

@tid871
Scenario: Add a new location - no mandatory data
Given I am on the 'Company Overview' page
And I click 'details' button
When I click on 'Add' button on the 'Location' section of company
And I fill in the mandatory fields with the following data
| companyLocationName | |
| locationDisplayName |  |
| businessRegion |  |
| street |  |
| streetNumber |  |
| postalCode |  |
| city |  |
| district |  |
| locationname |  |
And I submit the form
Then I should see error messages in location alert
|Property locationLongitude must be a valid number| |
|"companyLocationName" may not be empty.|           |
|"postalCode" may not be empty.|                    |
|"locationSearch" may not be empty.|                |
|Property locationLatitude must be a valid number|  |
|"Talent display name" may not be empty|  |
|"street" may not be empty.|  |
|"streetNumber" may not be empty.|  |
|"city" may not be empty.|  |
|"district" may not be empty.|  |
|"businessRegion" may not be empty.|  |

@tid872
Scenario: Add a new location - all data
Given I am on the 'Company Overview' page
And I click 'details' button
When I click on 'Add' button on the 'Location' section of company
And I fill in the mandatory fields with the following data
| companyLocationName | companylocation|
| locationDisplayName | displaylocation |
| businessRegion | BERLIN |
| area | area |
| zone | zone |
| street | STREET |
| streetNumber | 1 |
| supplementary | supplementary |
| postalCode | 10587 |
| city | city@(RANDOM_INTEGER) |
| district | Mitte |
| state | brandenburg |
| country | germany |
| locationname | Alexanderplatz, Berlin, Germany |
And I submit the form 
Then I should be redirected to the location update page 
Then I click on 'company name'
And I should see the 'location name', 'Edit' and 'Show' buttons in the 'Location' section of company
| locationname | companylocation|

