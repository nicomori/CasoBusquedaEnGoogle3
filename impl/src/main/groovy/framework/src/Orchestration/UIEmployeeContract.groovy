package framework.src.Orchestration

import geb.Browser
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.contract.EmployeeContractCreatePage
import framework.src.Pages.ZVS.employee.contract.EmployeeContractDetailsPage


class UIEmployeeContract {
    Browser browser
    UIEmployee employee

    UIEmployeeContract(Browser browser, UIEmployee employee) {
        this.browser = browser
        this.employee = employee
    }

    void create(String startDate) {
        EmployeeDetailsPage employeeDetailsPage = browser.at(EmployeeDetailsPage)
        employeeDetailsPage.clickAddNewEmployeeContractButton()
        EmployeeContractCreatePage employeeContractCreatePage = browser.at(EmployeeContractCreatePage)

        // fill in date
        employeeContractCreatePage.fillFields([
                'startDate': startDate // would be from the TDMS, after having parsed the tokens
        ])


        employeeContractCreatePage.submit()
        assert employeeContractCreatePage.noErrors()
        EmployeeContractDetailsPage employeeContractDetailsPage = browser.at(EmployeeContractDetailsPage)
        assert employeeContractDetailsPage.stateIs('PROPOSED')

        // confirm contract
        employeeContractDetailsPage.clickConfirmButton()
        employeeContractDetailsPage.stateIs('CONFIRMED')

        // sign contract
        employeeContractDetailsPage.clickSignByTalentButton()
        assert employeeContractDetailsPage.stateIs('SIGNED')

        // go back to details page
        browser.go("https://staging-main.zenjob.org/ops/employee/show/${employee.id}")
    }
}
