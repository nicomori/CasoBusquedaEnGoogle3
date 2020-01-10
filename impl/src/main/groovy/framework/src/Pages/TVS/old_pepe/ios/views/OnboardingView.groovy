package framework.src.Pages.TVS.old_pepe.ios.views

import framework.src.Pages.TVS.old_pepe.mobile.ios.IosBaseView

class OnboardingView extends IosBaseView {

    static at = { "zenjob.onboardingView"}//textFields.isEnabled()

    static content = {
        //email{ $(By.xpath("//XCUIElementTypeApplication[@name=\"ZENJOB\"]/XCUIElementTypeWindow[1]/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeOther/XCUIElementTypeScrollView/XCUIElementTypeOther/XCUIElementTypeOther[2]/XCUIElementTypeTextField")) }
        email{ $('name':"E-Mail")}
        password{ $('name':"Passwort")}
        //textFields{ $("name='TextFields, Uses of UITextField'")[0] }
        loginButton { $('name':"Einloggen")}
        registerButton { $("#Registrieren")}
        overEighteen{ $('class':"XCUIElementTypeButton")[3]}
        continueButton { $('name':"Weiter")}
        student{ $('class':"XCUIElementTypeButton")[1]}

        listNation { $('name':'Nationalität')}
        listNationClose { $('name':'closeButton')}
        nationality { $('class':'XCUIElementTypePickerWheel')}

        listCity { $('name':'Stadt')}
        //nationalityPicker { $('id':'champi_picker_selector')}

        userName{ $('name':'Vorname')}
        userSurname{ $('name':'Nachname')}
        userEmail{ $('name':'Deine E-Mail-Adresse')}//	XCUIElementTypeTextField
        userPassword{ $('name':'Bitte erstelle Dein Passwort')}// XCUIElementTypeSecureTextField
        userTerms{ $('name':'checkcircle 0')[0]}
        userTerms2{ $('name':'checkcircle 0')[1]}
    }

    void setLogin(email,password){
        this.email<<email
        this.password<<password
    }

    void clickLoginButton(){
        loginButton.click()
    }

    void clickContinue(){
        sleep(300)
        continueButton.click()
    }

    void clickListNation(){
        sleep(300)
        listNation.click()
    }

    void setNationality(country){
        //nationalityPicker = natio
        sleep(300)
        nationality<<country
        sleep(300)
        listNationClose.click()
        //n
    }

    void clickListCity(){
        sleep(300)
        listCity.click()
    }

    void setCity(city){
        //nationalityPicker = natio
        sleep(300)
        nationality<<city
        sleep(300)
        listNationClose.click()
        //nationalityPicker[4].click()
    }


    void setNames(String name, String surname){
        sleep(300)
        userName<<name
        userSurname<<surname
    }
    void checkTerms(){
        sleep(300)
        userTerms.click()
    }

    void setLogin(String email, String password){
        userEmail<<email
        userPassword<<password
        userTerms2.click()
    }
}
