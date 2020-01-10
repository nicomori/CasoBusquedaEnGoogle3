package framework.src.Pages.TVS.old_pepe.android.activities

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

/**
 * Created by pvh on 13/12/18
 */
class HomeActivity extends AndroidBaseActivity {

    static content = {
        pageTitle { $("#title").text() }
        //registerButton { $("#button_registration") }
        //zenjob.android.debug:id/button_registration

    }

    String getActivityName() {
        return "zenjob.android.app.ui.home.HomeActivity";
    }
}
