package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import geb.navigator.Navigator
import org.openqa.selenium.By

class OpsAppDashboardPage extends BasePage {

    //Locators
    static content = {
        ICON_ORDER_MANAGEMENT { $(By.xpath("//*[contains(text(),'Order Management')]"))}
        panel { String panelTitle ->
            $(By.xpath("//div[h4[contains(text(),'${panelTitle}')]]/following-sibling::div[@class='panel-body']"))
        }

        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath("//h4[contains(text(),'${text}')]"))
        }

        // for Primary / other jobs panel
        ClickModuleButton { Navigator panel, String text->
            println text
            linkWithText(panel, text)
        }
    }

    //methods section
    void makeClickInOrderManagement() {
        waitFor (30, { ICON_ORDER_MANAGEMENT.isDisplayed() })
        sleep(5000)

        ICON_ORDER_MANAGEMENT.click()
    }

    void ClickOnModule(modulename) {
        ClickModuleButton(panel(' Modules'),modulename).click()
    }
}