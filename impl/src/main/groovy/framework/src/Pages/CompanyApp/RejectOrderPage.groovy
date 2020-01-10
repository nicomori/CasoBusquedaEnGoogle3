package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class RejectOrderPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    //Locators
    static content = {
        botonEdit { $(By.xpath("(//*[contains(@class,'OrderActionHeader__HeaderButton')]/button)[2]"))}
        labelReject { $(By.xpath("//label[contains(text(),'Abgelehnt')]"))}
        buttonSubmitOrder { $(By.xpath("(//button[contains(text(),'Buchung abschicken')])[1]"))}
        popupButtonSubmitOrder { $(By.xpath("(//*[contains(@class,'SubmitOrderModal__ModalButtonsContainer')]/button)[2]"))}
        popupConfirmationLabelTitle { $(By.xpath("(//h2[contains(@class,'Modal__ModalHeadline')])[last()]"))}
        popupConfirmationButtonSubmit { $(By.xpath("//div[contains(@class,'SubmitOrderSuccessModal__ModalButtons')]/button"))}
        labelLastActivitie { $(By.xpath("//div[contains(@class,'Activity__Information')]"))}
    }

    // Other locators
    String buttonSubmitButton = "(//*[contains(@class,'OrderActionHeader__HeaderButton')]//button)[last()]"


    //methods section
    /**
     * This method return the label or rejected of the page, to validate you are here.
     *
     *  @return String with the content of the text in the label.
     */
    String getTheContentTextOfTheLabelRejectOrder() {
        waitFor { labelReject.isDisplayed() }
        return labelReject.text()
    }

    /**
     * This method make click in the button edit.
     *
     */
    void makeClickInTheButtonEdit() {
        sleep(4000)
        botonEdit.click()
    }

    /**
     * This method make click in the button submit order.
     *
     */
    void makeClickInTheButtonSubmitOrder() {
        waitFor (40, { buttonSubmitOrder.isDisplayed() })

        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath(buttonSubmitButton))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath(buttonSubmitButton))).click().build().perform()

        waitFor { buttonSubmitOrder.isDisplayed() }
        buttonSubmitOrder.click()
    }

    /**
     * This method make click in the button submit order in the popup confirmation.
     *
     */
    void makeClickInThePopUpConfirmationButtonSubmitOrder() {
        waitFor (40, { popupButtonSubmitOrder.isDisplayed() })
        popupButtonSubmitOrder.click()
    }

    /**
     * This method return the value in the title of the confirmation popup
     *
     * @return String with the title
     */
    String getTheTextOfThePupUpConfirmationOfSubmitOrder() {
        waitFor { popupConfirmationLabelTitle.isDisplayed()}
        return popupConfirmationLabelTitle.text()
    }

    /**
     * This method make click in the submit button in the popup of confirmation
     *
     */
    void makeClickInTheConfirmationPopUpOfSubmitOrder() {
        waitFor { popupConfirmationButtonSubmit.isDisplayed()}
        popupConfirmationButtonSubmit.click()
    }


    /**
     * This method return the value in the bodytext of the last activity
     *
     * @return String with the text inside of the activities
     */
    String getLastTextOfTheLastActivitie() {
        waitFor { labelLastActivitie.isDisplayed()}
        return labelLastActivitie.text()
    }
}