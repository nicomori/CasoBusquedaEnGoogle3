package framework.src.Steps.ZVS

import cucumber.api.DataTable
import framework.src.Pages.ZVS.employee.EmployeeAboutAndPreferencesPage
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


And(~/^I edit 'About and Preferences' section on the 'Employee Details' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.editAboutAndPreferences()
    assert at(EmployeeAboutAndPreferencesPage)
}

And(~/^I click on 'Update Employee' button$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.submit()
    at(EmployeeDetailsPage)
}

And(~/^'About and Preferences' data should be correct$/) { DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkFields(data)
}

When(~/^I don't fill in the form with any data$/) { ->
    assert true
}