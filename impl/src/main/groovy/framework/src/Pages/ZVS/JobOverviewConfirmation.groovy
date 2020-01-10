package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import org.openqa.selenium.Alert
import org.openqa.selenium.By

class JobOverviewConfirmation extends BasePage {
    //Locators
    static content = {
        buttonStartMatching { $(By.xpath("(//div[contains(@class,'panel-info')]//div[contains(@class,'panel-body')])[last()]/a")) }
        labelJobOverview{ $(By.xpath("//*[@id='dashboard']//h1")) }

    }

    //methods section
    /**
     * make click in the button starting to matching
     *
     */
    String makeClicInTheButtonStartingToMatch() {
        waitFor { buttonStartMatching.isDisplayed() }
        buttonStartMatching.click()

        sleep(3000)
        browser.driver.switchTo().alert().accept()
        sleep(8000)

        String labelInTheTitle = browser.driver.findElement(By.xpath("//*[@id='dashboard']//h1")).getText()

        //this regex get 4 numbers in the text received.
        labelInTheTitle = (labelInTheTitle =~ /.*#(\d{4,})\(.*/)[0][1]

        jobNumberCreated = Integer.parseInt(labelInTheTitle)
        println("job number created = "+jobNumberCreated)

        return labelInTheTitle
    }

}