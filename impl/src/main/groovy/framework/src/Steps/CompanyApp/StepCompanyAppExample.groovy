package framework.src.Steps.CompanyApp


import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.CompanyApp.NewOrderPage
import framework.src.Pages.CompanyApp.OrdersConfirmationPage
import framework.src.Pages.CompanyApp.OrdersPage
import framework.src.Pages.ZVS.PageZVSExample1

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Given(~/^test ggggggggggggggggg23333$/) { ->
    println('hhhhhhhhhhhhhhhhhh1111111111')
}

When(~/^test33333$/) { ->
    PageZVSExample1 pageZVSExample1 = new PageZVSExample1()
    pageZVSExample1.example1()
}

When(~/^I create an order by selecting one job location and one shift all mandatory information$/) { ->
    MenuBarPage menuBarPage = at(MenuBarPage)
    OrdersPage ordersPage = at(OrdersPage)
    NewOrderPage newOrderPage = at(NewOrderPage)
    OrdersConfirmationPage ordersConfirmationPage = at(OrdersConfirmationPage)
    menuBarPage.makeClickInTheBurgerMenu()
    menuBarPage.makeClickInTheOtionMenuOrders()
    ordersPage.makeClickInTheBurgerNewOrder()
    newOrderPage.generateANewOrder()
    ordersConfirmationPage.getOrderNumber()
}

