package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class SignaturePanelPage extends BasePage {
    //Locators
    static content = {
        textPanelToSign { $(By.xpath("(//android.widget.ImageView)[2]"))}
        buttonAcceptTheSignMaked { $(By.xpath("(//android.widget.Button)[2]"))}
        buttonFinalAcceptJob { $(By.xpath("(//android.widget.Button)[1]"))}
    }

    void signTheContract(){
        println('making click in the panel to make the sign.')
        textPanelToSign.click()

        println('Starting to make the sign')
        sleep(2000)

        (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                .press(924,424 )
                .moveTo(1620, 473)
                .release()
                .perform()

        (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                .press(393,656 )
                .moveTo(1508, 263)
                .release()
                .perform()

        println('making click in the button to accept the sign made.')
        sleep(2222)
        buttonAcceptTheSignMaked.click()


        println('making click in the final button to accept the job.')
        sleep(3333)
        buttonFinalAcceptJob.click()
    }
}