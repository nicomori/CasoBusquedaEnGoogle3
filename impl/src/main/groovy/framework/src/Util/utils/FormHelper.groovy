package framework.src.Util.utils

import geb.navigator.Navigator

class FormHelper {
    static Set<String> convertTagsToList(Navigator div) {
        Set<String> tagList = []
        div.find('span').each {
            tagList.add(it.text().trim())
        }
        tagList
    }

    static Set<String> extractErrorMessages(Navigator errorMessages) {
        Set<String> extracted = []
        errorMessages.each { errorMessage ->
            extracted << errorMessage.text()
        }

        extracted
    }

    static boolean checkErrorMessages(List<String> expectedErrorMessages, Navigator actualErrorMessages) {
        extractErrorMessages(actualErrorMessages) == (expectedErrorMessages as Set)
        // set so that order of error messages does not matter
    }
}
