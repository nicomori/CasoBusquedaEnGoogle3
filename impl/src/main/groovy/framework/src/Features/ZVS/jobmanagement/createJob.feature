Feature: Create Job

Background:
  Given I select the browser 'chrome'
Given I log into ops dashboard with valid login credentials
And I click on "Job Management" module
And I am on the 'Job Overview' page
And I click on the 'Create job' link

@tid1447
Scenario: Create job with only mandatory fields
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And I should see 'Company Details' section with correct data
|Company| Zenjob|
And I should see 'Shift Overview' section with correct data
|Checkin| @{TOMORROW} 09:00|
|Checkout| @{TOMORROW} 15:00|
|Head Count|1|
And I should see 'Job Details' section with correct data
|Zendesk Order Key|12345|
|Region| Berlin|
|Location| Zenjob HQ|
|Job Definition| Atze an der Tür|
And I should see the operator

@tid1448
Scenario: Create job without all mandatory fields
Given I am on the 'Create Job' page
When I don't enter any data
And I submit the form
Then I should see an error message


@tid1449
Scenario: Create job with all fields
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Head Count|1|
|Definition| Atze an der Tür|
|Title| automation create a job|
|Description| data_Description|
|Instructions| data_Instructions|
|Internal comment| data: Internal comment|
|Tags| tag1|
|Exclude Tags| exclude_tag|
|Tag Group 1|tag_tag_group_1|
|Tag Group 2|tag_tag_group_2|
|Tag Group 3|tag_tag_group_3|
|Tag Group 4|tag_tag_group_4|
|Tag Group 5|tag_tag_group_5|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And I should see 'Company Details' section with correct data
|Company| Zenjob|
And I should see 'Shift Overview' section with correct data
|Checkin| @{TOMORROW} 09:00|
|Checkout| @{TOMORROW} 15:00|
|Head Count|1|
And I should see 'Pricing Configuration' section 
And I should see 'Employee Pricing Table' section with default data
And I should see 'Internal Comment' section with correct data
|Internal comment| data: Internal comment|
And I should see 'Company Internal Comment' section
And I should see 'Job Details' section with correct data
|Zendesk Order Key|12345|
|Region| Berlin|
|Location| Zenjob HQ|
|Job Definition| Atze an der Tür|
|Tags| tag1|
|Exclude Tags| exclude_tag|
|Tag Group 1|tag_tag_group_1|
|Tag Group 2|tag_tag_group_2|
|Tag Group 3|tag_tag_group_3|
|Tag Group 4|tag_tag_group_4|
|Tag Group 5|tag_tag_group_5|
|Job Due Date| @{TODAY}|
|Job Due Time|09:00|
|Description| data_Description|
|Instructions| data_Instructions|
And 'Job Details' section should have 'Duplicate Job', 'Edit Job' and 'Cancel Job' button
And I should see the operator

@tid1450
Scenario: Create job without filling Zendesk Orderkey
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key||
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message ""zendeskOrderKey" may not be empty."

@tid1451
Scenario: Create job where Zendesk Orderkey has not a numeric value
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| nonNumeric|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message "Please add just the Zendesk Ticket ID (numbers only)."

 

@tid1452
Scenario: Create job without filling Company, Location and Definition fields
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 23423|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
And I should see the list of error messages
|Property company must be a valid number|
|Property jobLocation must be a valid number|
|Property jobDefinition must be a valid number|


@tid1453
Scenario: Create job without filling Location
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 20000|
|Company| Zenjob|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message "Property jobLocation must be a valid number"

@tid1454
Scenario: Create job without filling Job Definition
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message "Property jobDefinition must be a valid number"


@tid1455
Scenario: Create job with Head Count = 0
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
|Head Count|0|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see an error message


 

@tid1456
Scenario: Create job without Head Count
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
|Head Count||
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message ""headCount" may not be empty."

@tid1457
Scenario: Create job without filling Title
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message ""jobTitle" may not be empty."

@tid1458
Scenario: Create job without Job Shifts
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I submit the form
Then I should see the error message "Job must have at least one shift defined."

@tid1459
Scenario: Create job with Shift but without filled parameters
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I submit the form
Then I should see the list of error messages
|"startDate" may not be empty.|
|"startTime" may not be empty.|
|"endTime" may not be empty.|


@tid1460
Scenario: Create job without selecting Shift  Start Date in Job Shift
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message ""startDate" may not be empty."


@tid1461
Scenario: Create job without selecting Shift Start Time in Job Shift
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift End Time| 15:00|
And I submit the form
Then I should see the error message ""startTime" may not be empty."


@tid1462
Scenario: Create job without selecting Shift End Time in Job Shift
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
And I submit the form
Then I should see the error message ""endTime" may not be empty."

@tid1463
Scenario: Create job where Shift End Time is greater than Shift Start Date
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 15:00|
|Shift End Time| 10:00|
And I submit the form
Then I should see the error message "Shifts have to begin before they end."

@tid1464
Scenario: Create job with 2 valid Shifts
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I click on the 'Add Shift' button
And I fill in the shift "1" with the following data
|Shift Start Date| @{AFTER_TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And I should see 2 shifts in the 'Shift Overview' section

@tid1465
Scenario: Create job with a valid Shift and an invalid Shift
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I click on the 'Add Shift' button
And I fill in the shift "1" with the following data
|Shift Start Date| @{TOMORROW}|
And I submit the form
Then I should see the list of error messages
|"startTime" may not be empty.|
|"endTime" may not be empty.|

@tid1466
Scenario: Create job with a shift of 6 hours
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And Working hours should be correct
|Working hours| 6h 0min, Pause: 0h 0min, Total: 6h 0min|

@tid1467
Scenario: Create job with a shift of 10 hours
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 19:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And Working hours should be correct
|Working hours|9h 15min, Pause: 0h 45min, Total: 10h 0min|

@tid1468
Scenario: Create job with a shift of 12 hours
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 10:00|
|Shift End Time| 22:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should see an error message

@tid1469
Scenario: Create job with a shift of less than 6 hours
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 13:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And Working hours should be correct
|Working hours| 4h 0min, Pause: 0h 0min, Total: 4h 0min|

@tid1470
Scenario: Create job with a shift of more than 6 hours but less than 10
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 16:30|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And Working hours should be correct
|Working hours|7h 0min, Pause: 0h 30min, Total: 7h 30min|

@tid1471
Scenario: Create job without auto match
Given I am on the 'Create Job' page
When I fill all mandatory fields for a job
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And I should see the 'Start Matching' button at the bottom of the page
And 'Job Details' section should have 'Duplicate Job', 'Edit Job' and 'Cancel Job' button

@tid1472
Scenario: Create a job with auto-matching
Given I am on the 'Create Job' page
When I fill all mandatory fields for a job
And I check 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And 'Job Details' section should have 'Duplicate' and 'Cancel Job' button
And I should see the Scheduled Matching Run at the bottom of the page
And I stop automatching

@tid1473
Scenario: Cancel job creating
Given I am on the 'Create Job' page
When I click on the 'Cancel' button
Then I should be on the 'Job Overview' page

@tid1474
Scenario: Create a duplicate job
Given I am on the 'Create Job' page
When I fill in the form with the following data
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ|
|Definition| Atze an der Tür|
|Title| automation create a job|
And I click on the 'Add Shift' button
And I fill in the shift "0" with the following data
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
And I should be on the 'Job Details' page
And I click on the 'Duplicate Job' button in the 'Job Details' section
And All fields should be prefilled from previous job
|Zendesk Order Key| 12345|
|Company| Zenjob|
|Location| Zenjob HQ (B)|
|Definition| Atze an der Tür|
|Title| automation create a job|
|Shift Start Date| @{TOMORROW}|
|Shift Start Time| 09:00|
|Shift End Time| 15:00|
And I uncheck 'Auto-Matching' check box 
And I submit the form
Then I should be on the 'Job Details' page
And I should see 'Company Details' section with correct data
|Company| Zenjob|
And I should see 'Shift Overview' section with correct data
|Checkin| @{TOMORROW} 09:00|
|Checkout| @{TOMORROW} 15:00|
|Head Count|1|
And I should see 'Job Details' section with correct data
|Zendesk Order Key|12345|
|Region| Berlin|
|Location| Zenjob HQ|
|Job Definition| Atze an der Tür|

