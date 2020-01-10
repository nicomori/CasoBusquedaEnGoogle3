package zenjob.testautomation.stepdefinitions

import cucumber.api.PendingException
import zenjob.testautomation.pages.employee.EmployeeCreationPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on the 'Create Employee' page$/) { ->
    EmployeeCreationPage employeeCreationPage = at(EmployeeCreationPage)
}

And(~/^I click on 'Create Employee' button$/) { ->
    page.submit()
}