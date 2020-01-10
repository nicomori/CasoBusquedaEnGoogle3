package framework.src.Pages.ZVS.employee

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser

/*
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.utils.TokenParser
*/

class EmployeeOverviewPage extends BasePage {
    static at = {
        //Pattern pattern = ~/Employees ((\w+) )*([0-9]+)/
        //header.text() ==~ pattern
        true
    }

    static url = "https://staging-main.zenjob.org/ops/employee/list"



    static content = {

        createTestEmployeeButton { $(By.xpath("//a[@href='/ops/employee/createEmployee']")) }
        form { $(By.xpath('//form')) }
        clickemployeeoverview { $(By.xpath("//div[@id='dashboard']//a[@href='/ops/employee/list']"))}
        applyFilterButton { $(By.xpath('//input[@type="submit"]')) }
        employeeTable  { $("table") }
        tableRows { employeeTable.$('tbody > tr').moduleList(EmployeeRow) }
        EmployeeStateButton { $(By.xpath(".//button[@class='multiselect dropdown-toggle btn btn-default' ]"))}

        //Employee State navigator
        panel { String panelTitle ->
            $(By.xpath("//form[@class='form-inline']//fieldset"))
        }

        //for Status and Actions
        panel1 { String panelTitle ->
            $(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), '${panelTitle}')]"))
        }

        // To select desired checkbox
        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath("//input[@value='${text}']"))
        }

        EmpStatus { Navigator nav , int rowno ->
            nav.find(By.xpath("*//div[@class='col-lg-2']"))[rowno].text()
        }

        EmpStatusText { Navigator nav , int rowno ->
            nav.find(By.xpath("*//div[@class='col-lg-4 text-right']"))[rowno].text()
        }


        // Navigator for state filter to select the desired checkbox
        SelectEmployeeState() { String text ->
            linkWithText(panel, text)
        }

    }

    def activatedtext(int rowno)
    {
        String statusval=EmpStatus(panel1('Status and Actions'),rowno)
        String empaction=EmpStatusText(panel1('Status and Actions'),rowno)
        String emptext=empaction+","+statusval
        return emptext
    }

    void selectstate (def data) {
        data.each { def fieldName, def fieldValue ->
            EmployeeStateButton.click()
            waitFor {SelectEmployeeState}
            SelectEmployeeState(fieldValue).click()

        }

    }

    void clickCreateTestEmployeeButton() {
        createTestEmployeeButton[0].click()
        browser.page(EmployeeCreationPage)
    }

    void clickApplyFilterButton() {
        applyFilterButton.click()
    }



    void checkFiltration(def data) {
        data.each { def fieldName, def fieldValue ->

            for(int i=0;i<tableRows.size();i++)
            {
                assert tableRows[i]."${fieldName}".toLowerCase().contains(fieldValue)
            }
        }
    }

    void DetailsFiltrationCheck(def data) {
        data.each { def fieldName, def fieldValue ->
        int expectedtext=0
            for(int i=0;i<tableRows.size();i++)
            {
                tableRows[i].details.click()
                framework.src.Pages.ZVS.employee.EmployeeDetailsPage empdetailspage = new framework.src.Pages.ZVS.employee.EmployeeDetailsPage()
                waitFor {framework.src.Pages.ZVS.employee.EmployeeDetailsPage}
                assert at(framework.src.Pages.ZVS.employee.EmployeeDetailsPage)
                String verificationtext = fieldValue+",YES"
                assert activatedtext(expectedtext).toLowerCase().contains(verificationtext.toLowerCase())
                driver.navigate().back()
                waitFor {EmployeeOverviewPage}
                assert at(EmployeeOverviewPage)
            }

        }
    }

    void ClickEmployeeOverviewButton(){
        clickemployeeoverview.click()
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
        }
    }


    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }
}

class EmployeeRow extends Module {
    static content = {
        cell { $("td") }
        id { cell[0].text() }
        lastName { cell[1].text() }
        firstName { cell[2].text() }
        email { cell[3].text() }
        employeeCity { cell[4].text() }
        birthDay { cell[5].text() }
        gender { cell[6].text() }
        available { cell[8].text() }
        onboarded { cell[9].text() }
        tags { cell[10].text() }
        details { cell[11].$("a") }
    }
}


