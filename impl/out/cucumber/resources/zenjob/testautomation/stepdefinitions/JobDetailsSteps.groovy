package zenjob.testautomation.stepdefinitions

import cucumber.api.PendingException
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.orchestration.UIJob
import zenjob.testautomation.pages.LoginPage
import zenjob.testautomation.pages.job.JobCreationPage
import zenjob.testautomation.pages.job.JobDetailsPage
import zenjob.testautomation.pages.job.JobEditPage
import zenjob.testautomation.pages.job.JobOverviewPage


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIJob job = state.job

Given(~/^I have created a job without auto-matching$/) { ->
    LoginPage loginPage = to(LoginPage)
    loginPage.login()

    binding.getVariable('state').job.attachToBrowser(browser)
    binding.getVariable('state').job.orchestrateCreation()

}

And(~/^I click on the 'Edit' button on the 'Job Overview' page$/) {   ->
    page.editButtonForJobClick(job.id)

}

And(~/^I click on the 'Cancel Job' button on the 'Job Details' page$/) {   ->
    withConfirm(true) {page.cancelJobButton.click()}

}

And(~/^I click on the 'Edit Job' button on the 'Job Details' page$/) {   ->
    page.editJobButton.click()

}

And(~/^I update the job data$/) {   ->
    at(JobEditPage)
    page.zendeskOrderKey = "1000"
    page.selectCompany("Apple")
    page.selectLocation("apple (B)")
    page.selectDefinition("Test")
}

And(~/^Data should be updated$/) {   ->
    at(JobDetailsPage)
    assert page.company.text() == "Apple"
    assert page.location.text() == "apple"
    assert page.jobDefinition.text() == "Test"
}

When(~/^I go to the 'Job Overview' page$/) {   ->
    page.jobOverviewClick()
    assert at(JobOverviewPage)
}


And(~/^I click on 'OK' in the pop up$/) {  ->
    assert  true

}

And(~/^The job should have the status "([^"]*)"$/) { String status  ->
    assert  page.jobStatus.text()== status

}

And(~/^'Job Details' should have only 'Duplicate Job' button$/) {   ->
    assert page.elementExists(page.duplicateJobButton)
    assert !page.elementExists(page.editJobButton)
    assert !page.elementExists(page.cancelJobButton)
}
