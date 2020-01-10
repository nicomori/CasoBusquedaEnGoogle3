package framework.src.Steps.ZVS

import cucumber.api.PendingException
import framework.src.Pages.ZVS.CreateJobPage
import framework.src.Pages.ZVS.JobOverviewConfirmation
import framework.src.Pages.ZVS.MatchingManagerPage
import framework.src.Pages.ZVS.OpsOrdersDetailsPage
import framework.src.Pages.ZVS.OrderManagementPage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)


And(~/^I access to the order where I make click in the button create job to access to start to create the job$/) { ->
    println('searching the order created and generating the job')
    OrderManagementPage oderManagementPage = at(OrderManagementPage)

//    oderManagementPage.makeClickInTheDetailButtonOfTheOrder(3448)
    oderManagementPage.makeClickInTheDetailButtonOfTheOrder(thisIsTheVariableJuan)
    OpsOrdersDetailsPage opsOrdersDetailsPage = at(OpsOrdersDetailsPage)
    opsOrdersDetailsPage.makeClickInTheButtonCreateJob()
}

And(~/^In the detail page of the detail job created, I can write the job description Title$/) { ->
    println('creating the job')
    CreateJobPage createJobPage = at(CreateJobPage)
    createJobPage.fillTheFormAndCreateAJobWithoutAutoMarching()

}

Then(~/^In the page of the Job Overview, I make click in the botton Start Marching, and click in the popup to accept$/) {
    ->
    println('Starting the Matching')
    JobOverviewConfirmation jobOverviewConfirmation = at(JobOverviewConfirmation)


    String jobNumberCreated = jobOverviewConfirmation.makeClicInTheButtonStartingToMatch()

    jobNumberCreated = jobNumberCreated.substring(jobNumberCreated.size()-4, jobNumberCreated.size())

    thisIsTheVariableJuan2 = jobNumberCreated

}
Then(~/^in the page to match a talent I include the name of the talent and the last name to find the talent$/) { ->
    println('Starting fill the data of the talent and start to search')
    MatchingManagerPage matchingManagerPage = at(MatchingManagerPage)
    matchingManagerPage.fillFiltersOfAUserAndPressButtonApplyFilter()
}
Then(~/^I select the talent and I press in the button Match Selected$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}
And(~/^I wait refreshing the browser until the tab Matched appear with the number more$/) { ->
    // Write code here that turns the phrase above into concrete actions
    throw new PendingException()
}

And(~/^In the detail of the job created, I am writing the specials days for auto effective and the title$/) { ->
    println('Starting to set the days for after effective and the title')
    CreateJobPage createJobPage = at(CreateJobPage)
    createJobPage.fillTheFormAndCreateAJobWithoutAutoMarching()

}

And(~/^In the detail of the job created, I am starting to set the title and the specials date and times for auto effective$/) { ->
    println('Starting to set the days for auto effective and the title')
    CreateJobPage createJobPage = at(CreateJobPage)
    createJobPage.setAfterEffectiveSpecialsDays()
}



