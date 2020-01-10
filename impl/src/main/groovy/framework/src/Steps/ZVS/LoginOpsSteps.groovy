package framework.src.Steps.ZVS

import cucumber.api.PendingException
import framework.src.Pages.ZVS.OpsAppDashboardPage
import framework.src.Pages.ZVS.OpsAppLoginPage
import framework.src.Pages.ZVS.OrderManagementPage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Then(~/^Created order from company app should display in Order management module of core application$/) { ->
    OpsAppLoginPage opsAppLoginPage = at(OpsAppLoginPage)
    OpsAppDashboardPage opsAppDashboardPage = at(OpsAppDashboardPage)
    OrderManagementPage oderManagementPage = at(OrderManagementPage)
    opsAppLoginPage.makeASuccessLogin()
    opsAppDashboardPage.makeClickInOrderManagement()
    oderManagementPage.getTheValue()
}

