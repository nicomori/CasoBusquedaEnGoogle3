package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import org.openqa.selenium.By
import framework.src.Сonfig.Configuration

class OpsAppLoginPage extends BasePage {

    static url = 'https://staging-main.zenjob.org'

    //Locators
    static content = {
        EDITFIELD_USERNAME { $(By.xpath("//*[(@name='username')]"))}
        EDITFIELD_PASSWORD { $(By.xpath("//*[(@name='password')]"))}
        BUTTON_LOGIN { $(By.xpath("//*[(@id='submit')]"))}
        loginForm { $('form') }
        loginButton { $('input', type: 'submit') }
    }

    //methods section
    void makeASuccessLogin() {
        waitFor {EDITFIELD_USERNAME.isDisplayed()}
        EDITFIELD_USERNAME.value('nicolas.mori@zenjob.com')
        EDITFIELD_PASSWORD.value('test@123')
        BUTTON_LOGIN.click()
    }

    void login() {
        loginForm.username = Configuration.instance.getConfigProperty('opsDashboardUser')
        loginForm.password = Configuration.instance.getConfigProperty('opsDashboardPassword')
        loginButton.click()
    }
}