package framework.src.Steps

import cucumber.api.PendingException
import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.ZVS.OpsAppDashboardPage
import framework.src.Pages.ZVS.OpsAppLoginPage
import framework.src.Pages.ZVS.job.JobDetailsPage
import framework.src.Pages.ZVS.job.JobOverviewPage
import framework.src.Util.BrowserSelector
import framework.src.Util.TestConstants2
import geb.Page


this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

def talentName = TestConstants2.UsersTalent.TALENT_USER_LASTNAME+", "+TestConstants2.UsersTalent.TALENT_USER_FIRSTNAME

Given(~/^I select the browser 'chrome'$/) { ->
    BrowserSelector browserSelector = new BrowserSelector(binding)
    browserSelector.selectABrowser()
}

Then(~/^access to the 'https:\/\/company-release\.zenjob\.org\/auto'$/) { ->
    browser.go("https://company-release.zenjob.org/auto/login")
}

Given(~/^I select the browser '<browserName>'$/) { ->
    BrowserSelector browserSelector = new BrowserSelector(binding)
    browserSelector.selectABrowser()
}

Then(~/^access to the 'https:\/\/staging-main\.zenjob\.org'$/) { ->
    browser.go("https://staging-main.zenjob.org")
}

Then(~/^access to the '<secondUrl>'$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

Then(~/^access to the '<url>'$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

And(~/^I click on "([^"]*)" module$/) { String moduleName ->
    Page dashBoardPage = at OpsAppDashboardPage
    dashBoardPage.ClickOnModule(moduleName)
}

And(~/^I go to the Job Details for the job$/) { ->
    Page overviewPage = at JobOverviewPage
    println("should be order key")
    println(thisIsTheVariableJuan)

    overviewPage.findJobByOderKey2(thisIsTheVariableJuan)
    println("should be job key")
    overviewPage.matchingButtonForAJobClick3(thisIsTheVariableJuan2)
}

Then(~/^I should see the user in the 'Applied' tab$/) { ->
    page.tabClick('Applied')
    page.checkTalentInAppliedTab(talentName)
}

Then(~/^I should see the user in the 'Applied' tab for E2E cases$/) { ->
    //at(JobDetailsPage)
    page.tabClick('Applied')
    page.checkTalentInAppliedTab_ToImproveTheQualityOfTheCode(talentName)
}

When(~/^I book this talent$/) { ->
    //at(JobDetailsPage)
    page.bookTalent(talentName)
}

When(~/^I book this talent for E2E cases$/) { ->
    at(JobDetailsPage)
    page.bookTalentFor_E2ECases(talentName)
}

When(~/^This talent should be in 'Booked' tab$/) { ->
    //at(JobDetailsPage)
    page.tabClick('Booked')

}

Then(~/^I should see the user in the 'Applied' tab for E2E$/) { ->
    JobDetailsPage jobDetailsPage = at(JobDetailsPage)

    println('Making click in the tab applied')
    jobDetailsPage.tabClickInTheTabApplied()

}
