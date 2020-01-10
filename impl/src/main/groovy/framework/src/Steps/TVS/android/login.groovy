package framework.src.Steps.TVS.android

import framework.src.Pages.TVS.old_pepe.android.activities.HomeActivity
import framework.src.Pages.TVS.old_pepe.android.activities.IntroActivity
import framework.src.Pages.TVS.old_pepe.android.activities.LoginActivity


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on welcome page222$/) { ->
    waitFor{at IntroActivity}
}

When(~/^I click on I already have a login222$/) { ->
    IntroActivity page= at IntroActivity
    page.clickLogin()
    waitFor{at LoginActivity}
}

When(~/^I insert my login and click on login222$/) { ->
    LoginActivity page= at LoginActivity
    page.setLogin('pascal.vanhuffelen+000019@zenjob.com',"123456")
    page.clickLoginButton()
}

Then(~/^I should enter the app22$/) { ->
    sleep(5000)
    waitFor{at HomeActivity}
}

When(~/^I dismiss the promotion message222$/) { ->
    waitFor{at LoginActivity}//connection wait
    LoginActivity page= at LoginActivity
    page.clickBackArrow()
}