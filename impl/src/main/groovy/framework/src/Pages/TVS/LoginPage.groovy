package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class LoginPage extends BasePage {
    //Locators
    static content = {
        //loginPagePage
        editFieldEmail { $(By.xpath("(//android.widget.EditText)[1]"))}
        editFieldPassword { $(By.xpath("(//android.widget.EditText)[2]"))}
        buttonLoggin { $(By.xpath("(//android.widget.Button)[1]"))}
        menuOption1 { $(By.xpath("//android.widget.FrameLayout[contains(@content-desc,'Jobvorschläge')]"))}
        menuOption2 { $(By.xpath("//android.widget.FrameLayout[contains(@content-desc,'Aufgaben')]"))}
        buttonCheckInOutFirstCluster { $(By.xpath("(//android.widget.Button[contains(@resource-id,'check_in_out')])[1]"))}
        popUpButtonCheckin { $(By.xpath("//android.widget.Button[contains(@resource-id,'button1')]"))}


        //android.widget.EditText[contains(@resource-id,'password')]
    }

    void makeSuccessLoggin(String username, String password){
        sleep(1111)
        println('Starting to send to the mobile app the usermname')
        getDriver().findElement(By.xpath("(//android.widget.EditText)[1]")).sendKeys(username)

        println('Starting to send to the mobile app the password')
        getDriver().findElement(By.xpath("(//android.widget.EditText)[2]")).sendKeys(password)

        println('Starting to make click in the button login.')
        buttonLoggin.click()
    }

    void temporaryMethodLocalTest1(){
        try {
            println('making click in the menu bar option 2')
            sleep(2222)
            menuOption2.click()
        }catch(Exception e){
            println('making click in the menu bar option 2, option b')
            sleep(2222)
            menuOption2.click()
        }

        try {
            println('making click in the button checking')
            sleep(2222)
            buttonCheckInOutFirstCluster.click()
        }catch(Exception e){
            println('making click in the button checking, option b')
            sleep(2222)
            buttonCheckInOutFirstCluster.click()
        }


        println('making click in the popup button checking')
        sleep(2222)
        popUpButtonCheckin.click()

        println('Making click in the menu bar 1')
        sleep(2222)
        menuOption1.click()

        println('Making click in the menu bar 2')
        sleep(2222)
        menuOption2.click()

        println('Making click in the checkin button')
        sleep(2222)
        try {
            buttonCheckInOutFirstCluster.click()
        }catch(Exception e){
            println('Making click in the checkin button, option b')
            sleep(3222)
            buttonCheckInOutFirstCluster.click()
        }

        println('Making click in the checkout button')
        sleep(2222)
        try {
            popUpButtonCheckin.click()
        }catch(Exception e){
            println('Making click in the checkout button, option b')
            sleep(3222)
            popUpButtonCheckin.click()
        }

    }

}