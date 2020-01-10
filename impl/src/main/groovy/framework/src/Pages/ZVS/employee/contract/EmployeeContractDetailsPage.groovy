package framework.src.Pages.ZVS.employee.contract


import org.openqa.selenium.By
import framework.src.Pages.BasePage

//import zenjob.testautomation.pages.BasePage

import java.util.regex.Pattern

class EmployeeContractDetailsPage extends BasePage {
    static at = {
        Pattern pattern = ~/(Add )?Employee Contract for ?([^"]*)/
        header.text() ==~ pattern
    }

    static content = {
        header { $(By.xpath('//h1')) }
        detailsPanel { $(By.xpath('//div[@class="panel panel-default"]')) }

        field { String name ->
            detailsPanel.find(By.xpath(".//div[@class='row' and contains(.//strong, '${name}')]")).find(By.xpath('div[2]'))
        }

        confirmButton { $(By.xpath('//a[contains(.,"Confirm")]')) }
        signByTalentButton { $(By.xpath('//a[contains(.,"Signed by Talent")]')) }
    }

    void clickConfirmButton() {
        withConfirm(true) {
            confirmButton.click()
        }
    }

    void clickSignByTalentButton() {
        withConfirm(true) {
            this.signByTalentButton.click()
        }
    }

    boolean stateIs(String state) {
        this.field('Contract State').text() == state
    }
}
