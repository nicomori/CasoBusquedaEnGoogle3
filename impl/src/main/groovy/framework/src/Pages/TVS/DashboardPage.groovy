package framework.src.Pages.TVS

import framework.src.Pages.BasePage
import io.appium.java_client.TouchAction
import io.appium.java_client.android.AndroidDriver
import org.openqa.selenium.By

class DashboardPage extends BasePage {
    //Locators
    static content = {
        buttonRowBackInInformMessageInitial { $(By.xpath("(//android.widget.ImageView)[1]"))}
        fristJobOffer { $(By.xpath("(//android.view.ViewGroup)[1]"))}
    }

    void makeClickInTheInformMessageIfThisAppear(){

        sleep(12777)
        try {
            println('making click in the button back in the inform message, if this appear')
            buttonRowBackInInformMessageInitial.click()
        }catch (Exception e) {
            println('The inform message not appear.')
        }
    }


    void acceptTheFirstJob(){
        println('iiiiiiiiiiiii 2222')
        fristJobOffer.click()
    }
}