package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class WelcomePage extends BasePage {
    //Locators
    static content = {
        //welcomePage
        buttonMenuLogout { $(By.xpath("(//android.widget.Button)[1]"))}
    }

    void makeClickInTheButtonMakeLoggin(){
        println('welcomepage')
        sleep(2555)
        buttonMenuLogout.click()
    }
}