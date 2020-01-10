package framework.src.Steps.TVS.android

import framework.src.Pages.TVS.old_pepe.android.activities.EmailVerificationActivity
import framework.src.Pages.TVS.old_pepe.android.activities.RegistrationActivity
import framework.src.Pages.TVS.old_pepe.android.activities.IntroActivity


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I click on new user22$/) { ->
    IntroActivity page= at IntroActivity
    page.clickRegister()
    waitFor{at RegistrationActivity}
}

When(~/^I am over eighteen22$/) {  ->
    RegistrationActivity page= at RegistrationActivity
    page.checkEighteen()
    page.clickContinue()
}

When(~/^I am a Studen22t$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.checkStudent()
    page.clickContinue()
}

When(~/^I am Germa22n$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    sleep(100)//transition
    page.clickListNation()
    page.setNationality(4)//4 germany - index in the showed list
    page.clickContinue()
}

When(~/^I am from Berl22in$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    sleep(100)//transition
    page.clickListCity()
    page.setCity(1)//1 berlin - index in the showed list
    page.clickContinue()
}

When(~/^My name is Robert22o Roboto$/) { ->
    waitFor{at RegistrationActivity}
    RegistrationActivity page= at RegistrationActivity
    page.setNames("Roberto","Roboto")
    page.clickContinue()
}

When(~/^My email is22 x$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.setLogin("Appium-droid-001@zenjob.com","123456")
}

When(~/^I check t22erms$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.checkTerms()
}

When(~/^I press Continu22e$/) { ->
    RegistrationActivity page= at RegistrationActivity
    page.clickContinue()
}

Then(~/^I should see email ver22ification$/) { ->
    sleep(5000)
    waitFor{at EmailVerificationActivity}
}
