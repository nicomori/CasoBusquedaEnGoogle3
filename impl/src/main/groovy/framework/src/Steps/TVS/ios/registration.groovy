package framework.src.Steps.TVS.ios

import framework.src.Pages.TVS.old_pepe.ios.views.IntroViewController
import framework.src.Pages.TVS.old_pepe.ios.views.OnboardingView


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

When(~/^I click on new user$/) { ->
    IntroViewController page= at IntroViewController
    page.register.click()
    waitFor{at OnboardingView}
}

When(~/^I am over eighteen$/) {  ->
    OnboardingView page= at OnboardingView
    page.waitFor{at OnboardingView}
    println("ios")
    page.overEighteen.click()
    page.clickContinue()
}

When(~/^I am a Student$/) { ->
    waitFor{at OnboardingView}
    OnboardingView page= at OnboardingView
    sleep(600)
    page.student.click()
    page.clickContinue()
}

When(~/^I am German$/) { ->
    waitFor{at OnboardingView}
    OnboardingView page= at OnboardingView
    page.clickListNation()
    page.setNationality("Deutschland")//4 germany - index in the showed list
    page.clickContinue()
}

When(~/^I am from Berlin$/) { ->
    OnboardingView page= at OnboardingView
    page.clickListCity()
    page.setCity("Berlin")//1 berlin - index in the showed list
    page.clickContinue()
}

When(~/^My name is Roberto Roboto$/) { ->
    OnboardingView page= at OnboardingView
    page.setNames("Roberto","Roboto")
    page.clickContinue()
}

When(~/^My email is x$/) { ->
    OnboardingView page= at OnboardingView
    page.setLogin("Appium-ios-001@zenjob.com","123456")
}

When(~/^I check terms$/) { ->
    OnboardingView page= at OnboardingView
    page.checkTerms()
}

When(~/^I press Continue$/) { ->
    OnboardingView page= at OnboardingView
    page.clickContinue()
}

Then(~/^I should see email verification$/) { ->
    sleep(5000)
    waitFor{at OnboardingView}//still onboardingview
    //TODO : check if error message b/c same view => will be ok everytime
}
