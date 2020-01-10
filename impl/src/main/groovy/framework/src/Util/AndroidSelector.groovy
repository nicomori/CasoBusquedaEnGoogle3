package framework.src.Util

import framework.src.Pages.TVS.old_pepe.mobile.driver.GebMobileDriverFactory
import geb.Browser
import geb.binding.BindingUpdater

class AndroidSelector {
    Binding scriptBinding

    AndroidSelector(Binding binding) {
        this.scriptBinding = binding
    }

    Browser startingDriverAndroid(){
        println("Starting Android DRIVER Creation")
        System.setProperty("framework", "appium")
        System.setProperty("appium_platformName", "Android")


        System.setProperty("appium_appWaitActivity", "zenjob.android.feature.intro.IntroActivity")
        System.setProperty("appium_app", "/Users/nicolasmori/Documents/app-debug.apk")
        System.setProperty("appium_deviceName", "Nexus 5 API 24")

        def bindingUpdater2 = new BindingUpdater(null,null)
        Browser theBrowserMobile

        theBrowserMobile = new Browser()

        println('Starting to verify if we send the execution to BrowserStack')
        boolean sendExecutionTorunInBrowserStack = false


        println('Starting to create the mobile driver.')
        theBrowserMobile.driver = GebMobileDriverFactory.createMobileDriverInstance(sendExecutionTorunInBrowserStack)

        println('Mobile driver created successfully')
        bindingUpdater2 = new BindingUpdater(scriptBinding, theBrowserMobile)

        println('Starting the driver for Geb.')
        bindingUpdater2.initialize()

        println('jjjjjjjjjjjjj 33333')

        return theBrowserMobile
    }
}