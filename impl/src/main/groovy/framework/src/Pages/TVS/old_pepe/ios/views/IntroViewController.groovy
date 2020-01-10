package framework.src.Pages.TVS.old_pepe.ios.views

import framework.src.Pages.TVS.old_pepe.mobile.ios.IosBaseView

class IntroViewController extends IosBaseView {

    static at = { "introView"}//textFields.isEnabled()
    def isIOS(){(System.getProperty('appium_platformName')=="iOS")}
    static content = {
        mytitle { $("#intro_01") }
        login{ $(isIOS()?['name':"Du bist bereits Zenjob-Talent?"]:['id':'button_login']) }
        register{ $('name':"Du bist neu bei Zenjob?") }
        //textFields{ $("name='TextFields, Uses of UITextField'")[0] }
    }
}
