package framework.src.Pages.TVS.old_pepe.mobile.driver

import groovy.util.logging.Slf4j
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.android.AndroidElement
import io.appium.java_client.ios.IOSDriver
import io.appium.java_client.ios.IOSElement
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.Platform
import org.openqa.selenium.firefox.FirefoxDriver
import org.openqa.selenium.interactions.Actions
import org.openqa.selenium.interactions.KeyInput
import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.LocalFileDetector
import org.openqa.selenium.remote.RemoteWebDriver
import org.uiautomation.ios.IOSCapabilities
import org.uiautomation.ios.client.uiamodels.impl.RemoteIOSDriver
import org.uiautomation.ios.communication.device.DeviceType
import framework.src.TestConstants

/**
 * Created by gmueksch on 12.08.14.
 * Based on the your System property settings this class creates the remote driver impl
 * to connect to your selenium server
 * is used in the GebConfig.groovy by geb or directly if you prefere writing old style unit tests
 * TODO: automatically figure out what type of server is running, could be done with json requests
 * TODO: refactor the if/then construct
 */
@Slf4j
class GebMobileDriverFactory {

    public static String FRAMEWORK_APPIUM = "appium"
    public static String FRAMEWORK_SELENDRIOD = "selendroid"
    public static String FRAMEWORK_IOSDRIVER = "iosdriver"
    public static String FRAMEWORK_SELENIUM = "selenium"

    public static URL getURL(String url) {
        String seleniumUrl = System.getProperty("selenium.url")
        String no = System.getProperty("apium.tcp.5554")
        if (seleniumUrl) return new URL(seleniumUrl)
        else return new URL(url)
    }

    static RemoteWebDriver createMobileDriverInstance(boolean sendToBS) {
        println('Starting the creation of the mobile driver')
        println('Status of the variable to use appium: '+useAppium())

        if (useAppium()) {
            println('sssssssssssss jjjjjj455555')

            DesiredCapabilities capa = new DesiredCapabilities()
            //set default platform to android
            capa.setCapability("platformName", "iOS");

            System.properties.each { String k, v ->
                def m = k =~ /^appium_(.*)$/
                if (m.matches()) {
                    log.info "setting appium property: $k , $v"
                    capa.setCapability(m[0][1], v)
                }
            }
            def driver

            println('sssssssssssss jjjjjj77777777')
            println('sssssssssssss capa.getCapability("platformName")  '+capa.getCapability("platformName"))

            if (capa.getCapability("platformName") == "Android") {

                println("Create Appium Driver for Android")


                if(sendToBS){
                    println("Setting for execute the test in BROWSERSTACK")
                    String USERNAME = TestConstants.BrowserStackCredentials.BREWSERSTACK_USER
                    String AUTOMATE_KEY = TestConstants.BrowserStackCredentials.BREWSERSTACK_AUTOMATE_KEY
                    String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"



                    System.setProperty("webdriver.gecko.driver", "geckodriver")


                    capa.setCapability("marionette", true);

                    capa.setCapability("os", "android");
                    capa.setCapability("os_version", "6.0");
                    capa.setCapability("device", "Samsung Galaxy Note 4");
                    capa.setCapability("real_mobile", "true");
                    capa.setCapability("browserstack.appium_version", "1.8.0");
                    capa.setCapability("browserstack.local", "false");
                    capa.setCapability("browserstack.debug", "true");

                    capa.setCapability("browserstack.project", "TVS");
                    capa.setCapability("browserstack.build", "build 4.5");
                    capa.setCapability("browserstack.name", "Android App");



                    println('sdsdsddsdsd§§§§1111  111')

//                    capa.setCapability("app", "bs://868fce1e545fce3f35865d6427631dd95c530eb1");
                    capa.setCapability("app", "bs://868fce1e545fce3f35865d6427631dd95c530eb1");



                    AndroidDriver<AndroidElement> driverAndroid = new AndroidDriver<AndroidElement>(new java.net.URL(URL), capa)

//                    driver = new AndroidDriver(new URL("http://"+USERNAME+":"+AUTOMATE_KEY+"@"+config.get("server")+"/wd/hub"), capabilities);

//                    driver = new IOSDriver<IOSElement>(new java.net.URL(URL), capa)

                    println('sdsdsddsdsd§§§§1111  22222222')

//                    driver = new AndroidDriver<AndroidElement>(getURL("http://localhost:4723/wd/hub"), capa)

                    return driverAndroid

                }else{
                    println("Setting for execute the test in the local")
                    capa.setCapability("platform", Platform.ANDROID)
                    if( appPackage() ) capa.setCapability("appPackage", appPackage())
                    if (!capa.getCapability("deviceName"))
                        capa.setCapability("deviceName", "Android");

                    try {
                        driver = new AndroidDriver<AndroidElement>(getURL("http://localhost:4723/wd/hub"), capa)
                        log.info("Driver created: $driver.capabilities")

                        return driver
                    } catch (e) {
                        //
                        log.error("eXC: $e.message", e)
                    }
                }



            }else{
                log.info("Create Appium IOSDriver")
                if(sendToBS){
                    log.info("SETTING UP FOR BROWSERSTACK")

                    String USERNAME = TestConstants.BrowserStackCredentials.BREWSERSTACK_USER
                    String AUTOMATE_KEY = TestConstants.BrowserStackCredentials.BREWSERSTACK_AUTOMATE_KEY
                    String URL = "http://" + USERNAME + ":" + AUTOMATE_KEY + "@hub-cloud.browserstack.com/wd/hub"

                    capa.setCapability("os_version", "12");
                    capa.setCapability("device", "iPhone 8");
                    capa.setCapability("real_mobile", "true");
                    capa.setCapability("browserstack.local", "false");
                    capa.setCapability("browserstack.debug", "true");
                    capa.setCapability("app", "bs://f9caaa3b3beaa6fbea3dbfa9335691f4872cd6b0");

                    driver = new IOSDriver<IOSElement>(new java.net.URL(URL), capa)

                }
                else{
                    log.info("SETTING UP LOCAL")
                    capa.setCapability("deviceName", 'iPhone XR')
                    capa.setCapability("automationName", 'XCUITest')
                    capa.setCapability("app", "/Users/nicolasmori/Documents/zenjob.app")
                    capa.setCapability("udid", '8B20CBB5-E35D-4BF9-AC54-5248957E7528')
//                    capa.setCapability("udid", '29639B6E-B2DF-41D0-B065-DA72558C744C')
                    driver = new IOSDriver<IOSElement>(getURL("http://localhost:4723/wd/hub"), capa)
                }

                driver.setFileDetector(new LocalFileDetector())
                return driver

            }



            if (!driver) throw new RuntimeException("Appiumdriver could not be started")
        } else if (useIosDriver()) {
            DesiredCapabilities capa = new DesiredCapabilities()
            capa.setCapability(IOSCapabilities.BUNDLE_NAME, appPackage())
            if (appVersion())
                capa.setCapability(IOSCapabilities.BUNDLE_VERSION, appVersion())
            capa.setCapability(IOSCapabilities.DEVICE, DeviceType.iphone)
            //try for permissions
            //capa.setCapability("useNewWDA", true);

            System.properties.each { String k, v ->
                def m = k =~ /^iosdriver_(.*)$/
                if (m.matches()) {
                    log.info "setting ios property: $k , $v"
                    capa.setCapability(m[0][1], v)
                }
            }

            IOSCapabilities iCapa = new IOSCapabilities(capa.asMap())

            new RemoteIOSDriver(getURL("http://localhost:5555/wd/hub/"), iCapa)
            //new RemoteWebDriver(getURL("http://localhost:5555/wd/hub/"), capa)

        } else if (System.properties.framework == FRAMEWORK_SELENIUM) {
            DesiredCapabilities capa = DesiredCapabilities.firefox()
            System.properties.each { String k, v ->
                def m = k =~ /^selenium_(.*)$/
                if (m.matches()) {
                    log.info "setting ios property: $k , $v"
                    capa.setCapability(m[0][1], v)
                }
            }
            def selenium = new RemoteWebDriver(getURL("http://localhost:4444/wd/hub/"),capa)
            selenium.setFileDetector(new LocalFileDetector())
            return selenium
        } else {
            throw new Exception("Set Systemproperty 'framework' to selendroid or appium")
        }

    }

    public static boolean useAppium() {
        System.properties.framework == FRAMEWORK_APPIUM
    }

    public static boolean useSelendroid() {
        System.properties.framework == FRAMEWORK_SELENDRIOD
    }

    public static boolean useIosDriver() {
        System.properties.framework == FRAMEWORK_IOSDRIVER
    }

    public static String appPackage() {
        System.properties.'appUT.package'
    }

    public static String appVersion() {
        System.properties.'appUT.version'
    }

    /*  Test Helper Methods */
    /**
     *
     * @param framework
     * @param map the capabilities to add
     */
    public static void setFrameWork(String framework, def map = null) {
        System.setProperty("framework", framework)
        map?.each { k, v ->
            def key = "${framework}_${k}"
            if (k in ['appUT.package', 'appUT.version']) System.setProperty(k, v)
            else if(System.getProperty(key,null)==null) System.setProperty(key, v)
        }
    }

    /**
     * Convinient Method to set Framework and Capabilities for ...
     * @param map
     */
    public static void setIosDriver(def map) {
        setFrameWork(FRAMEWORK_IOSDRIVER, map)
    }

    /**
     * Convinient Method to set Framework and Capabilities for ...
     * @param map
     */
    public static void setAppium(def map) {
        setFrameWork(FRAMEWORK_APPIUM, map)
    }

    public static void setAppiumAndroid(def map = []){
        map.platformName = map.platformName ?: 'Android'
        map.appActivity = map.appActivity ?: "MainActivity"
        setAppium(map)
    }

    /**
     * Convinient Method to set Framework and Capabilities for ...
     * @param map
     */
    public static void setAppiumIos(def map) {
        if (!map) map = []
        map.platformName = map.platformName ?: 'iOS'
        map.deviceName = map.deviceName ?: 'iPhone 6'
        setFrameWork(FRAMEWORK_APPIUM, map)
    }

    /**
     * Convinient Method to set Framework and Capabilities for ...
     * @param map
     */
    public static void setSelendroid(def map) {
        setFrameWork(FRAMEWORK_SELENDRIOD, map)
    }


}
