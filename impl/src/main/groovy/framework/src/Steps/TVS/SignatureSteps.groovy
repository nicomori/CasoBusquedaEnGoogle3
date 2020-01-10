package framework.src.Steps.TVS

import framework.src.Pages.TVS.*

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Then(~/^In the signature page I make a signature and I press the button accept the job$/) { ->
    SignaturePanelPage signaturePanelPage = at(SignaturePanelPage)
    signaturePanelPage.signTheContract()
}