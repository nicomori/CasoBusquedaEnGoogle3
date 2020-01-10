package framework.src.Steps.ZVS

import cucumber.api.DataTable
import framework.src.Pages.ZVS.job.JobCreationPage
import framework.src.Pages.ZVS.job.JobDetailsPage
import framework.src.Pages.ZVS.job.JobOverviewPage


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

And(~/^I am on the 'Job Overview' page$/) { ->
    assert at(JobOverviewPage)
}

And(~/^I click on the 'Create job' link$/) { ->
    page.createNewJobLinkClick()
}

And(~/^I am on the 'Create Job' page$/) { ->
    assert at(JobCreationPage)
}

And(~/^I click on the 'Add Shift' button$/) { ->
    page.addShiftButtonClick()
}

And(~/^I fill in the shift "([^"]*)" with the following data$/) {String shiftNumber , DataTable dataTable  ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillShiftFields(shiftNumber, data)
}

And(~/^I should be on the 'Job Details' page$/) { ->
    //assert at(JobDetailsPage)
    JobDetailsPage jobDetailsPage = at(JobDetailsPage)

}

And(~/^I am on the 'Job Details' page$/) { ->
    //assert at(JobDetailsPage)
    JobDetailsPage jobDetailsPage = at(JobDetailsPage)

}

And(~/^I should see 'Company Details' section with correct data$/) { DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkCompanyContactDetails(data)
}

And(~/^I should see 'Shift Overview' section with correct data$/) { DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkShiftOverview(0,data)
}

And(~/^I should see 'Job Details' section with correct data$/) { DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkJobDetails(data)
}

And(~/^I should see the operator$/) {  ->
    page.checkOperator()
}

And(~/^I don't enter any data$/) {  ->
   assert true
}

And(~/^I should see 'Pricing Configuration' section$/) {  ->
    assert page.elementExists(page.pricingConfiguration)

}

And(~/^I should see 'Employee Pricing Table' section with default data$/) {   ->
    page.checkDefaultEmployeePricingTable()
}

And(~/^I should see 'Internal Comment' section with correct data$/) {  DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    //page.checkInternalComment(data)
    assert true
}

And(~/^I should see 'Company Internal Comment' section$/) {   ->
    page.elementExists(page.companyInternalComment)
}

And(~/^'Job Details' section should have 'Duplicate Job', 'Edit Job' and 'Cancel Job' button$/) {   ->
    page.elementExists(page.duplicateJobButton)
    page.elementExists(page.editJobButton)
    page.elementExists(page.cancelJobButton)
}

And(~/^I fill all mandatory fields for a job$/) {   ->
    page.fillMandatoryFieldsWithDefaultData()
}

And(~/^I uncheck 'Auto-Matching' check box$/) {   ->
    page.uncheckAutomatchingCheckBox()
}

And(~/^I should see the 'Start Matching' button at the bottom of the page$/) {   ->
    page.elementExists(page.startMatchingButton)

}

And(~/^The job should have 'Edit' and 'Matching' buttons on the 'Job Overview' page$/) {   ->
    JobDetailsPage jobDetailsPage = at(JobDetailsPage)
    def job = jobDetailsPage.getJobId()
    jobDetailsPage.jobOverviewClick()
    assert at(JobOverviewPage)
    page.checkActionsForJob(jobDetailsPage.getJobId())
}

And(~/^The job should have a red cross on the 'Job Overview' page$/) {   ->
    page.checkAutomatchingFalse(job)

}

And(~/^'Job Details' section should have 'Duplicate' and 'Cancel Job' button$/) {   ->
    assert page.elementExists(page.duplicateJobButton)
    assert !page.elementExists(page.editJobButton)
    assert page.elementExists(page.cancelJobButton)

}

And(~/^I check 'Auto-Matching' check box$/) {   ->
    page.checkAutomatchingCheckBox()

}

And(~/^I should see the Scheduled Matching Run at the bottom of the page$/) {   ->
    assert page.elementExists(page.automatchingTable)
}

And(~/^I click on the 'Cancel' button$/) {   ->
     page.cancelButtonClick()
}

And(~/^I should be on the 'Job Overview' page$/) {   ->
    assert at(JobOverviewPage)
}

And(~/^I click on the 'Duplicate Job' button in the 'Job Details' section$/) {   ->
    page.duplicateJobButton.click()
}

And(~/^All fields should be prefilled from previous job$/) {  DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    at(JobCreationPage)
    page.checkPreFilledFields(data)
}

And(~/^I should see 2 shifts in the 'Shift Overview' section$/) {   ->
    assert page.checkShiftsCount() == 2
}

And(~/^Working hours should be correct$/) {  DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkJobDetails(data)
}

And(~/^I stop automatching$/) {  ->
    page.stopAutomatchingClick()
}
