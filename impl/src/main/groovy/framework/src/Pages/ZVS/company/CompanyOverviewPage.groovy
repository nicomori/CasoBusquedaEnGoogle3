
package framework.src.Pages.ZVS.company

import geb.Module
import org.openqa.selenium.By
import framework.src.Pages.BasePage

class CompanyOverviewPage extends BasePage{

    static url = 'https://staging-main.zenjob.org/ops/company/list'

    static content = {
        form { $(By.xpath('//form')) }
        companytable  { $("table") }
        tableRows { companytable.$('tbody > tr').moduleList(CompanyRow) }
        apply {$(By.xpath('//input[@id=\'search\']'))}

    }
/*
    void checkFiltration(def data) {
        data.each { def fieldName, def fieldValue ->
            Date date = new Date()
            String datePart = date.format("dd/MM/yyyy")
            fieldValue=fieldValue+datePart
                assert tableRows[0]."${fieldName}".toLowerCase().contains(fieldValue)
        }
    }*/

    void checkFiltration(def data) {
        data.each { def fieldName, def fieldValue ->
            Date date = new Date()
            String datePart = date[Calendar.YEAR].toString()+date[Calendar.MONTH].toString()+date[Calendar.DATE].toString()+date[Calendar.HOUR].toString()
            //String datePart = date.format("dd/MM/yyyy")
            fieldValue=fieldValue+datePart
            assert tableRows[0]."${fieldName}".toLowerCase().contains(fieldValue)
        }
    }


    void clickdetailsbutton(){
        tableRows[0].details.click()

    }

}

class CompanyRow extends Module {
    static content = {
        cell { $("td") }
        id { cell[0].text() }
        company { cell[1].text() }
        description { cell[2].text() }
        tags { cell[3].text() }
        contact { cell[4].text() }
        edit { cell[5].$("a")[0] }
        details {cell[5].$("a")[1]}
    }
}

