

package zenjob.testautomation.stepdefinitions

import cucumber.api.DataTable
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.DashBoardPage
import geb.Page
import zenjob.testautomation.pages.OpsDashboardPage
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
import zenjob.testautomation.pages.company.CompanyCreatePage
import zenjob.testautomation.pages.company.CompanyOverviewPage
import zenjob.testautomation.pages.company.CompanyDetailsPage




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





