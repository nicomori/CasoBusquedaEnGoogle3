package framework.src.Orchestration

import geb.Browser
import framework.src.Pages.ZVS.employee.EmployeeCreationPage
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.EmployeeOverviewPage
import framework.src.Util.utils.DataGenerator
import framework.src.Util.utils.UrlUtil

class UIEmployee {
    // Orchestration entities work on one browser
    private Browser browser

    class Contract {
        int id
    }

    int id
    String email

    // create employee from employee overview page
    // create employee contract, store the id
    // the core of the management module is the starting point for the entity Orchestration

    private int getEmployeeIdFromUrl() {
        Integer.parseInt(UrlUtil.getUrlSegments('/', browser.driver.getCurrentUrl()).last())
    }

    void orchestrateCreation() {
        //browser.go("${BasePage.getUrlParameter()}/ops/employee/list")

        browser.to(EmployeeOverviewPage)
        //browser.go("https://staging-main.zenjob.org/ops/employee/list")

        EmployeeOverviewPage employeeOverviewPage = browser.at(EmployeeOverviewPage)
        employeeOverviewPage.clickCreateTestEmployeeButton()


        EmployeeCreationPage employeeCreationPage = browser.at(EmployeeCreationPage)
        email=DataGenerator.getRandomZenjobEmailAddress()
        Map<String, String> data = [
                'email': email,
                'birthday': '01/01/1999',
                'firstName': 'AutoTestFirstName',
                'lastName': 'AutoTestLastName',
        ]

        employeeCreationPage.fillFields(data)
        employeeCreationPage.submit()

        browser.at(EmployeeDetailsPage)

        id = getEmployeeIdFromUrl()
    }

    void attachToBrowser(Browser browser) {
        this.browser = browser
    }
}
