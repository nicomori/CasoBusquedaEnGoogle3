package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement

class OrdersPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    int orderIndex = 1

    //Locators
    static content = {
        buttonNewOrder { $(By.xpath("//button[contains(text(),'Neue Personalbuchung')]")) }
        labelOrderStatus { $(By.xpath("(//tbody/tr/td/div/label)[${orderIndex}]")) }
        buttonOrderShowOrder { $(By.xpath("(//tbody/tr/td/button)[${orderIndex}]")) }
        listOfOrders { $(By.xpath("//tbody/tr")) }

    }

    //methods section
    /**
     * This method make click in the button New Order in the page orders.
     */
    void makeClickInTheBurgerNewOrder() {
        sleep(2000)
        waitFor { buttonNewOrder.isDisplayed() }
        buttonNewOrder.click()
    }

    /**
     * this method get the list of the orders read the status and at the momento to find some of them with status of pending is making click
     *
     */
    void makeClickInTheButtonShowOrderInTheFirstOrderWithStatusPending() {
        List<WebElement> listOfOrders = getBrowser().getDriver().findElements(By.xpath("//tbody/tr"))

        for (orderIndex; orderIndex < listOfOrders.size(); orderIndex++) {
            String statusOfTheOrder = labelOrderStatus.text()

            if (statusOfTheOrder.contains('Ausstehend')) {
                buttonOrderShowOrder.click()
                break
            }
        }
    }

    /**
     * this method get the list of the orders read the status and at the moment to find some of them with status of reject is making click
     *
     */
    void makeClickInTheButtonShowOrderInTheFirstOrderWithStatusReject() {
        List<WebElement> listOfOrders = getBrowser().getDriver().findElements(By.xpath("//tbody/tr"))

        for (orderIndex; orderIndex < listOfOrders.size(); orderIndex++) {
            String statusOfTheOrder = labelOrderStatus.text()

            if (statusOfTheOrder.contains('Abgelehnt')) {
                buttonOrderShowOrder.click()
                break
            }
        }
    }
}