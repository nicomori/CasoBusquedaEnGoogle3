package zenjob.testautomation.stepdefinitions

import activities.HomeActivity
import activities.IntroActivity
import activities.LoginActivity


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on welcome page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException()
    println("WELCOME PAGE STEP 1")
    waitFor{at IntroActivity}
}

When(~/^I click on I already have a login$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException()
    IntroActivity page= at IntroActivity
    page.clickLogin()
    waitFor{at LoginActivity}
}

When(~/^I insert my login and click on login$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException()
    LoginActivity page= at LoginActivity
    page.setLogin('pascal.vanhuffelen+000019@zenjob.com',"123456")
    page.clickLoginButton()
}

Then(~/^I should enter the app$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //throw new PendingException()
    sleep(5000)
    waitFor{at HomeActivity}
}