package zenjob.testautomation.config

import zenjob.testautomation.exceptions.TAFConfigurationPropertyNotFound

@Singleton
class Configuration {
    final String PROPERTY_FILE_NAME = 'environment.properties'

    File propertiesFile = new File(PROPERTY_FILE_NAME)

    Properties properties = new Properties()

    /**
     *  1. check the system's environment variables
     *  2. check the properties file
     *  3. use the default if set
     */

    String getConfigProperty(String propertyName, String defaultValue = null) {
        if (System.getenv(propertyName) != null) {
            return System.getenv(propertyName)
        }

        else if (propertiesFile.exists()) {
            // located in the top directory
            properties.load(propertiesFile.newInputStream())

            if (properties.getProperty(propertyName) != null) {
                return properties.getProperty(propertyName)
            }
        }

        else if (defaultValue != null) {
            return defaultValue
        }

        else {
            throw new TAFConfigurationPropertyNotFound(propertyName)
        }
    }
}
