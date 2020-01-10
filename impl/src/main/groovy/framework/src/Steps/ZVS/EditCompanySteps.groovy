

package framework.src.Steps.ZVS

import cucumber.api.DataTable
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
import framework.src.Pages.ZVS.company.CompanyDetailsPage
import framework.src.Pages.ZVS.company.CompanyOverviewPage

Given(~/^I am on the 'Company Overview' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    at(CompanyOverviewPage)
}

Given(~/^I click 'details' button$/) { ->
    // Write code here that turns the phrase above into concrete actions
    CompanyOverviewPage compovervpage = to(CompanyOverviewPage)
    compovervpage.clickdetailsbutton()

}


When(~/^I edit 'General Details' section of company$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert at(CompanyDetailsPage)
    CompanyDetailsPage compdetailpage = at(CompanyDetailsPage)
    sleep(4000)
    compdetailpage.editGeneralDetails()


}


Then(~/^'General Details' should be correct$/) { DataTable dataTable ->
    assert at(CompanyDetailsPage)
    CompanyDetailsPage compdetailpage = at(CompanyDetailsPage)
    Map<String, String> data = dataTable.asMap(String, String)
    compdetailpage.CheckGenUpdatedDetails(data)

}


Then(~/^I should be redirected to 'Company Details' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert at(CompanyDetailsPage)
    sleep(4000)
}





