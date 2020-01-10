package zenjob.testautomation.stepdefinitions

import cucumber.api.DataTable
import geb.Page
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.pages.DashBoardPage
import zenjob.testautomation.pages.company.CompanyCreatePage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)


import zenjob.testautomation.pages.LoginPage
import zenjob.testautomation.pages.employee.EmployeeDetailsPage
import zenjob.testautomation.pages.employee.EmployeeOverviewPage

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee


And(~/^I click on "([^"]*)" module$/) { String moduleName ->

    Page dashBoardPage = at DashBoardPage
    //DashBoardPage dashBoardPage  = at DashBoardPage

    dashBoardPage.ClickOnModule(moduleName)
}


And(~/^I create a test employee$/) { ->
    Page employeeOverviewPage = at EmployeeOverviewPage
    employeeOverviewPage.clickCreateTestEmployeeLink()
}

// Backgrounds
Given(~/^I have created a new employee$/) { ->
    LoginPage loginPage = to(LoginPage)
    loginPage.login()

    // re-initiate the employee for every run
    binding.getVariable('state').employee.attachToBrowser(browser)
    binding.getVariable('state').employee.orchestrateCreation()

}

Given(~/^I log into ops dashboard with valid login credentials$/) { ->
    LoginPage loginPage = to(LoginPage)
    loginPage.login()
}

Given(~/^I am on the 'Employee Details' page of an employee$/) { ->
    page.browser.go("${BasePage.getUrlParameter()}/ops/employee/show/${employee.id}")
    at(EmployeeDetailsPage)

}

And(~/^I click on the 'Create Test Employee' link$/) { ->
    EmployeeOverviewPage employeeOverviewPage = at(EmployeeOverviewPage)
    employeeOverviewPage.clickCreateTestEmployeeButton()
}

When(~/^I fill in the mandatory fields with the following data$/) { DataTable dataTable ->

    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}

And(~/^I submit the form$/) { ->
    page.submit()
}

Then(~/^I should be on the 'Employee Overview' page$/) { ->
    assert at(EmployeeOverviewPage)
}


When(~/^I fill in the form with the following data$/) { DataTable dataTable ->
    //Page employeeUpdatePage = at EmployeeUpdatePage
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}

And(~/^I should be on the 'Employee Details' page$/) { ->
    assert at(EmployeeDetailsPage)
}

Then(~/^1I should see the error message "([^"]*)"$/) { String arg1 ->
    arg1=arg1.replace("employee.id",employee.id.toString())
    assert page.hasError(arg1)
}

Then(~/^I should see the error message "(.*)"$/) { String arg1 ->
    arg1=arg1.replace("employee.id",employee.id.toString())
    assert page.hasError(arg1)
}

Then(~/^I should see the list of error messages$/) {DataTable dataTable->
    List<String> data = dataTable.asList(String)
    assert page.errors.check(data)
}


Then(~/^I should not see any error messages$/) { ->
    assert page.noErrors()
}

Then(~/^I should see an error message$/) { ->
    assert page.hasErrors()
}


//------------ Company module ------------//

Given(~/^I click on the 'Create Company' link$/) { ->
    // Write code here that turns the phrase above into concrete actions
    CompanyCreatePage comppage = at(CompanyCreatePage)
    comppage.createcompany()
}

Then(~/^I should see the error messages$/) { String arg1->
    assert page.hasErrors(arg1)
}