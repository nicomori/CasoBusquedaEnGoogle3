package framework.src.Pages.TVS.old_pepe.com.google.android

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

class CameraActivity extends AndroidBaseActivity{

    static content = {
        shutter{ $("#shutter_button")}
        done{ $("#done_button")}
        takePicture {
            shutter.click()
            done.click()
        }
    }

    @Override
    String getActivityName() {
        null
    }


}
