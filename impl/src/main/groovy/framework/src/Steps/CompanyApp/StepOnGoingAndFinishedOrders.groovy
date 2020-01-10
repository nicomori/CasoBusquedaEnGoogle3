package framework.src.Steps.CompanyApp


import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.CompanyApp.ShiftsPage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

And(~/^I am on page with ongoind and finished orders$/) { ->
    MenuBarPage menuBarPage = at(MenuBarPage)
    menuBarPage.makeClickInTheBurgerMenu()
    menuBarPage.makeClickInTheOtionMenuShifts()
}
And(~/^I choose finished shift with times stated by talent$/) { ->
    ShiftsPage shiftsPage = at(ShiftsPage)
    shiftsPage.makeClickInTheButtonOnGoingAndFinished()
}
When(~/^I state different talents times$/) { ->
    ShiftsPage shiftsPage = at(ShiftsPage)
    shiftsPage.compareTheElementsinsideOfTheDatesToAccessToTheRightShift()
}
Then(~/^company times for shift are not equal to talent ones$/) { ->
    ShiftsPage shiftsPage = at(ShiftsPage)
    println(shiftsPage.getInformationAboutCheckingAndCheckoutData())
    shiftsPage.verifyIfTheFakeCheckingHourAndFakeCheckoutHourAppearInTheShift()
}
