package framework.src.Steps.TVS

import framework.src.Pages.TVS.*

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Then(~/^I am in the Dashboard page and I accept the first job of the list$/) { ->
    DashboardPage dashboardPage = at(DashboardPage)
    dashboardPage.makeClickInTheInformMessageIfThisAppear()
    dashboardPage.acceptTheFirstJob()
}
