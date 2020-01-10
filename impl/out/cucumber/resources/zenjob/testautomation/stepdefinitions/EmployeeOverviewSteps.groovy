package zenjob.testautomation.stepdefinitions

import cucumber.api.DataTable
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.DashBoardPage
import zenjob.testautomation.pages.employee.EmployeeOverviewPage
import geb.Page
import zenjob.testautomation.pages.OpsDashboardPage
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

// Scenario: Filter by - First Name

Given(~/^i am on the employee overview page$/) { ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    assert at(EmployeeOverviewPage)
    employeeOverviewPage.ClickEmployeeOverviewButton()
}

When(~/^I enter data in FIRST NAME filter$/) { DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}

And(~/^I click on APPLY FILTER button$/) {  ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    employeeOverviewPage.clickApplyFilterButton()

}

Then(~/^employee data should be displayed only with the mentioned FIRST NAME$/) {DataTable dataTable->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    Map<String, String> data = dataTable.asMap(String, String)
    employeeOverviewPage.checkFiltration(data)
}

//Scenario: Filter by - Last Name

When(~/^I enter data in LAST NAME filter$/) {DataTable dataTable ->
    // Write code here that turns the phrase above into concrete actions
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}
Then(~/^employee data should be displayed only with the mentioned LAST NAME$/) {DataTable dataTable ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    Map<String, String> data = dataTable.asMap(String, String)
    employeeOverviewPage.checkFiltration(data)
}

//Scenario: Filter by - Email

When(~/^I enter data in EMAIL filter$/) {DataTable dataTable ->
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}
Then(~/^employee data should be displayed only with the mentioned EMAIL$/) { DataTable dataTable ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    Map<String, String> data = dataTable.asMap(String, String)
    employeeOverviewPage.checkFiltration(data)
}

//Scenario: Filter by - State

When(~/^I select data in STATE filter$/) { DataTable dataTable ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    Map<String, String> data = dataTable.asMap(String, String)
    employeeOverviewPage.selectstate(data)
}

Then(~/^employee data should be displayed only with the mentioned STATE$/) { DataTable dataTable ->
    EmployeeOverviewPage employeeOverviewPage = at EmployeeOverviewPage
    Map<String, String> data = dataTable.asMap(String, String)
    employeeOverviewPage.DetailsFiltrationCheck(data)
}

