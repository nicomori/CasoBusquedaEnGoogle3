package zenjob.testautomation.stepdefinitions.android

import android.activities.HomeActivity
import android.activities.IntroActivity
import android.activities.LoginActivity


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on welcome page$/) { ->
    waitFor{at IntroActivity}
}

When(~/^I click on I already have a login$/) { ->
    IntroActivity page= at IntroActivity
    page.clickLogin()
    waitFor{at LoginActivity}
}

When(~/^I insert my login and click on login$/) { ->
    LoginActivity page= at LoginActivity
    page.setLogin('pascal.vanhuffelen+000019@zenjob.com',"123456")
    page.clickLoginButton()
}

Then(~/^I should enter the app$/) { ->
    sleep(5000)
    waitFor{at HomeActivity}
}

When(~/^I dismiss the promotion message$/) { ->
    waitFor{at LoginActivity}//connection wait
    LoginActivity page= at LoginActivity
    page.clickBackArrow()
}