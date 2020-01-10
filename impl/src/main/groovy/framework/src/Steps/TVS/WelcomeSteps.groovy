package framework.src.Steps.TVS

import framework.src.Pages.TVS.*

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

And(~/^I am in the welcome page and I press the button to go to the login page$/) { ->
    WelcomePage welcomePage = at(WelcomePage)
    welcomePage.makeClickInTheButtonMakeLoggin()
}
