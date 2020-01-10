package framework.src.Pages.ZVS.employee


import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Modules.ErrorsModule
import framework.src.Mapping.jsonMapping
import java.util.regex.Pattern



class EmployeeImportPage extends BasePage{

    static at = {
        Pattern pattern = ~/((\w)+ )*#([0-9])+ Show in Shift Panel/
        header.text() ==~ pattern
    }


    static content = {
        header { $(By.xpath('//h1')) }
        jsonField {$(By.xpath('//textarea[@name="jsonTextField"]'))}
        importFromJsonButton  { $(By.xpath('//input[@type="submit"]'))}
        errors { module(ErrorsModule)}
    }

    static filename = '/impl/src/main/resources/employee.json'



    String changeEmailAddressInJson(String email){
        def content = jsonMapping.getJsonFile().text.replaceAll('blabla0001@zenjob.com',email)

        return content
    }

    String changeEmailAddressInIncorrectJson(String email){
        def content = jsonMapping.getJsonFile().text.replaceAll('blabla0001@zenjob.com',email)

        return content
    }

    void fillFieldsWithNewEmail(String email) {
        jsonField = changeEmailAddressInJson(email)

    }

    void fillFieldsMailIsNotMatched() {
        jsonField = jsonMapping.getJsonFile().text

    }

    void submit() {
        importFromJsonButton.click()
    }
}
