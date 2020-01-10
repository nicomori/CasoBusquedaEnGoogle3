package zenjob.testautomation.exceptions

class TAFConfigurationPropertyNotFound extends Exception {
    TAFConfigurationPropertyNotFound(String propertyName) {
        super("The property ${propertyName} has not been found.")
    }
}
