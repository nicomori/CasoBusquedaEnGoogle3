package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import org.openqa.selenium.By

class OrdersConfirmationPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    //Locators
    static content = {
        LABEL_CONFIRMATION_ORDER { $(By.xpath("//h1"))}
        LABEL_PRINCIPAL { $(By.xpath("//h2[contains(@class,'Activities')]"))}
    }

    //methods section
    /**
     * this method get the value of the order generated and create a int value to after return this value.
     *
     * @return int with the value of the new order created.
     */
    int getOrderNumber(){
        String someString = null
        String someString2 = null

        sleep(2000)
        waitFor {LABEL_PRINCIPAL.isDisplayed()}

        someString = getBrowser().getDriver().findElement(By.xpath("//h1")).getText()
        someString2 = someString.findAll( /\d+/ )*.toInteger()

        someString2 = someString2.replace(']','')
        someString2 = someString2.replace('[','')

        valueIntoTheCompanyApp = Integer.parseInt(someString2)
        println('We get the value of Order Number: '+valueIntoTheCompanyApp)


        orderNumberCreated = valueIntoTheCompanyApp

        println("we store this value like a order nuymber = "+orderNumberCreated)

        return valueIntoTheCompanyApp
    }
}