package zenjob.testautomation.stepdefinitions.android

import android.activities.EmailVerificationActivity
import android.activities.IntroActivity
import android.activities.RegistrationActivity


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I click on new user$/) { ->
    IntroActivity page= at IntroActivity
    page.clickRegister()
    waitFor{at RegistrationActivity}
}

When(~/^I am over eighteen$/) {  ->
    RegistrationActivity page= at RegistrationActivity
    page.checkEighteen()
    page.clickContinue()
}

When(~/^I am a Student$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.checkStudent()
    page.clickContinue()
}

When(~/^I am German$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    sleep(100)//transition
    page.clickListNation()
    page.setNationality(4)//4 germany - index in the showed list
    page.clickContinue()
}

When(~/^I am from Berlin$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    sleep(100)//transition
    page.clickListCity()
    page.setCity(1)//1 berlin - index in the showed list
    page.clickContinue()
}

When(~/^My name is Roberto Roboto$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    page.setNames("Roberto","Roboto")
    page.clickContinue()
}

When(~/^My email is x$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.setLogin("Appium-droid-001@zenjob.com","123456")
}

When(~/^I check terms$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.checkTerms()
}

When(~/^I press Continue$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.clickContinue()
}

Then(~/^I should see email verification$/) { ->
    sleep(5000)
    waitFor{at EmailVerificationActivity}
}
