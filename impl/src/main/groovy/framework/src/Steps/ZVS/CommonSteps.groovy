package framework.src.Steps.ZVS

import cucumber.api.DataTable
import geb.Page
import framework.src.Pages.BasePage
import framework.src.Pages.ZVS.company.CompanyCreatePage
import framework.src.Pages.ZVS.employee.EmployeeOverviewPage
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.OpsAppLoginPage
import framework.src.ApplicationState
import framework.src.Orchestration.UIEmployee

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

/*
And(~/^I click on "([^"]*)" module$/) { String moduleName ->

    Page dashBoardPage = at DashBoardPage
    //DashBoardPage dashBoardPage  = at DashBoardPage

    dashBoardPage.ClickOnModule(moduleName)
}
*/

And(~/^I create a test employee$/) { ->
    Page employeeOverviewPage = at EmployeeOverviewPage
    employeeOverviewPage.clickCreateTestEmployeeLink()
}

// Backgrounds
Given(~/^I have created a new employee$/) { ->
    OpsAppLoginPage loginPage = to(OpsAppLoginPage)
    loginPage.login()

    // re-initiate the employee for every run
    binding.getVariable('state').employee.attachToBrowser(browser)
    binding.getVariable('state').employee.orchestrateCreation()

}

Given(~/^I log into ops dashboard with valid login credentials$/) { ->
    OpsAppLoginPage loginPage = to(OpsAppLoginPage)
    loginPage.makeASuccessLogin()
}

Given(~/^I am on the 'Employee Details' page of an employee$/) { ->
 //   page.browser.go("${BasePage.getUrlParameter()}/ops/employee/show/${employee.id}")
    page.browser.go(binding.getVariable("ops")+"/ops/employee/show/${employee.id}")
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