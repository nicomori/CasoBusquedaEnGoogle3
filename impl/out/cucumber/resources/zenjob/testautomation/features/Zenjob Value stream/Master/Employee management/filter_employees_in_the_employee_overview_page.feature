Feature: Filter employees in the employee overview page

Background: 
Given I am logged into the ops dashboard
 And i navigate to the "employee" module

@tid852
Scenario: Filter by - First Name
Given i am on the employee overview page
When I enter data in FIRST NAME filter
| firstName | tester |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned FIRST NAME
| firstName | tester |

@tid853
Scenario: Filter by - Last Name
Given i am on the employee overview page
When I enter data in LAST NAME filter
| lastName | tester |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned LAST NAME
| lastName | tester |

@tid855
Scenario: Filter by - State - single
Given i am on the employee overview page
When I select data in STATE filter
| state | ACTIVATED |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned STATE
| state | ACTIVATED |

@tid856
Scenario: Filter by - State - multiple
Given i am on the employee overview page
When I select data in STATE filter
| state | ACTIVATED |
| state | ONBOARDED |
| state | DELETED |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned STATE

@tid857
Scenario: Filter by - State - all
Given i am on the employee overview page
When I select data in STATE filter
| State |  Select All |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned STATE

@tid858
Scenario: Filter by - Tags - one
Given i am on the employee overview page
When I entered data in TAGS filter
| Tags | tester |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned TAGS
| Tags | tester |

@tid859
Scenario: Filter by - Tags - multiple
Given i am on the employee overview page
When I entered data in TAGS filter
| Tags | tester1 |
| Tags | tester2 |
| Tags | tester3 |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned TAGS

@tid860
Scenario: Filter by - multiple
Given i am on the employee overview page
When I entered data in below filters
| firstName | tester |
| email | tester |
| state | ACTIVATED |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned filters

