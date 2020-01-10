package framework.src.Steps.CompanyApp

import cucumber.api.PendingException
import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.CompanyApp.OrdersPage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Then(~/^Follow the menu to the orders page$/) { ->
    println('Starting to follow the menu to orders page')
    MenuBarPage menuBarPage = at(MenuBarPage)
    menuBarPage.makeClickInTheBurgerMenu()
    menuBarPage.makeClickInTheOtionMenuOrders()
}

Then(~/^I click new order button$/) { ->
    println('Starting to make click in the button new order')
    OrdersPage ordersPage = at(OrdersPage)
    ordersPage.makeClickInTheBurgerNewOrder()
}

