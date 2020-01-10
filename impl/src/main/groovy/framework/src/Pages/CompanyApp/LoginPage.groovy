package framework.src.Pages.CompanyApp


import org.openqa.selenium.By
import framework.src.Pages.BasePage

class LoginPage extends BasePage {

    static url = 'https://company-release.zenjob.org/auto/login'

    //Locators
    static content = {
        EDITFIELD_EMAIL { $(By.xpath("//input[@name='username']")) }
        EDITFIELD_PASSWORD { $(By.xpath("//input[@name='password']")) }
        BUTTON_LOGIN { $(By.xpath("//button[contains(@class,'LoginButton')]")) }
        ERROR_MESSAGE_USER { $(By.xpath("(//span[contains(@class,'InputField__ErrorMessage')])[1]"))}
        ERROR_MESSAGE_PASS { $(By.xpath("(//span[contains(@class,'InputField__ErrorMessage')])[2]"))}
    }

    //methods section
    void setUserCredentials(String username, String password){
        waitFor {EDITFIELD_EMAIL}
        EDITFIELD_EMAIL.value(username)
        EDITFIELD_PASSWORD.value(password)
    }

    void makeClickInTheLogginButton(){
        waitFor {BUTTON_LOGIN}
        BUTTON_LOGIN.click()
    }

    String getErrorMessage(){
        String errorMessage

        sleep(3555)

        errorMessage = ERROR_MESSAGE_USER.text()
        errorMessage = errorMessage + ERROR_MESSAGE_PASS.text()

        return errorMessage
    }
}