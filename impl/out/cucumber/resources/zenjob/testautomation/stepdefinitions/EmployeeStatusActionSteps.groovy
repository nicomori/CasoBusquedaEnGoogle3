package zenjob.testautomation.stepdefinitions

import cucumber.api.DataTable
import cucumber.api.PendingException
import zenjob.testautomation.ApplicationState
import zenjob.testautomation.orchestration.UIEmployee
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.pages.employee.EmployeeDetailsPage
import geb.Page
import zenjob.testautomation.pages.employee.EmployeeImmaPage
import zenjob.testautomation.pages.employee.EmployeeWorkingPermitPage
import zenjob.testautomation.pages.employee.contract.EmployeeContractCreatePage
import zenjob.testautomation.pages.employee.contract.EmployeeContractDetailsPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)

ApplicationState state = binding.getVariable('state') as ApplicationState
UIEmployee employee = state.employee

 Given(~/^I have an existing immatrikulation and no active contract$/) { ->
    if (!page.hasImma()){
        page.addImmaButtonClick()
        at(EmployeeImmaPage)
        page.setDate("01/09/2018","31/10/2020")
        at(EmployeeDetailsPage)
    }
    if (page.activeContractExists()){
        //not sure should be done
        page.removeContract()
     }
}


 When(~/^I click on 'Onboarded before' button$/) { ->
     at(EmployeeDetailsPage)
    page.clickOnboardedBefore()
}

 When(~/^I click on 'Mark Employee as Onboarded' in the pop up$/) { ->
    page.clickMarkEmployeeAsOnboarded()
    at(EmployeeDetailsPage)
}

 Then(~/^I should be directed back to the 'Employee Overview' page$/) { ->
    assert at(EmployeeDetailsPage)
}

 Given(~/^I have an existing contract and no immatrikulation$/) { ->
    if (page.activeContractExists())
        println("Got a Contract")
    else{
        println("no contract=>get one")
        page.clickAddNewEmployeeContractButton()
        EmployeeContractCreatePage employeeContractCreatePage = at(EmployeeContractCreatePage)

         // fill in date
        employeeContractCreatePage.fillFields(['startDate': "01/11/2018"])
        employeeContractCreatePage.submit()
        assert employeeContractCreatePage.noErrors()
        EmployeeContractDetailsPage employeeContractDetailsPage = at(EmployeeContractDetailsPage)
        assert employeeContractDetailsPage.stateIs('PROPOSED')

         // confirm contract
        employeeContractDetailsPage.clickConfirmButton()
        employeeContractDetailsPage.stateIs('CONFIRMED')

         // sign contract
        employeeContractDetailsPage.clickSignByTalentButton()
        assert employeeContractDetailsPage.stateIs('SIGNED')

         // go back to details page
        page.browser.go("${BasePage.getUrlParameter()}/ops/employee/show/${employee.id}")
    }

 }

 Given(~/^I have an existing contact and immatrikulation$/) { ->
    if (page.activeContractExists())
        println("Got a Contract")
    else{
        println("no contract=>get one")
        page.clickAddNewEmployeeContractButton()
        EmployeeContractCreatePage employeeContractCreatePage = at(EmployeeContractCreatePage)

         // fill in date
        employeeContractCreatePage.fillFields(['startDate': "01/11/2018"])
        employeeContractCreatePage.submit()
        assert employeeContractCreatePage.noErrors()
        EmployeeContractDetailsPage employeeContractDetailsPage = at(EmployeeContractDetailsPage)
        assert employeeContractDetailsPage.stateIs('PROPOSED')

         // confirm contract
        employeeContractDetailsPage.clickConfirmButton()
        employeeContractDetailsPage.stateIs('CONFIRMED')

         // sign contract
        employeeContractDetailsPage.clickSignByTalentButton()
        assert employeeContractDetailsPage.stateIs('SIGNED')

         // go back to details page
        page.browser.go("${BasePage.getUrlParameter()}/ops/employee/show/${employee.id}")
        at (EmployeeDetailsPage)
    }
    if (!page.hasImma()){
        page.addImmaButtonClick()
        at(EmployeeImmaPage)
        page.setDate("01/09/2018","31/10/2020")
        at(EmployeeDetailsPage)
    }
}

 Then(~/^I should be directed back to Employee Overview and changes should be visable$/) { ->
     at(EmployeeDetailsPage)
}

When(~/^I click on <Button>$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //List<List<String>> data = arg1.raw()

    //print(data[0][0])

}

When(~/^I select 'OK' from popup$/) { ->
    // Write code here that turns the phrase above into concrete actions

}

When(~/^<Action> should happen$/) { ->
    // Write code here that turns the phrase above into concrete actions
    //List<List<String>> data = arg2.raw()
    //print(data[0][0])
}

Then(~/^<Status> should be changed and button text be renemed to <New Button Text>$/) { DataTable arg3 ->
    // Write code here that turns the phrase above into concrete actions
    List<List<String>> data_t = arg3.raw()
    assert page.processActions(data_t)

    /*page.fillFields(data)
   page.clickActionButton(data[0][0])
   print(data[1][0])
   assert page.hasInfoLike("Unmarked Employee")//#2380 (JeanDidier Pirette) as activated!")//print(data[1][0]))
*/
}

And(~/^I have an existing contact, immatrikulation and working permit$/) { ->
    if (page.activeContractExists())
        println("Got a Contract")
    else{
        println("no contract=>get one")
        page.clickAddNewEmployeeContractButton()
        EmployeeContractCreatePage employeeContractCreatePage = at(EmployeeContractCreatePage)

        // fill in date
        employeeContractCreatePage.fillFields(['startDate': "01/11/2018"])
        employeeContractCreatePage.submit()
        assert employeeContractCreatePage.noErrors()
        EmployeeContractDetailsPage employeeContractDetailsPage = at(EmployeeContractDetailsPage)
        assert employeeContractDetailsPage.stateIs('PROPOSED')

        // confirm contract
        employeeContractDetailsPage.clickConfirmButton()
        employeeContractDetailsPage.stateIs('CONFIRMED')

        // sign contract
        employeeContractDetailsPage.clickSignByTalentButton()
        assert employeeContractDetailsPage.stateIs('SIGNED')

        // go back to details page
        page.browser.go("${BasePage.getUrlParameter()}/ops/employee/show/${employee.id}")
        at (EmployeeDetailsPage)
    }

    if (!page.hasWorkingPermit()){
        page.addWorkingPermitButtonClick()
        at(EmployeeWorkingPermitPage)
        page.setDate("01/09/2018","31/10/2020")
        at(EmployeeDetailsPage)
    }
    if (!page.hasImma()){
        page.addImmaButtonClick()
        at(EmployeeImmaPage)
        page.setDate("01/09/2018","31/10/2020")
        at(EmployeeDetailsPage)
    }
}
And(~/^The user should be onboarded$/) { ->
    at(EmployeeDetailsPage)
    page.checkOnboarded()
}