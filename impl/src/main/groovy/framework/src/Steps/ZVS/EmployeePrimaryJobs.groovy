

package framework.src.Steps.ZVS

import cucumber.api.DataTable
import framework.src.ApplicationState
import framework.src.Orchestration.UIEmployee
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.EmployeeCommentUpdate.EmployeeCommentUpdatePage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

Given(~/^i am on the employee details page for an employe$/) { ->
    // Write code here that turns the phrase above into concrete actions


    EmployeeDetailsPage employeeDetailsPage = to(EmployeeDetailsPage, employee.id)
    //EmployeeCommentUpdatePage employeecommentupdatepage = new EmployeeCommentUpdatePage()
}

When(~/^I click on update comment button in Primary or other jobs section$/) { ->

//click on update comment button
    //page.UpdateCommentButton("Primary / other jobs (Please note we do not save history)")
    page.editPrimaryComment()
}

When(~/^I navigate to Employee comment update page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert  at (EmployeeCommentUpdatePage)
}

When(~/^I entered or update comment with below data$/) { DataTable dataTable ->
    //Page employeeUpdatePage = at EmployeeUpdatePage
    Map<String, String> data = dataTable.asMap(String, String)
    page.fillFields(data)
}

When(~/^I click on Update comment button$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.clickUpdateCommentButton_ECUpage()
}

Then(~/^I navigate to Employee details page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert at (EmployeeDetailsPage)
}

Then(~/^Comment should be displayed under Primary or other jobs section$/) { DataTable dataTable ->
    // Write code here that turns the phrase above into concrete actions
    //assert $("div").text() == "yes 20 years"
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkFields(data)
}

