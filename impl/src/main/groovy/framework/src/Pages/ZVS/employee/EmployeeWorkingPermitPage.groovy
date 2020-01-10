package framework.src.Pages.ZVS.employee

import org.openqa.selenium.By
import framework.src.Pages.BasePage

class EmployeeWorkingPermitPage extends BasePage{
    static at = {
        header.text() == "Add Working Permit"
    }

    static url = url + '/ops/employee/addEmployeeImmatriculation/'

    static content = {
        header { $(By.xpath('//h1')) }
        startDate {$(By.xpath("//input[@id='startDate']"))[0]}
        endDate{ $(id:"endDate")}
        saveButton {$(By.xpath("//input[@type='submit']"))}
    }

    void setDate(String start, String end){
        startDate.value(start)
        endDate.value(end)
        withConfirm(true) {saveButton.click()}
    }
}

