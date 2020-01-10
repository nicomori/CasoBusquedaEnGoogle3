package framework.src.Pages.ZVS.employee

import cucumber.api.DataTable
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule
import framework.src.Pages.ZVS.employee.EmployeeDetailsPage

/*
import zenjob.testautomation.modules.ErrorsModule
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.utils.TokenParser
*/

class EmployeeCreationPage extends BasePage {
    static at = {
        //Pattern pattern = ~/Create Employee/
        //header.text() ==~ pattern
        true
    }

    static content = {
        form { $(By.xpath('//form')) }
        header { $(By.xpath('//*[@class="page-header"]//h1')) }
        submitButton { $(By.xpath('//input[@type="submit"]')) }
        errors { module(ErrorsModule)}
        termChechbox { $(By.xpath('//*[@id="termsAccepted"]')) }
        privacyChechbox { $('input', type:'checkbox', name:"privacyStatementAccepted") }

    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
        }
    }

    void fillFields(DataTable data) {
        fillFields(data.asMap(String, String))
    }

    void submit() {
        submitButton.click()
        at(EmployeeDetailsPage)
    }

}
