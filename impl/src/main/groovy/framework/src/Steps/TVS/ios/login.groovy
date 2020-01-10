package framework.src.Steps.TVS.ios

import cucumber.api.PendingException
import framework.src.Pages.CompanyApp.MenuBarPage
import framework.src.Pages.TVS.DashboardPage
import framework.src.Pages.TVS.DetailsJobPage
import framework.src.Pages.TVS.LoginPage
import framework.src.Pages.TVS.PageTVSExample1
import framework.src.Pages.TVS.SignaturePanelPage
import framework.src.Pages.TVS.WelcomePage
import framework.src.Pages.TVS.old_pepe.ios.views.IntroViewController
import framework.src.Pages.TVS.old_pepe.ios.views.LoginView
import framework.src.Util.TestConstants2
import org.openqa.selenium.support.PageFactory

//import framework.src.Pages.TVS.old_pepe.ios.views.OfferView


this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

Given(~/^I am on welcome page$/) { ->
    waitFor{at IntroViewController}
}

When(~/^I click on I already have a login$/) { ->
    IntroViewController page= at IntroViewController
    page.login.click()
    waitFor{at LoginView}
}

When(~/^I insert my login and click on login$/) { ->
    LoginView page= at LoginView
    sleep(5000)
    page.setLogin(TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_1+TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_MIDDLE+TestConstants2.UsersTalent.TALENT_USER_EMAIL_DOMAIN_NAME_PART_2,TestConstants2.UsersTalent.TALENT_USER_PASSWORD)
//    page.setLogin('nicolas.mori+0003@zenjob.com','123456')
    sleep(5000)
    page.clickLoginButton()
}

Then(~/^I should enter the app$/) { ->
    sleep(5000)
    println("LOGGED IN")
}

When(~/^I dismiss the promotion message$/) { ->
    LoginView page= at LoginView
    sleep(5000)
    page.verstanden()
    sleep(5000)
}
And(~/^I click on the first offer$/) { ->
    LoginView page= at LoginView


    try {
        println('ffffff eeeee ')

        sleep(6000)
        page.clickFirstOffer()
    }catch(Exception e){

        println('ffffff eeeee 444444444444')
        sleep(4000)

        page.clickFirstOffer()
    }


    sleep(5000)
}
And(~/^I click on the apply button$/) { ->
    LoginView page= at LoginView
    println('kkkkkkkkkkkkkkkk 3333')

    try {
        page.scroll()
    }catch(Exception e){
        println('kkkkkkkkkkkkkkkk 2222222')
        sleep(4000)
        page.scroll()
    }


    try {
        println('kkkkkkkkkkkkkkkk 1111111111')

        sleep(4000)
        page.clickApply()
    }catch(Exception e){

        println('kkkkkkkkkkkkkkkk sssss ssss  1111111111')
        sleep(4000)

        page.clickApply()
    }


}
And(~/^I sign the screen$/) { ->
    LoginView page= at LoginView
    sleep(5000)



    try {
        println('kkkkkkkkkkkkkkkk 8888')

        sleep(4000)
        page.signatureButton()
    }catch(Exception e){

        println('kkkkkkkkkkkkkkkk 888888 hhhhhhhhhhhh')
        sleep(4000)

        page.signatureButton()
    }



    try {
        println('kkkkkkkkkkkkkkkk 99999')

        sleep(4000)
        page.drawing()
    }catch(Exception e){

        println('kkkkkkkkkkkkkkkk 99999 gggggggggggg')
        sleep(4000)

        page.drawing()
    }




    try {
        println('kkkkkkkkkkkkkkkk gggggg')

        sleep(4000)
        page.masterworkButton()
    }catch(Exception e){

        println('kkkkkkkkkkkkkkkk gggggg ggggg43333333')
        sleep(4000)

        page.masterworkButton()
    }


}


And(~/^I send the signed contract$/) { ->

    LoginView page= at LoginView
    println('kkkkkkkkkkkkkkkk hhhhhhh')
    sleep(5000)
    try {
        page.clickSend()
    }catch (Exception ee){
        println('errroooooorrrr -- kkkkkkkkkkkkkkkk hhhhhhh')
        sleep(5000)
        page.clickSend()
    }

    println('adsfgdsafvdsa sadfdsadfvdsadfv sdvcbdsadvsadvc asdfsfafvdsavc')


    sleep(5000)

}


Given(~/^I am in the android app localtest$/) { ->
    WelcomePage welcomePage = at(WelcomePage)
    welcomePage.makeClickInTheButtonMakeLoggin()

    LoginPage loginPage = at(LoginPage)
    loginPage.makeSuccessLoggin('nicolas.mori+0003@zenjob.com')

    DashboardPage dashboardPage = at(DashboardPage)
    dashboardPage.acceptTheFirstJob()

    DetailsJobPage detailsJobPage = at(DetailsJobPage)
    detailsJobPage.scrollDownAndAceptTheJob()

    SignaturePanelPage signaturePanelPage = at(SignaturePanelPage)
    signaturePanelPage.signTheContract()
}

