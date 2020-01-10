package framework.src.Steps

import framework.src.ApplicationState

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)


Before { scenario ->
    println('this happen in the BEFORE METHOD')



}

After { scenario ->
    println('this happen in the AFTER METHOD')
    //println(thisIsTheVariableJuan)

    browser.getDriver().quit()
//    browserContainer.stop()


}

ApplicationState applicationState = new ApplicationState()
binding.setVariable('state', applicationState)
binding.setVariable("ops","https://staging-main.zenjob.org")