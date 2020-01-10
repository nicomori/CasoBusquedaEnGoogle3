package framework.src.Steps.CompanyApp

import cucumber.api.PendingException
import framework.src.Pages.CompanyApp.BookedShiftsPage
import framework.src.Pages.CompanyApp.LoginPage
import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.CompanyApp.ShiftsPage
import framework.src.Util.TestConstants2

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)


LoginPage companyAppLoginPage

Given(~/^I am on existing company page$/) { ->
    to CompanyAppLoginPage
}

When(~/^I fill email and the password fields with invalid data$/) { ->
    companyAppLoginPage = to(LoginPage)
    companyAppLoginPage.setUserCredentials("nicolas.mori@zenjob.com","test@123344")
}

Then(~/^I am logged in to the company app$/) { ->
    assert at(BookedShiftsPage)
}

Then(~/^I am not logged in to the company app$/) { ->
    assert companyAppLoginPage.getErrorMessage().length()>1
}

And(~/^I click on the login button$/) { ->
    companyAppLoginPage.makeClickInTheLogginButton()
}

When(~/^I fill email and the password fields with valid data$/) { ->
    companyAppLoginPage = to(LoginPage)
    companyAppLoginPage.setUserCredentials(TestConstants2.CredencialsValuesForCompanyApp.COMPANY_USER_GLOBAL_MANAGER_USERNAME,TestConstants2.CredencialsValuesForCompanyApp.GLOBAL_PASSWORD)
}
When(~/^I fill email and the password fields with valid data like a store manager$/) { ->
    companyAppLoginPage = to(LoginPage)
    companyAppLoginPage.setUserCredentials(TestConstants2.CredencialsValuesForCompanyApp.COMPANY_USER_STORE_MANAGER_USERNAME,TestConstants2.CredencialsValuesForCompanyApp.GLOBAL_PASSWORD)
}
Then(~/^I make the logout$/) { ->
    println('Starting to make the logout')
    MenuBarPage menuBarPage = at(MenuBarPage)
    menuBarPage.makeClickInTheBurgerMenu()
    menuBarPage.makeClickInTheButtonLogOut()
}
When(~/^I fill email and the password fields with valid data like a global manager$/) { ->
    companyAppLoginPage = to(LoginPage)
    companyAppLoginPage.setUserCredentials(TestConstants2.CredencialsValuesForCompanyApp.COMPANY_USER_GLOBAL_MANAGER_USERNAME,TestConstants2.CredencialsValuesForCompanyApp.GLOBAL_PASSWORD)
}


And(~/^Local temporary automation company app$/) { ->
    println('GGGGGGGGGGGGGGG 11111111')
    MenuBarPage menuBarPage = at(MenuBarPage)

    println('GGGGGGGGGGGGGGG 2222222')
    menuBarPage.makeClickInTheBurgerMenu()
    menuBarPage.makeClickInTheOtionMenuShifts()

    println('GGGGGGGGGGGGGGG 3333333')
    ShiftsPage shiftsPage = at(ShiftsPage)

    println('GGGGGGGGGGGGGGG 44444444')
    shiftsPage.makeClickInTheButtonOnGoingAndFinished()
    shiftsPage.compareTheElementsinsideOfTheDatesToAccessToTheRightShift()

    println('GGGGGGGGGGGGGGG 44444444')
    println(shiftsPage.getInformationAboutCheckingAndCheckoutData())
    shiftsPage.verifyIfTheFakeCheckingHourAndFakeCheckoutHourAppearInTheShift()
}