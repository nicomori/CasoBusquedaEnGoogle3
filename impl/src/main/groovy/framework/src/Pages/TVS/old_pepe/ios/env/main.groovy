package framework.src.Pages.TVS.old_pepe.ios.env

import geb.Browser
import geb.binding.BindingUpdater

//import org.testcontainers.containers.BrowserWebDriverContainer
//import zenjob.testautomation.config.Configuration

//import geb.mobile.GebMobileNavigatorFactory
import framework.src.Pages.TVS.old_pepe.mobile.driver.GebMobileDriverFactory
//import zenjob.testautomation.ApplicationState

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

// init Testcontainers
//BrowserWebDriverContainer browserContainer = new BrowserWebDriverContainer()
//        .withDesiredCapabilities(DesiredCapabilities.chrome())

//init driver




// init cucumber
def bindingUpdater
def theBrowser



//println "============================="
//println "============================="
//println "============================="
//println System.getProperties()
//println System.getenv()
//println "============================="








Before {scenario ->/*
    browserContainer.start()
    println browserContainer.vncAddress
    // open vnc viewer
    if (Configuration.instance.getConfigProperty('showExecution') == 'true') {
        Runtime.runtime.exec('open -W ' + browserContainer.vncAddress)
    }

    if(!binding.hasVariable('browser')) {
        theBrowser = new Browser()
        theBrowser.driver = browserContainer.webDriver
        bindingUpdater = new BindingUpdater(binding, theBrowser)
        bindingUpdater.initialize()
    }

    else {
        theBrowser = browser
    }

    // set up timeouts, time in seconds
    final int ELEMENT_SEARCH_TIMEOUT = 2
    final int PAGE_LOAD_TIMEOUT = 2 * 60 // 2 minutes
    final int SCRIPT_EXECUTION_TIMEOUT = 10 * 60 // 10 minutes

    WebDriver.Options options = theBrowser.driver.manage()
    WebDriver.Timeouts timeouts = options.timeouts()
    timeouts
            .implicitlyWait(ELEMENT_SEARCH_TIMEOUT, TimeUnit.SECONDS)
            .pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
            .setScriptTimeout(SCRIPT_EXECUTION_TIMEOUT, TimeUnit.SECONDS)*/
    System.setProperty("framework", "appium")
    System.setProperty("appium_platformName", "iOS")
    System.setProperty("appium_deviceName", 'iPhone XR')
    System.setProperty("appium_automationName", 'XCUITest')
    System.setProperty('appUT.package', 'UICatalog')//,simulator:'true', language:'en')
    System.setProperty("appium_app", "/Users/pascalvanhuffelen/Documents/zenjob.app")
    System.setProperty("appium_udid", '29639B6E-B2DF-41D0-B065-DA72558C744C')

    println "============================="
    if(!binding.hasVariable('driver')) {
        theBrowser = new Browser()
        theBrowser.driver = GebMobileDriverFactory.createMobileDriverInstance()

        bindingUpdater = new BindingUpdater(binding, theBrowser)
        bindingUpdater.initialize()
    }

    else {
        theBrowser = browser
    }
}

After { scenario ->
    //browserContainer.stop()
    bindingUpdater?.remove()
}
// init ApplicationState
//ApplicationState applicationState = new ApplicationState()
//binding.setVariable('state', applicationState)

// init configuration ...
