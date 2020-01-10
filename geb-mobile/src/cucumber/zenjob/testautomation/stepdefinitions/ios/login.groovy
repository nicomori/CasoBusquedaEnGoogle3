package zenjob.testautomation.stepdefinitions.ios

import ios.views.IntroViewController
import ios.views.LoginView


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on welcome page$/) { ->
    waitFor{at IntroViewController}
}

When(~/^I click on I already have a login$/) { ->
    IntroViewController page= at IntroViewController
    page.login.click()
    waitFor{at LoginView}
}

When(~/^I insert my login and click on login$/) { ->
    LoginView page= at LoginView
    page.setLogin('pascal.vanhuffelen+000019@zenjob.com',"123456")
    page.clickLoginButton()
}

Then(~/^I should enter the app$/) { ->
    sleep(5000)
    println("LOGGED IN")
}

When(~/^I dismiss the promotion message$/) { ->
    //TODO : dismiss notification + promotion message
}