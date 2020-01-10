package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import framework.src.Pages.CompanyApp.modules.ToastSuccessModule
import framework.src.Util.UniqueValueUtil
import framework.src.Util.WaitUtil
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class NewOrderPage extends BasePage {

    //TODO: this url shouldn't be hardcoded
    static url = 'https://company-release.zenjob.org/auto/createOrder'

    static at = { title == 'Zenjob - Company App' }

    Calendar cal=Calendar.getInstance()
    def currentDate = cal.get(Calendar.DAY_OF_MONTH)

    //Locators
    static content = {
        LABEL_POPUP_LOCATION_SELECTOR {
            $(By.xpath("//*[contains(@class,'Modal__ModalHeadline') and contains(text(),'Standort auswählen')]"))
        }
        POPUP_LOCATION_NAME_DROPDOWN { $(By.xpath("//input[contains(@class,'InputField')]")) }
        POPUP_LOCATION_NAME_DROPDOWN_OPTION_1 { $(By.xpath("(//li[contains(@class,'Autocomplete')])[1]")) }
        POPUP_LOCATION_BUTTON_ADD_LOCATION { $(By.xpath("(//div[contains(@class,'AddLocationModal')]//button)[2]")) }

        // shift section
        BUTTON_SELECTOR_DATE { $(By.xpath("(//tr//button[contains(@class,'SingleDatePickerInput')])[1]")) }
        BUTTON_SELECTOR_START_HOUR { $(By.xpath("(//td[contains(@class,'TdTimePicker__Styled')]/div/div/div)[1]")) }
        BUTTON_SELECTOR_FINISH_HOUR { $(By.xpath("(//td[contains(@class,'TdTimePicker__Styled')]/div/div/div)[2]")) }
        BUTTON_DONE_HOUR { $(By.xpath("//div[contains(@class,'TimePicker')]/div/div/button")) }
        BUTTON_ARROW_INCREASE_HOUR { $(By.xpath("(//div[contains(@class,'TimePicker')]/span/span)[1]")) }
        BUTTON_DROPDOWN_JOB_DEFINITION { $(By.xpath("(//label[contains(@class,'Dropdown')]/div/input)[1]")) }
        OPTIONLIST_1_JOB_DEFINITION { $(By.xpath("(//*[contains(@class,'Dropdown')]//ul//li)[2]")) }
        HEADER_BUTTON_SUBMIT_ORDER { $(By.xpath("(//div[contains(@class,'OrderActionHeader__Header')]/button)[3]")) }

        // second shift section
        BUTTON_ADD_NEW_SHIFT { $(By.xpath("//button[contains(@class,'AddShiftButton')]")) }
        SECOND_SHIFT_BUTTON_SELECTOR_DATE { $(By.xpath("(//tr//button[contains(@class,'SingleDatePickerInput')])[2]")) }
        SECOND_SHIFT_BUTTON_SELECTOR_START_HOUR { $(By.xpath("(//td[contains(@class,'TdTimePicker__Styled')]/div/div/div)[3]")) }
        SECOND_SHIFT_BUTTON_SELECTOR_FINISH_HOUR { $(By.xpath("(//td[contains(@class,'TdTimePicker__Styled')]/div/div/div)[4]")) }
        SECOND_SHIFT_BUTTON_DROPDOWN_JOB_DEFINITION { $(By.xpath("(//label[contains(@class,'Dropdown')]/div/input)[2]")) }
        SECOND_SHIFT_OPTIONLIST_1_JOB_DEFINITION { $(By.xpath("((//ul[contains(@class,'Dropdown')])[2]/span/li)[1]")) }

        //shift details sections
        dropdownContactInfo { $(By.xpath("(//div[contains(@class,'ContactInfo')]//input)[1]")) }
        DROPDOWN_CONTACT_INFO_OPTION_TEMPLATE_1 {
            $(By.xpath("(//div[contains(@class,'ContactInfo')]//ul/span/li)[2]"))
        }
        BUTTON_CLOSE_WINDOW_CONTACT_INFO { $(By.xpath("//div[contains(@class,'BasicInfoHeader')]/div[2]")) }

        // popup acceptance sections
        POPUP_SUBMIT_ORDER_BUTTON_SUBMIT { $(By.xpath("(//div[contains(@class,'SubmitOrderModal')]/button)[2]")) }
        POPUP_CONFIRMATION_TITLE_SUCCESS { $(By.xpath("(//div[contains(@class,'NakedModal')])[10]//h2")) }
        POPUP_CONFIRMATION_BUTTON_SUBMIT { $(By.xpath("(//div[contains(@class,'NakedModal')])[10]/div//button")) }

        //shift details form
        //Job Requirements fields
        jobRequirementsDropDown { $('[class*="RequirementsInfo"] input') }
        requirementsTemplatesList { $('[class*="RequirementsInfo"] ul li') }
        jobDescriptionField { $('[class*="RequirementsInfo"] textarea')[0] }
        jobSpecRequirementsField { $('[class*="RequirementsInfo"] textarea')[1] }
        saveRequirementsTemplateButton { $('[class*="RequirementsInfo"] button')[0] }
        updateRequirementsTemplateButton { $('[class*="RequirementsInfo"] button')[1] }

        contactNameAddButton { $('[class*="ContactInfo"] [class*="Autocomplete"] li:nth-child(1) span') }
        contactNumberField { $('[class*="ContactInfo"] input')[1] }
        contactEmailField { $('[class*="ContactInfo"] input')[2] }
        saveContactTemplateButton { $('[class*="ContactInfo__Button"] button:nth-child(1)') }


        templateSavedSuccessMessage { module ToastSuccessModule }
    }

    //some data for tests
    def JOB_TEMPLATE_NAME = UniqueValueUtil.getUniqueValue("Test template")
    def JOB_DESCRIPTION = "This s test template to check what can we do with automation"
    def JOB_SPECIAL_REQUIREMENTS = "And here we added some special stuff... also by auto-tests"
    def CONTACT_NAME = UniqueValueUtil.getUniqueValue("Contact")
    def CONTACT_NUMBER = "015110230000"
    def CONTACT_EMAIL = "nonemailreally@notexisting.rr"

    // other locators
    String optionDateCurrentDateLocator = "(//table[contains(@class,'CalendarMonth_table CalendarMonth_table_1')]//td[@role='button' and contains(text(),'${currentDate}')])[1]"


    //methods section
    /**
     * This method make generate a complete order.
     *
     */
    void generateANewOrder() {
        boolean continueChecking = true
        sleep(2000)
        waitFor { POPUP_LOCATION_NAME_DROPDOWN.isDisplayed() }
        POPUP_LOCATION_NAME_DROPDOWN.value("B")
        POPUP_LOCATION_NAME_DROPDOWN_OPTION_1.click()
        sleep(2000)
        POPUP_LOCATION_BUTTON_ADD_LOCATION.click()
        BUTTON_SELECTOR_DATE.click()

        sleep(1000)
        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath(optionDateCurrentDateLocator))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath(optionDateCurrentDateLocator))).click().build().perform();

        BUTTON_SELECTOR_START_HOUR.click()
        waitFor { BUTTON_DONE_HOUR.isDisplayed() }
        BUTTON_DONE_HOUR.click()
        waitFor { BUTTON_SELECTOR_FINISH_HOUR.isDisplayed() }
        BUTTON_SELECTOR_FINISH_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_DONE_HOUR.click()
        BUTTON_DROPDOWN_JOB_DEFINITION.click()
        OPTIONLIST_1_JOB_DEFINITION.click()
        dropdownContactInfo.click()
        DROPDOWN_CONTACT_INFO_OPTION_TEMPLATE_1.click()
        waitFor { BUTTON_CLOSE_WINDOW_CONTACT_INFO.isDisplayed() }
        BUTTON_CLOSE_WINDOW_CONTACT_INFO.click()
        waitFor { HEADER_BUTTON_SUBMIT_ORDER.isDisplayed() }
        HEADER_BUTTON_SUBMIT_ORDER.click()
        POPUP_SUBMIT_ORDER_BUTTON_SUBMIT.click()

        println('waiting until the confirmation button of the new order appear')
        while (continueChecking){
            if(POPUP_CONFIRMATION_BUTTON_SUBMIT.isDisplayed()){
                sleep(2000)
                continueChecking = false
            }
        }
        POPUP_CONFIRMATION_BUTTON_SUBMIT.click()
        sleep(2000)
    }

    /**
     * This method return the title of the pop up after make click in the button generate a new order.
     *
     * @return String with the value of the label of the popup.
     */
    String getLabelTextFromThePopUpSelectorOfLocation() {
        waitFor { LABEL_POPUP_LOCATION_SELECTOR.isDisplayed() }
        return LABEL_POPUP_LOCATION_SELECTOR.text()
    }


    /**
     * This method select a location from the pop up in a new order page.
     *
     */
    void selectATheLocationFromThePopUp() {
        waitFor { POPUP_LOCATION_NAME_DROPDOWN.isDisplayed() }
        POPUP_LOCATION_NAME_DROPDOWN.value("B")
        POPUP_LOCATION_NAME_DROPDOWN_OPTION_1.click()
        waitFor {
            try {
                return !(POPUP_LOCATION_NAME_DROPDOWN_OPTION_1.isDisplayed() && WaitUtil.isAttributePresent(POPUP_LOCATION_BUTTON_ADD_LOCATION, "disabled"))
            } catch (Exception e) {
                return true
            }
        }
        //stability hack
        sleep(100)
        POPUP_LOCATION_BUTTON_ADD_LOCATION.click()
    }


    /**
     * This method fill the principal part of new order.
     *
     */
    void fillAllTheFieldsWithValidValues() {
        waitFor { BUTTON_SELECTOR_DATE.isDisplayed() }
        BUTTON_SELECTOR_DATE.click()

        sleep(1000)
        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath(optionDateCurrentDateLocator))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath(optionDateCurrentDateLocator))).click().build().perform();

        BUTTON_SELECTOR_START_HOUR.click()
        waitFor { BUTTON_DONE_HOUR.isDisplayed() }
        BUTTON_DONE_HOUR.click()
        waitFor { BUTTON_SELECTOR_FINISH_HOUR.isDisplayed() }
        BUTTON_SELECTOR_FINISH_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_DONE_HOUR.click()

//        waitFor { BUTTON_DROPDOWN_JOB_DEFINITION.isDisplayed() }
        BUTTON_DROPDOWN_JOB_DEFINITION.click()

//        waitFor { OPTIONLIST_1_JOB_DEFINITION.isDisplayed() }
        OPTIONLIST_1_JOB_DEFINITION.click()
        waitFor { jobRequirementsDropDown.isDisplayed() && dropdownContactInfo.isDisplayed() }
    }

    /**
     * This method fill all the form in the shift detail.
     *
     */
    void fillAllTheFieldsWithValidValuesInTheShitDetails() {
        waitFor { dropdownContactInfo.isDisplayed() }
        dropdownContactInfo.click()
        DROPDOWN_CONTACT_INFO_OPTION_TEMPLATE_1.click()
        waitFor { BUTTON_CLOSE_WINDOW_CONTACT_INFO.isDisplayed() }
        BUTTON_CLOSE_WINDOW_CONTACT_INFO.click()
    }

    /**
     * This method make click to submit the new order.
     */
    void makeClickInSubmitForm() {
        waitFor { HEADER_BUTTON_SUBMIT_ORDER.isDisplayed() }
        HEADER_BUTTON_SUBMIT_ORDER.click()
        POPUP_SUBMIT_ORDER_BUTTON_SUBMIT.click()
        println('waiting until the confirmation button of the new order appear')
        waitFor(40, { POPUP_CONFIRMATION_BUTTON_SUBMIT.isDisplayed() })
        POPUP_CONFIRMATION_BUTTON_SUBMIT.click()
    }

    /**
     * This method make click in the button to add a new shift
     *
     */
    void makeClickInAddNewShift() {
        waitFor { BUTTON_ADD_NEW_SHIFT.isDisplayed() }
        BUTTON_ADD_NEW_SHIFT.click()
    }

    /**
     *  Fill the values in the second shift
     *
     */
    void fillValuesInTheSecondShift() {
        waitFor { SECOND_SHIFT_BUTTON_SELECTOR_DATE.isDisplayed() }
        SECOND_SHIFT_BUTTON_SELECTOR_DATE.click()

        sleep(1000)
        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath(optionDateCurrentDateLocator))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath(optionDateCurrentDateLocator))).click().build().perform();

        waitFor { SECOND_SHIFT_BUTTON_SELECTOR_START_HOUR.isDisplayed() }
        SECOND_SHIFT_BUTTON_SELECTOR_START_HOUR.click()
        waitFor { BUTTON_DONE_HOUR.isDisplayed() }
        BUTTON_DONE_HOUR.click()
        waitFor { SECOND_SHIFT_BUTTON_SELECTOR_FINISH_HOUR.isDisplayed() }
        SECOND_SHIFT_BUTTON_SELECTOR_FINISH_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_ARROW_INCREASE_HOUR.click()
        BUTTON_DONE_HOUR.click()
        waitFor { SECOND_SHIFT_BUTTON_DROPDOWN_JOB_DEFINITION.isDisplayed() }

        SECOND_SHIFT_BUTTON_DROPDOWN_JOB_DEFINITION.click()
        SECOND_SHIFT_OPTIONLIST_1_JOB_DEFINITION.click()
    }

//Shift Requirements functions

    void checkDetailsFormDisplayed() {
        assert jobRequirementsDropDown.isDisplayed() && dropdownContactInfo.isDisplayed()
    }

    void fillJobRequeremntsTemplateName() {
        jobRequirementsDropDown.value(JOB_TEMPLATE_NAME)
        requirementsTemplatesList[0].click()
    }

    void fillJobDescriptionField() {
        waitFor { WaitUtil.isAttributePresent(jobDescriptionField, "disabled") }
        jobDescriptionField.value(JOB_DESCRIPTION)
    }

    void fillJobSpecialRequirementsField() {
        jobSpecRequirementsField.value(JOB_SPECIAL_REQUIREMENTS)
    }

    void clickSaveRequirementsTemplateButton() {
        saveRequirementsTemplateButton.click()
    }

    void fillContactTemplateName() {
        dropdownContactInfo.value(CONTACT_NAME)
        contactNameAddButton.click()
    }

    void fillContactNumber() {
        contactNumberField.value(CONTACT_NUMBER)
    }

    void fillContactEmail() {
        contactEmailField.value(CONTACT_EMAIL)
    }

    void clickSaveContactTemplateButton() {
        saveContactTemplateButton.click()
    }

    void checkTemplateWasSaved() {
        waitFor(10, { templateSavedSuccessMessage.isDisplayed() })
    }

}