package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class PageTVSExample1 extends BasePage {
    //Locators
    static content = {
        //welcomePage
        buttonMenuLogout { $(By.xpath("(//android.widget.Button)[1]"))}

        //loginPagePage
        editFieldEmail { $(By.xpath("(//android.widget.EditText)[1]"))}
        editFieldPassword { $(By.xpath("(//android.widget.EditText)[2]"))}
        buttonLoggin { $(By.xpath("(//android.widget.Button)[1]"))}

        // dashboardPage
        buttonRowBackInInformMessageInitial { $(By.xpath("(//android.widget.ImageView)[1]"))}
        fristJobOffer { $(By.xpath("(//android.view.ViewGroup)[1]"))}

        // detailsJobPage
        buttonAcceptTheJob { $(By.xpath("(//android.widget.Button)[2]"))}

        // signaturePanelPage
        textPanelToSign { $(By.xpath("(//android.widget.ImageView)[2]"))}
        buttonAcceptTheSignMaked { $(By.xpath("(//android.widget.Button)[2]"))}

        // signedContractPage
        buttonFinalAcceptJob { $(By.xpath("(//android.widget.Button)[1]"))}

    }

    void example1(){
        //welcomepage
        println('welcomepage')
        sleep(2555)
        buttonMenuLogout.click()

        //loginPage
        sleep(1111)
        println('kkkkkkkkk')
        getDriver().findElement(By.xpath("(//android.widget.EditText)[1]")).sendKeys('nicolas.mori+0003@zenjob.com')

        println('loginPage 2')
        getDriver().findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys('123456')
        println('loginPage 3')
        buttonLoggin.click()

        // dashboard
        sleep(9777)
        println('iiiiiiiiiiiii')
        buttonRowBackInInformMessageInitial.click()

        println('iiiiiiiiiiiii 2222')
        fristJobOffer.click()

        // detailsJobPage
        println('iiiiiiiiiiiii 33333')
        sleep(5555)
        //scroll down

        try{
            // touch, hold, and drag based on coordinates
            println('Making scroll down in the mobile.')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,1300 )
                    .moveTo(208, 200)
                    .release()
                    .perform()

        }catch(Exception e){
            println('Making scroll down in the mobile.')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,200 )
                    .moveTo(208, 1300)
                    .release()
                    .perform()
        }

        sleep(2000)

        println('Making click in the ')
        buttonAcceptTheJob.click()

        // signedContractPage
        println('Activating the signature')
        textPanelToSign.click()

        println('Starting to make the signature')
        sleep(2000)

        (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                .press(324,124 )
                .moveTo(620, 173)
                .release()
                .perform()

        (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                .press(293,656 )
                .moveTo(508, 263)
                .release()
                .perform()

        println('Accept the signature')
        sleep(2222)
        buttonAcceptTheSignMaked.click()

        println('Starting to make click in the button to accept to job.')
        sleep(3333)
        buttonFinalAcceptJob.click()
    }
}