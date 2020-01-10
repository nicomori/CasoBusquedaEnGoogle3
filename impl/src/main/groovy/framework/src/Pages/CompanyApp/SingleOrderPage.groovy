package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class SingleOrderPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    int orderIndex = 1

    //Locators
    static content = {
        buttonHeaderReject { $(By.xpath("//button[contains(@class,'ReviewOrder__RejectButton')]")) }
        buttonHeaderApprove { $(By.xpath("(//*[contains(@class,'OrderActionHeader')]/button[2])")) }
        popupEditfieldReajectReason { $(By.xpath("//textarea")) }
        popupButtonSubmitCommentInTheRejectPopup { $(By.xpath("//*[contains(@class,'ModalContent')]/div[2]/button[2]"))}
        buttonOrderRejectOk { $(By.xpath("//*[contains(@class,'OrderRejectSuccessModal__ModalButtonsContainer')]/button"))}
        popupLabelMessageReject{$(By.xpath("(//*[contains(@class,'Modal__HeaderSection')]/h2)[last()]"))}



        popupButtonOKconfirmationApprove { $(By.xpath("(//*[contains(@class,'ModalContent')]/div[2]/button)[3]"))}
        popupTitleConfirmationApprove { $(By.xpath("(//*[contains(@class,'Modal__HeaderSection')])[2]"))}

        activitiesOfTheOrder { $(By.xpath("//*[contains(@class,'Activity__Information')]"))}
        labelOrderStatus { $(By.xpath("(//tbody/tr/td/div/label)[${orderIndex}]")) }
        buttonOrderShowOrder { $(By.xpath("(//tbody/tr/td/button)[${orderIndex}]")) }

    }







    //methods section
    /**
     * this method make click in the button Reject
     *
     */
    void makeClickInTheButtonReject() {
        boolean continueChecking = true

        println('waiting until the confirmation button of the new order appear')
        while (continueChecking){
            if(buttonHeaderReject.isDisplayed()){
                sleep(2000)
                continueChecking = false
            }
        }
        buttonHeaderReject.click()
    }

    /**
     * this method make click in the button Reject
     *
     */
    void writeATextInTheREasonToReject() {
        boolean continueChecking = true

        println('waiting until the confirmation button of the new order appear')
        while (continueChecking){
            if(popupEditfieldReajectReason.isDisplayed()){
                sleep(2000)
                continueChecking = false
            }
        }

        popupEditfieldReajectReason.value('Automation - reject text')
        popupButtonSubmitCommentInTheRejectPopup.click()
    }

    /**
     * this method make click in the button Reject
     *
     */
    String makeClickInTheButtonOkToConfirmTheReject() {
        boolean continueChecking = true
        String messageOfConfirmationRejected = null

        println('waiting until the confirmation button of the new order appear')
        while (continueChecking){
            if(buttonOrderRejectOk.isDisplayed()){
                sleep(2000)
                continueChecking = false
            }
        }

        messageOfConfirmationRejected = popupLabelMessageReject.text()
        buttonOrderRejectOk.click()

        return messageOfConfirmationRejected
    }

    /**
     * this method approve a single order
     *
     */
    void makeClickInTheButtonApprove() {
        sleep(5000)

        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath("(//*[contains(@class,'OrderActionHeader')]/button[2])"))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath("(//*[contains(@class,'OrderActionHeader')]/button[2])"))).click().build().perform()

        buttonHeaderApprove.click()
    }

    /**
     * this method get the text of the last activity of the order
     *
     * @return String with the text content of the last activity of the order
     */
    String getTextDisplayedInTheLastActivity() {
        waitFor (20, { activitiesOfTheOrder.isDisplayed() })
        return activitiesOfTheOrder.text()
    }

    /**
     * this method get the text of the confirmation title popup
     *
     * @return String with the text content in the title
     */
    String getTextDisplayedInThePopupOfConfirmation() {
        waitFor (20, { popupTitleConfirmationApprove.isDisplayed() })
        return popupTitleConfirmationApprove.text()
    }

    /**
     * this method approve a single order
     *
     */
    void makeClickInTheButtonOkOftheConfirmationPopUp() {
        waitFor (40, { popupButtonOKconfirmationApprove.isDisplayed() })
        popupButtonOKconfirmationApprove.click()
    }

    /**
     * this method get the list of the orders read the status and at the moment to find some of them with status of reject is making click
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
}