package framework.src.Util

import geb.Browser
import geb.binding.BindingUpdater
import org.openqa.selenium.By
import org.openqa.selenium.WebDriver
import org.openqa.selenium.WebElement
import org.testcontainers.containers.BrowserWebDriverContainer


import org.openqa.selenium.remote.DesiredCapabilities
import org.openqa.selenium.remote.RemoteWebDriver

import java.text.SimpleDateFormat
import java.time.YearMonth
import java.util.concurrent.TimeUnit

class BrowserSelector {
    Binding scriptBinding

    BrowserSelector(Binding binding) {
        this.scriptBinding = binding
    }

    Browser selectABrowser(){
            // create browser container and expose it to the scripts through the scirpt binding
            BrowserWebDriverContainer browserContainer = new BrowserWebDriverContainer()
                    .withDesiredCapabilities(BrowserCapailabilities.returnCapailabilities())

            scriptBinding.setVariable('browserContainer', browserContainer)

            String someVariableName = 'example of set variable between Step and Pages'

            scriptBinding.setVariable('thisIsTheVariableJuan', someVariableName)
            scriptBinding.setVariable('thisIsTheVariableJuan2', someVariableName)

            println('Starting execution in Docker containers')

            browserContainer.start()

            // open vnc viewer
            if (zenjob.testautomation.config.Configuration.instance.getConfigProperty('showExecution') == 'true') {
                Runtime.runtime.exec('open -W ' + browserContainer.vncAddress)
            }

            // initialize the browser
            Browser browser = new Browser()
            browser.driver = browserContainer.webDriver
            BindingUpdater bindingUpdater = new BindingUpdater(scriptBinding, browser)
            bindingUpdater.initialize()

            // set up timeouts, time in seconds
            final int ELEMENT_SEARCH_TIMEOUT = 10
            final int PAGE_LOAD_TIMEOUT = 2 * 60 // 2 minutes
            final int SCRIPT_EXECUTION_TIMEOUT = 10 * 60 // 10 minutes
            WebDriver.Options options = browser.driver.manage()



            browser.driver.manage().window().maximize()

        WebDriver.Timeouts timeouts = options.timeouts()
            timeouts
                    .implicitlyWait(ELEMENT_SEARCH_TIMEOUT, TimeUnit.SECONDS)
                    .pageLoadTimeout(PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS)
                    .setScriptTimeout(SCRIPT_EXECUTION_TIMEOUT, TimeUnit.SECONDS)

            return browser
    }
}