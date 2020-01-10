package framework.src.Pages.CompanyApp.modules

import geb.Module

class LoadingAnimationModule extends Module {

    static content = {
       element { $('[class^="Circle__"]') }
    }

}
