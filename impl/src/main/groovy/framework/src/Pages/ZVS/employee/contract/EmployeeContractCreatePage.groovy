package framework.src.Pages.ZVS.employee.contract


import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule
/*
import zenjob.testautomation.modules.ErrorsModule
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.utils.TokenParser
*/

import java.util.regex.Pattern

class EmployeeContractCreatePage extends BasePage {
    static at = {
        //Pattern pattern = ~/(Add )?Employee Contract for ?((\w)+ )*/
        Pattern pattern = ~/(Add )?Employee Contract for ([^"]*)/
        header.text() ==~ pattern
    }

    static content = {
        header { $(By.xpath('//h1')) }
        form { $(By.xpath('//form')) }
        errors { module(ErrorsModule)}
        createEmployeeContractButton { $(By.xpath('//input[@name="createEmployeeContract"]')) }
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
        }
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }


    void submit() {
        createEmployeeContractButton.click()
    }
}
