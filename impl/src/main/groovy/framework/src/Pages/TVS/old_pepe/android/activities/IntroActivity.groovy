package framework.src.Pages.TVS.old_pepe.android.activities

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

/**
 * Created by pvh on 13/12/18
 */
class IntroActivity extends AndroidBaseActivity {

    static content = {
        pageTitle { $("#title").text() }
        registrationButton { $('id':'button_registration')}
        loginButton { $('id':'button_login')}
    }

    String getActivityName() {
        return "zenjob.android.feature.intro.IntroActivity";
    }

    void clickRegister(){
        registrationButton.click()
    }

    void clickLogin(){
        loginButton.click()
    }
}
