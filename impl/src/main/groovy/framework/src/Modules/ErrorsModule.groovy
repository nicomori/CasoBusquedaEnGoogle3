package framework.src.Modules
import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.By

class ErrorsModule extends Module {
    static content = {
        actualErrorMessages { $(By.xpath("//div[@class='alert alert-danger']")) }
        actualInfoMessages { $(By.xpath("//div[@class='alert alert-info']")) }
    }

    boolean check(List<String> expectedErrorMessages) {
        Navigator navigatorWithoutField = actualErrorMessages.find(By.xpath('.//ul//li'))
        Navigator navigatorWithField = actualErrorMessages.find(By.xpath('.//ul//li[@data-field-id]'))
        Navigator navigator
        Navigator navigatorMessages
        boolean allExpectedErrorsFound = true

        if (navigatorWithField.isEmpty()) {
            if(navigatorWithoutField.isEmpty()) {
                navigator = actualErrorMessages
            }
            else {
                navigator = navigatorWithoutField
            }
        }
        else {
            navigator = navigatorWithField
        }


            expectedErrorMessages.each {errorMessage ->
            boolean errorFound = false
            for (int i = 1; i <= navigator.size(); i++) {

                if (navigatorWithField.isEmpty()) {
                    if(navigatorWithoutField.isEmpty()) {
                        navigatorMessages =navigator
                    }
                    else {
                        navigatorMessages = actualErrorMessages.find(By.xpath(".//ul//li[${i}]"))
                    }
                }
                else {
                    navigatorMessages = actualErrorMessages.find(By.xpath(".//ul//li[@data-field-id][${i}]"))
                }

                if (checkErrorMessages([errorMessage], navigatorMessages)) {
                    errorFound = true
                    break
                }
            }
            allExpectedErrorsFound = allExpectedErrorsFound && errorFound
        }
        return allExpectedErrorsFound
    }


    boolean hasErrors() {
        actualErrorMessages.find(By.xpath('.//ul//li')) || actualErrorMessages.text()
    }

    static Set<String> extractErrorMessages(Navigator errorMessages) {
        Set<String> extracted = []
        errorMessages.each { errorMessage ->
            //extracted << errorMessage.text().replaceAll("\"", "")
            extracted << errorMessage.text()
        }

        extracted
    }

    static boolean checkErrorMessages(List<String> expectedErrorMessages, Navigator actualErrorMessages) {
        // set so that order of error messages does not matter
        extractErrorMessages(actualErrorMessages) == (expectedErrorMessages as Set)
    }

    static boolean checkInfoMessagesLike(List<String> expectedErrorMessages, Navigator actualErrorMessages) {
        // set so that order of error messages does not matter
        println(extractErrorMessages(actualErrorMessages)[0])
        println((expectedErrorMessages as Set)[0])
        (extractErrorMessages(actualErrorMessages))[0].contains((expectedErrorMessages as Set)[0])
    }

    boolean hasInfo() {
        actualInfoMessages.find(By.xpath('.//ul//li')) || actualErrorMessages.text()
    }

    boolean checkInfoLike(List<String> expectedInfoMessages) {
        Navigator navigator = actualInfoMessages.find(By.xpath('.//ul//li'))
        if (navigator.isEmpty()) {
            navigator = actualInfoMessages
        }
        checkInfoMessagesLike(expectedInfoMessages, navigator)
    }
}
