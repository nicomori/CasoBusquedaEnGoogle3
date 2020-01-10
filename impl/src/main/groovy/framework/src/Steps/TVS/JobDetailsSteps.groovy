package framework.src.Steps.TVS

import cucumber.api.PendingException
import framework.src.Pages.TVS.*

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

Then(~/^In the job details page, I make an scroll down and I accept the job$/) { ->
    DetailsJobPage detailsJobPage = at(DetailsJobPage)
    detailsJobPage.scrollDownAndAceptTheJob()
}