package framework.src.Steps.TVS

import framework.src.Pages.TVS.*
import framework.src.Util.TestConstants2

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

And(~/^In the login page I make a success login$/) { ->
    LoginPage loginPage = at(LoginPage)
    loginPage.makeSuccessLoggin('nicolas.mori+0005@zenjob.com','12345678')
}

And(~/^In the login page I make a success login, with a user used for auto effective$/) { ->
    LoginPage loginPage = at(LoginPage)
    loginPage.makeSuccessLoggin(
            TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_1+
                    TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_MIDDLE +
                    TestConstants2.UsersTalent.TALENT_USER_EMAIL_DOMAIN_NAME_PART_2
            ,'12345678')

}

And(~/^Local temporary automation mobile app$/) { ->
    DashboardPage dashboardPage = at(DashboardPage)
    dashboardPage.makeClickInTheInformMessageIfThisAppear()

    LoginPage loginPage = at(LoginPage)
    loginPage.temporaryMethodLocalTest1()
}
