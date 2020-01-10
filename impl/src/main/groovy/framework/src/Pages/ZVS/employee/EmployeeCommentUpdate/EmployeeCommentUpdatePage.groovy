

package framework.src.Pages.ZVS.employee.EmployeeCommentUpdate

import geb.navigator.Navigator
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser

import java.util.regex.Pattern

class EmployeeCommentUpdatePage extends BasePage {

    static at = {
        Pattern pattern = ~/Employee Comment Update/
        header.text() ==~ pattern
    }


    def i

    static content = {
        header { $(By.xpath('//h1')) }
        form { $(By.xpath('//form')) }
        jobCommentField {$(By.xpath("//*[@id='comment']"))}
        updatebutton {$(By.xpath(".//*[@value='Update Comment']"))}


        panel { String panelTitle ->
            $(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), '${panelTitle}')]"))
        }

        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath(".//a[normalize-space()='${text}']"))
        }

        // for Primary / other jobs panel
        UpdateCommentButton { Navigator panel ->
            linkWithText(panel, 'Update Comment')
        }

        // for employee comment update page
        UpdateCommentButton_ECUpage { Navigator nav ->
            nav.find(By.xpath(".//*[@value='Update Comment']"))
        }

    }

    void clickUpdateCommentButton() {
        UpdateCommentButton(panel('Primary / other jobs (Please note we do not save history)')).click()
    }

    void clickUpdateCommentButton_ECUpage() {
        //UpdateCommentButton_ECUpage().click()
        updatebutton.click()
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
            //jobCommentField = parseFieldValue(fieldValue)
        }
    }


}



