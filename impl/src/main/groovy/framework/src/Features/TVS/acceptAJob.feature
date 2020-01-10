Feature: Accept a job
@android1
Scenario: Accept a job

#  Given I am accessing ios
  Given I am accessing android
  And I am in the welcome page and I press the button to go to the login page
  And In the login page I make a success login
  Then I am in the Dashboard page and I accept the first job of the list
  Then In the job details page, I make an scroll down and I accept the job
  Then In the signature page I make a signature and I press the button accept the job

