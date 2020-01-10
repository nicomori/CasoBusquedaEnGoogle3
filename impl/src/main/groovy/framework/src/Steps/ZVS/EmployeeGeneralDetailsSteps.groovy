package framework.src.Steps.ZVS

import cucumber.api.DataTable

import framework.src.Orchestration.UIEmployee
import framework.src.ApplicationState
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.EmployeeGeneralDetailsPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

When(~/^I click on the 'Edit' button for 'General Details' section$/) { ->
    page.editGeneralDetails()
    assert at (EmployeeGeneralDetailsPage)
}

And(~/^'General Details' section should have correct data$/) { DataTable dataTable ->
    assert at(EmployeeDetailsPage)
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkFields(data)
}

