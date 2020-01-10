package framework.src.Pages.CompanyApp.modules

import geb.Module

class ToastSuccessModule extends Module {
    static base = { $('[class*="SuccessToastWrapper"]') }
    static content = {
        message { $('[class*="Toast__Message"]') }
        closeButton { $('[class*=Toast__Close"]') }
    }
}
