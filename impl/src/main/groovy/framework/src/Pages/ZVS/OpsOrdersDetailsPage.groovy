package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class OpsOrdersDetailsPage extends BasePage {
   //Locators
    static content = {
        buttonCreateJob { $(By.xpath("//a[contains(@href,'/ops/job/createFromOrder?orderShift')]")) }
    }

    //methods section

    /**
     * this method make click in the button create job in the page order details
     *
     */
    void makeClickInTheButtonCreateJob() {
        waitFor { buttonCreateJob.isDisplayed() }
        buttonCreateJob.click()
    }

}