package framework.src.Pages.TVS.old_pepe.com.htc.camera

import framework.src.Pages.TVS.old_pepe.mobile.android.AndroidBaseActivity

/**
 * HTC One Camera
 */
class CameraActivity extends AndroidBaseActivity {

    static content = {
        takePicture {
            def dim = getScreenDimension(driver)
            performTap(dim.width / 2, dim.height / 2)
            sleep 200
            performTap(dim.width - 100, dim.height / 2)
            sleep 200
            performTap(dim.width - 100, dim.height / 2)
        }
    }
    @Override
    String getActivityName() {
        null
    }

}
