package framework.src.Pages.ZVS.employee


import groovy.util.logging.Slf4j
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule

import java.util.regex.Pattern

@Slf4j
class EmployeeUpdatePage extends BasePage {
    static at = {
        Pattern pattern = ~/Edit Employee ((\w+) )*#([0-9]+)/
        header.text() ==~ pattern
    }

    static content = {
        form { $(By.xpath('//form')) }
        header { $(By.xpath('//*[@class="page-header"]//h1')) }
        submitButton { $(By.xpath('//input[@type="submit"]')) }
        errors { module(ErrorsModule)}
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
        }
    }

    void submit() {
        submitButton.click()
    }
}
