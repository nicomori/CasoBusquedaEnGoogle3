package framework.src.Util

import org.openqa.selenium.remote.DesiredCapabilities

class TestConstants2 {
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

    static class CredencialsValuesForCompanyApp{
        public static final String COMPANY_USER_STORE_MANAGER_USERNAME = 'store.manager-notrealone@zenjob.com'
        public static final String COMPANY_USER_GLOBAL_MANAGER_USERNAME = 'nicolas.mori@zenjob.com'
        public static final String COMPANY_USER_STORE_CONTROLLER_USERNAME = 'store.controller-notrealone@zenjob.com'
        public static final String COMPANY_USER_GLOBAL_CONTROLLER_USERNAME = 'global.controller-notrealone@zenjob.com'
        public static final String GLOBAL_PASSWORD = '12345678'
    }

    static class UsersTalent{
        public static final String TALENT_USER_FIRSTNAME = 'auto'
        public static final String TALENT_USER_LASTNAME = 'test'
        public static final String TALENT_USER_EMAIL = 'marie.aurhammer+01@zenjob.com'
        public static final String TALENT_USER_EMAIL_USERNAME_PART_1 = 'nicolas.mori+'
        public static String TALENT_USER_EMAIL_USERNAME_PART_MIDDLE = '0001'
        public static final String TALENT_USER_EMAIL_DOMAIN_NAME_PART_2 = '@zenjob.com'
        public static final String TALENT_USER_PASSWORD = '12345678'
    }
}
