Feature: localtest
@ios1 @wip @android
Scenario: login into app

#  Given I am accessing ios
  Given I am accessing android
  #Given I am in the android app localtest

#  Given I am on welcome page
#When I click on I already have a login
#And I insert my login and click on login
#  And I dismiss the promotion message
#Then I should enter the app
#  And I click on the first offer
#  And I click on the apply button
#  And I sign the screen
#  And I send the signed contract


  And I am in the welcome page and I press the button to go to the login page
  And In the login page I make a success login
  Then I am in the Dashboard page and I accept the first job of the list
  Then In the job details page, I make an scroll down and I accept the job
  Then In the signature page I make a signature and I press the button accept the job

