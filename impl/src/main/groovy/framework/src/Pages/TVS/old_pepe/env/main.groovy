package framework.src.Pages.TVS.old_pepe.env

import geb.Browser
import geb.binding.BindingUpdater
import framework.src.Pages.TVS.old_pepe.mobile.GebMobileNavigatorFactory
import org.openqa.selenium.WebDriver
import org.openqa.selenium.remote.DesiredCapabilities
//import org.testcontainers.containers.BrowserWebDriverContainer
//import zenjob.testautomation.config.Configuration

//import geb.mobile.GebMobileNavigatorFactory
import framework.src.Pages.TVS.old_pepe.mobile.driver.GebMobileDriverFactory
//import zenjob.testautomation.ApplicationState

import java.util.concurrent.TimeUnit

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

// init Testcontainers
//BrowserWebDriverContainer browserContainer = new BrowserWebDriverContainer()
//        .withDesiredCapabilities(DesiredCapabilities.chrome())

//init driver




// init cucumber
def bindingUpdater
def theBrowser


System.setProperty("framework", "appium")
System.setProperty("appium_platformName", "Android")

System.setProperty("appium_appWaitActivity", "zenjob.android.feature.intro.IntroActivity")

System.setProperty("appium_app", "/Users/pascalvanhuffelen/Documents/zenjob-android/app/build/outputs/apk/debug/app-debug.apk")
System.setProperty("appium_deviceName", "Nexus 5 API 24")
println "============================="
//println "============================="
//println "============================="
//println "============================="
//println System.getProperties()
//println System.getenv()
//println "============================="

if(!binding.hasVariable('driver')) {
    theBrowser = new Browser()
    theBrowser.driver = GebMobileDriverFactory.createMobileDriverInstance()

    bindingUpdater = new BindingUpdater(binding, theBrowser)
    bindingUpdater.initialize()
}

else {
    theBrowser = browser
}






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

}

After { scenario ->
    //browserContainer.stop()
    bindingUpdater?.remove()
}
// init ApplicationState
//ApplicationState applicationState = new ApplicationState()
//binding.setVariable('state', applicationState)

// init configuration ...
