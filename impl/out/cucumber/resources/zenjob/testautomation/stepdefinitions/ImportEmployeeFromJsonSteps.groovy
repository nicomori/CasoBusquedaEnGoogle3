package zenjob.testautomation.stepdefinitions

import cucumber.api.PendingException
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.employee.EmployeeDetailsPage
import zenjob.testautomation.pages.employee.EmployeeImportPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee


When(~/^I click on 'Import Employee Data' button$/) { ->
    page.importEmployeeData()
}
And(~/^I am on the 'Import Data' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    at EmployeeImportPage
}
And(~/^I fill in valid employee data in correct format$/) { ->
    page.fillFieldsWithNewEmail(employee.email)
}
And(~/^I click on 'Import from Json' button$/) { ->
    page.submit()
}

Then(~/^All imported data should be displayed in respective panels$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.checkFieldsFromJson(employee.email)
}
And(~/^I fill in the form with invalid employee data$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.fillFieldsMailIsNotMatched()
}

