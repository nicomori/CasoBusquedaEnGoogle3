Feature: Filter employees in the employee overview page

Background:
  Given I select the browser 'chrome'
Given I have created a new employee

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

@tid854
Scenario: Filter by - Email
Given i am on the employee overview page
When I enter data in EMAIL filter
| email | tester |
And I click on APPLY FILTER button
Then employee data should be displayed only with the mentioned EMAIL
| email | tester |
