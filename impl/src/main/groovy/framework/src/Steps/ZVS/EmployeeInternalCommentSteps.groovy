package framework.src.Steps.ZVS

import cucumber.api.DataTable
import framework.src.ApplicationState

import framework.src.Orchestration.UIEmployee
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.EmployeeInternalCommentPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

When(~/^I click on 'Update Comment' button for 'Internal zenjob comment' section$/) { ->
    page.editInternalComment()
    assert at(EmployeeInternalCommentPage)
}
And(~/^I click on 'Update Comment' button$/) { ->
    page.submit()
    assert at(EmployeeDetailsPage)
}
And(~/^The comment should be displayed correct in 'Internal zenjob comment' section$/) { DataTable dataTable  ->
    // Write code here that turns the phrase above into concrete actions
    assert at(EmployeeDetailsPage)
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkFields(data)
}

And(~/^There is no any comment in 'Internal zenjob comment' section$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert page.internalZenjobComment.text() == ''
}