package framework.src.Steps.ZVS

import framework.src.ApplicationState
import framework.src.Orchestration.UIEmployee
import framework.src.Pages.ZVS.employee.EmployeeUpdatePage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

Then(~/^I should see warning for those fields$/) { ->
    assert page.hasError("taxIdentNumber-error")
    assert page.hasError("socialInsuranceNumber-error")
    assert page.hasError("iban-error")
    assert page.hasError("bic-error")
}

Given(~/^I edit 'Payment Details' section on the 'Employee Details' page$/) { ->
    page.editPersonnelMasterData()
    assert at(EmployeeUpdatePage)
}