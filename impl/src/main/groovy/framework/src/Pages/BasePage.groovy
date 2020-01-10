package framework.src.Pages

import geb.Page
import geb.error.RequiredPageContentNotPresent

class BasePage extends Page {

   static atCheckWaiting = true

    static at = { true }
    static url = ""
    static int valueIntoTheCompanyApp = 1000
    static int valueIntoTheOpsApp = 0

    static int orderNumberCreated = 0
    static int jobNumberCreated = 0

    static int valuesToUseInTheTestDate = 3
    static int valuesToUseInTheTestHourCheckIn = 10
    static int valuesToUseInTheTestHourCheckout = 16
    static int valuesToUseInTheTestMinutesCheckout = 58



    static String firstNameOfTheTalent = "null"

    boolean noErrors() {
        try {
            return !errors.hasErrors()
        }
        catch (RequiredPageContentNotPresent e) {
            return true
        }
    }

    boolean hasError(String err) {
        try {
            return errors.check([err])
        }
        catch (RequiredPageContentNotPresent e) {
            return true
        }
    }

    boolean hasErrors(String err) {
        try {
            return errors.hasErrors()
        }
        catch (RequiredPageContentNotPresent e) {
            return true
        }
    }

}
