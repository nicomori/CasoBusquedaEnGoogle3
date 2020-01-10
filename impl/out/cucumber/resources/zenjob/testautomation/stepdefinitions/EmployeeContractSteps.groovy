package zenjob.testautomation.stepdefinitions

import geb.Browser
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.orchestration.UIEmployeeContract
import zenjob.testautomation.pages.employee.EmployeeDetailsPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee


When(~/^I add a new contract for that employee with start date "([^"]*)"$/) { String startDate ->
     UIEmployeeContract contract = new UIEmployeeContract(page.browser as Browser, employee)
     contract.create(startDate)
}

/*
 *  existence of contracts
 */

And(~/^There are no inactive contracts for that employee$/) { ->
    EmployeeDetailsPage employeeDetailsPage = at(EmployeeDetailsPage)
    assert employeeDetailsPage.noInactiveContractsExist()
}

And(~/^There is an active contract for that employee$/) { ->
    EmployeeDetailsPage employeeDetailsPage = at(EmployeeDetailsPage)
    assert employeeDetailsPage.activeContractExists()
}

And(~/^There is no active contract for that employee$/) { ->
    EmployeeDetailsPage employeeDetailsPage = at(EmployeeDetailsPage)
    assert !employeeDetailsPage.activeContractExists()
}

/*
 * existence and state of contracts
 */

Then(~/^The employee contract should be in signed state under inactive contracts$/) { ->
    // TODO
    assert true
}

Then(~/^The employee contract should be in signed state under active contracts$/) { ->
    // TODO
    assert true
}

Then(~/^The employee contract should be in signed state under scheduled contracts$/) { ->
    // TODO
    assert true
}

/*
 * error handling
 */

Then(~/^an error message with "([^"]*)" should be displayed"$/) { String errorMessage ->
    // TODO
    assert true
}

