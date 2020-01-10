package framework.src.Pages.TVS.old_pepe.android.activities

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity


/**
 * Created by pvh on 13/12/18
 */
class LoginActivity extends AndroidBaseActivity {

    static content = {
        pageTitle { $("#title").text() }
        continueButton { $('id':'login')}
        userEmail{ $('id':'email')}
        userPassword{ $('id':'password')}
        backArrowButton{ $('id':'back_arrow')}

    }

    String getActivityName() {
        return "zenjob.android.feature.login.LoginActivity";
    }

    void clickLoginButton(){
        continueButton.click()
    }

    void setLogin(email, String password){
        userEmail << email
        userPassword << password
    }

    void clickBackArrow()
    {
        backArrowButton.click()
    }

}
