package framework.src.Steps.ZVS

import cucumber.api.DataTable
this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)
import framework.src.Pages.ZVS.company.CompanyDetailsPage

When(~/^I click on 'Add' button on the 'Location' section of company$/) { ->
    CompanyDetailsPage comdetailpage = at(CompanyDetailsPage)
    comdetailpage.AddLocationDetails()
}

Then(~/^I should be redirected to the location update page$/) { ->
    assert at(CompanyDetailsPage)
}

Then(~/^I click on 'company name'$/) { ->
    CompanyDetailsPage comdetailpage = at(CompanyDetailsPage)
    comdetailpage.ClickCompanyName()
}

Then(~/^I should see the 'location name', 'Edit' and 'Show' buttons in the 'Location' section of company$/) { DataTable dataTable ->
    CompanyDetailsPage comdetailpage = at(CompanyDetailsPage)
    Map<String, String> data = dataTable.asMap(String, String)
    comdetailpage.CheckLocationName(data)
    //test klsejlsgjk
}

Then(~/^I should see error messages in location alert$/) { DataTable dataTable ->
    CompanyDetailsPage comdetailpage = at(CompanyDetailsPage)
    Map<String, String> data = dataTable.asMap(String, String)
    comdetailpage.CreateJObLocationAlert(data)
}
