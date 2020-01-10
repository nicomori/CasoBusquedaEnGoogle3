package framework.src.Pages.TVS.old_pepe.ios.views

import framework.src.Pages.TVS.old_pepe.mobile.ios.IosBaseView
import io.appium.java_client.AppiumDriver
import io.appium.java_client.MobileElement
import io.appium.java_client.TouchAction
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.remote.RemoteWebElement

class LoginView extends IosBaseView {
    def isIOS(){(System.getProperty('appium_platformName')=="iOS")}

    static at = { "zenjob.LoginView"}//textFields.isEnabled()
    static content = {
        //email{ $(By.xpath("//XCUIElementTypeApplication[@name=\"ZENJOB\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField")) }
        email{ $(isIOS()?[name:"E-Mail"] : [name:"aa"])}
        password{ $('name':"Passwort")}
        //textFields{ $("name='TextFields, Uses of UITextField'")[0] }
        loginButton { $('name':"Einloggen")}
        registerButton { $('name':"Registrieren")}
        verstandenButton { $('name':"Verstanden")}
        allowNotification{ $('name':"Allow")}
        promotionButton{ $('name':"Schließen")}
        offerList{ $('class':"XCUIElementTypeCell")}
        ApplyOfferButton{ $('name':"Jetzt Bewerben")}
        signatureArea{ $('name':"Unterschrift")}
        masterworkButton{ $('name':"Meisterwerk!")}
//swiper needed
    }

    void setLogin(email,password){
        this.email<<email
        this.password<<password
    }

    void clickLoginButton(){
        loginButton.click()
    }

    void signatureButton(){
        signatureArea.click()
    }
    void masterworkButton(){
        masterworkButton.click()
    }

    void verstanden(){
        sleep(500)
        verstandenButton.click()
        sleep(1000)
        allowNotification.click()
        sleep(700)
        promotionButton.click()
    }

    void clickFirstOffer(){
        offerList[0].click()
        sleep(1000)
    }

    void clickApply(){
        ApplyOfferButton.click()
        sleep(200)
    }
    void clickSend(){
        ApplyOfferButton[0].click()
        sleep(200)
    }

    void scroll()
    {
        /*TouchAction(new AppiumDriver<MobileElement>(this.driver))
                .press(410,662)
                .moveTo(410,444)
                .release()
                .perform()*/
        // Java
        JavascriptExecutor js = (JavascriptExecutor) getBrowser().getDriver()
        Map<String, Object> params = new HashMap<>();
        params.put("direction", "up");
        //params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);

        sleep(2000)
    }

    void drawing()
    {
        /*TouchAction(new AppiumDriver<MobileElement>(this.driver))
                .press(410,662)
                .moveTo(410,444)
                .release()
                .perform()*/
        // Java
        JavascriptExecutor js = (JavascriptExecutor) getBrowser().getDriver()
        Map<String, Object> params = new HashMap<>();
        if(System.getProperty('real_mobile')==true)
            params.put("direction", "left");
        else
            params.put("direction", "up");
        //params.put("element", ((RemoteWebElement) element).getId());
        js.executeScript("mobile: swipe", params);

        sleep(2000)
    }

}
