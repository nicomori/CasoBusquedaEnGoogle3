package framework.src.Pages.ZVS.employee


import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule

import java.util.regex.Pattern

class EmployeeInternalCommentPage extends BasePage{

    static at = {
       Pattern pattern = ~/Employee Comment Update/
       header.text() ==~ pattern
   }

    static content = {
       header { $(By.xpath('//*[@class="page-header"]')) }
       updateCommentButton { $(By.xpath('//input[@type="submit"]')) }
       commentField {$(By.xpath('//textarea[@name="comment"]'))}
       errors { module(ErrorsModule)}
   }

    String parseFieldValue(String fieldValue) {
       TokenParser.parseToken(fieldValue)
   }

    void fillFields(Map<String, String> data) {
       data.each { String fieldName, String fieldValue ->
           switch (fieldName) {
               case "Internal zenjob comment":
                   if (fieldValue == "none") {
                       commentField = ''
                   }
                   else
                       commentField = parseFieldValue(fieldValue)
                   break
               default: println("There is no this field")

            }
       }
   }

    void submit() {
       updateCommentButton.click()
   }

}
