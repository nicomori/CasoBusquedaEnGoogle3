package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import org.openqa.selenium.By

class OrderManagementPage extends BasePage {
    int orderIndex = 1
    def indexOfTheRightRow = 0

    //Locators
    static content = {
        VALUE_TO_COMPARE { $(By.xpath("((//tr)[6]//a)[1]"))}
        labelOrderNamber { $(By.xpath("(//tbody/tr/td[1]/a)[${orderIndex}]"))}
        buttonInTheRowOfTheOrderNumber { $(By.xpath("(//tbody/tr/td[9]/a)[${orderIndex}]"))}
    }

    //methods section
    void getTheValue() {
        waitFor {VALUE_TO_COMPARE.isDisplayed()}
        println('In the Ops App we can see the value: '+VALUE_TO_COMPARE.text())
        valueIntoTheOpsApp = Integer.parseInt(VALUE_TO_COMPARE.text())
        println('Starting to compare value of the company app = '+valueIntoTheCompanyApp)
        println('Starting to compare value of the Ops App = '+valueIntoTheOpsApp)
    }

    /**
     * this method return the right row where the order number is.
     *
     * @param orderNumberToFind
     * @return index in the table where the order number is located
     */
    int getRowOfTheNumberOfOrder(int orderNumberToFind) {
        waitFor { VALUE_TO_COMPARE.isDisplayed() }

        for (int i = 1; i < 10; i++) {
            String someString2 = null

            sleep(2000)

            someString2 = getBrowser().getDriver().findElement(By.xpath("(//tbody/tr/td[1]/a)[${orderIndex}]")).getText()
            someString2 = someString2.replace(']','')
            someString2 = someString2.replace('[','')

            if(!someString2.isEmpty()){
                if (someString2.contains(orderNumberToFind+"") ) {
                    indexOfTheRightRow = orderIndex
                }
            }

            orderIndex++
        }
        return indexOfTheRightRow
    }

    /**
     * this method make click in the button details of the order correctly.
     * @param orderNumber
     */
    void makeClickInTheDetailButtonOfTheOrder(int orderNumber){
        waitFor (30, { VALUE_TO_COMPARE.isDisplayed() })

        orderIndex = getRowOfTheNumberOfOrder(orderNumber)
        buttonInTheRowOfTheOrderNumber.click()
    }
}