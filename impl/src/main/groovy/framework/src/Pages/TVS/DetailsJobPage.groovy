package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By
import org.openqa.selenium.Dimension
import org.openqa.selenium.interactions.touch.TouchActions

class DetailsJobPage extends BasePage {
    //Locators
    static content = {
        buttonAcceptTheJob { $(By.xpath("(//android.widget.Button)[2]"))}
    }

    void scrollDownAndAceptTheJob(){
        println('Starting to make the scroll down, in the job details.')
        sleep(5555)
        //scroll down

        try{
            println('activating the execution to make scroll')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,1300 )
                    .moveTo(208, 200)
                    .release()
                    .perform()

        }catch(Exception e){
            println('starting to make scroll down try 1')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,200 )
                    .moveTo(208, 1300)
                    .release()
                    .perform()

            println('starting to make scroll down try 2')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,200 )
                    .moveTo(208, 1300)
                    .release()
                    .perform()

            println('starting to make scroll down try 3')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,200 )
                    .moveTo(208, 1300)
                    .release()
                    .perform()

            println('starting to make scroll down try 4')
            (new TouchAction((AndroidDriver) getBrowser().getDriver()))
                    .press(200,200 )
                    .moveTo(208, 1300)
                    .release()
                    .perform()
        }

        sleep(2000)

        println('Making click in the button to accept the job')
        buttonAcceptTheJob.click()
    }
}