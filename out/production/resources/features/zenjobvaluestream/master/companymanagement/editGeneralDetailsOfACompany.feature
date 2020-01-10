Feature: Edit general details of a company

Background: 
Given I log into ops dashboard with valid login credentials
  And I click on "Company Management" module
  And I click on the 'Create Company' link

@tid746
Scenario: Edit general details - mandatory data
And I click 'details' button
When I edit 'General Details' section of company
And I fill in the mandatory fields with the following data
| name | company|
| stream | 1 |
| urlKey | key |
And I submit the form
Then I should be redirected to 'Company Details' page
And 'General Details' should be correct
| name | company|
| stream | 1 |
| urlKey | key |

@tid747
Scenario: Edit general details - no mandatory data
Given I am on the 'Company Overview' page
And I click 'details' button
When I edit 'General Details' section of company
And I fill in the mandatory fields with the following data
| name |  |
And I submit the form
Then I should see error messages in alert

@tid748
Scenario: Edit general details - mandatory data after error message
Given I am on the 'Company Overview' page
And I click 'details' button
When I edit 'General Details' section of company
And I fill in the mandatory fields with the following data
| name |  |
And I submit the form
Then I should see error messages in alert
When I fill in the mandatory fields with the following data
| name | company|
| stream | 1 |
| urlKey | key |
And I submit the form
Then I should be redirected to 'Company Details' page
And 'General Details' should be correct
| name | company|
| stream | 1 |
| urlKey | key |

@tid749
Scenario: Edit general details - all data
Given I am on the 'Company Overview' page
And I click 'details' button
When I edit 'General Details' section of company
And I fill in the mandatory fields with the following data
| name | company |
| stream | 1 |
| urlKey | key |
| billingName | billingname |
| paymentTermAmount | 30 |
| paymentTermUnit | Working Days |
| invoicingType | MANUAL |
| billingNotes | billing |
| registerNumber | number |
| registerNotes | notes |
And I submit the form
Then I should be redirected to 'Company Details' page
And 'General Details' should be correct
| name | company |
| urlKey | key |
| stream | 1 |
| billingName | billingname |
| billingNotes | billing |
| Paymentterm | 30 Working Days|
| invoicingType | MANUAL |
| registerNumber | number |
| registerNotes | notes |


