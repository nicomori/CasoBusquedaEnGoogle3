Feature: Create a new company

Background:
  Given I select the browser 'chrome'
Given I log into ops dashboard with valid login credentials 
And I click on "Company Management" module
And I click on the 'Create Company' link

@tid734
Scenario: Create new company - without mandatory data
Given I am on the create new company page
When I fill in the mandatory fields with the following data
| name |  |
| stream |  |
| profileName|  |
And I submit the form
Then I should see error messages in alert

@tid735
Scenario: Create new company - with mandatory data
Given I am on the 'Create New Company' page
When I fill in the mandatory fields with the following data
| name | compname |
| stream | 3 |
| urlKey | compnamekey |
| profileName | testprofilename+@{RANDOM_INTEGER} |
| profileDescription| testprofiledescription+@{RANDOM_INTEGER} |
And I submit the form 
Then I am redirected to 'Company Overview' page
And I can see the created company on the 'Company Overview' page
| company | compname |

