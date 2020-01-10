package framework.src.Util

import geb.navigator.Navigator

class WaitUtil {

    static boolean isAttributePresent(Navigator element, String attr) {
        Boolean result = false;
        try {
            String value = element.getAttribute(attr)
            if (value.isEmpty()) {
                result = true
            }
        } catch (Exception e) {
        }

        return result
    }

    static boolean elementNotDisplayed(Navigator element) {
        try {
            return !element.isDisplayed()
        } catch (Exception e) {
            return true
        }
    }

}
