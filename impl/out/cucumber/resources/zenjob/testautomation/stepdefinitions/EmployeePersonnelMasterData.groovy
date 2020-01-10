package zenjob.testautomation.stepdefinitions

import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.pages.employee.EmployeeDetailsPage
import geb.Page
import zenjob.testautomation.pages.employee.EmployeeUpdatePage

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