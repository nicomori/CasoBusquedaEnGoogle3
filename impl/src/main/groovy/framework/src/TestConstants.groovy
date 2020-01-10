package framework.src

import org.openqa.selenium.remote.DesiredCapabilities

class TestConstants {

    private static class DefaultBrowser{
        private static final DesiredCapabilities CHROME_BROUSER = DesiredCapabilities.chrome()
    }

    static class DefaultEnviroment{
        public static final String QA_ENVIROMENT = 'staging'
    }

    static class DefaultApplication{
        public static final String OPS_APPLICATION = 'ops'
    }

    static class BrowserStackCredentials{
        public static final String BREWSERSTACK_USER = 'robertfehrmann1'
        public static final String BREWSERSTACK_AUTOMATE_KEY = 'ih9dtthwMQsP8HYUdwsq'
    }


}
