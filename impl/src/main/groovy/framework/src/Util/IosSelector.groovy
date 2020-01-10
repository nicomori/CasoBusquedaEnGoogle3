package framework.src.Util

import framework.src.Pages.TVS.old_pepe.mobile.driver.GebMobileDriverFactory
import geb.Browser
import geb.binding.BindingUpdater


class IosSelector {
    Binding scriptBindingMobile

    IosSelector(Binding binding) {
        this.scriptBindingMobile = binding
    }

    Browser startingDriverIOS(){
        println("Starting iOS DRIVER Creation")
        System.setProperty("framework", "appium")
//        System.setProperty("appium_platformName", "bs")
        System.setProperty("appium_platformName", "iOS")


        println('sssssssssssss 1111')
        def bindingUpdater2 = new BindingUpdater(null,null)

        println('sssssssssssss 2222')
        Browser theBrowserMobile

        println('sssssssssssss 333333')

            //if(!scriptBindingMobile.hasVariable('browser')) {
        theBrowserMobile = new Browser()
                //call createMobileDriverInstance(true) => boolean to test with BrowserStack

        println('sssssssssssss 4444444')
        boolean sendExecutionTorunInBrowserStack = false


        println('sssssssssssss 55555555')

        theBrowserMobile.driver = GebMobileDriverFactory.createMobileDriverInstance(sendExecutionTorunInBrowserStack)

        println('sssssssssssss 666666')
                bindingUpdater2 = new BindingUpdater(scriptBindingMobile, theBrowserMobile)

        println('sssssssssssss 77777')
                bindingUpdater2.initialize()


        println('sssssssssssss 8888')
           /* }else {
                theBrowser = browser
            }*/

            return theBrowserMobile
    }
}