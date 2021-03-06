package framework.src.Steps.ZVS

import cucumber.api.DataTable
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
import framework.src.Pages.ZVS.company.CompanyCreatePage
import framework.src.Pages.ZVS.company.CompanyOverviewPage



Given(~/^I am on the create new company page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    assert at {CompanyCreatePage}
}

Then(~/^I should not be able to create employee$/) { ->
    // Write code here that turns the phrase above into concrete actions

}

Then(~/^I should see error message for not filling mandatory fields$/) { DataTable dataTable ->
    // Write code here that turns the phrase above into concrete actions
    CompanyCreatePage page = to (CompanyCreatePage)
    Map<String, String> data = dataTable.asMap(String, String)
    page.checkmsg(data)

}


Then(~/^I should see error messages in alert$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //CompanyCreatePage page = to (CompanyCreatePage)
    sleep(5000)
    page.checkmsg()
}

Given(~/^I am on the 'Create New Company' page$/) { ->
    // Write code here that turns the phcrase above into concrete actions

}

Then(~/^I am redirected to 'Company Overview' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //CompanyOverviewPage compovervpage = to(CompanyOverviewPage)
    //assert at {CompanyOverviewPage}

}

Then(~/^I can see the created company on the 'Company Overview' page$/) { DataTable dataTable->
    // Write code here that turns the phrase above into concrete actions
    CompanyOverviewPage compovervpage = to(CompanyOverviewPage)
    Map<String, String> data = dataTable.asMap(String, String)
    compovervpage.checkFiltration(data)
}



