package framework.src.Pages.ZVS.job

import geb.Module
import geb.navigator.Navigator
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule
import framework.src.Сonfig.Configuration

import java.util.regex.Pattern

class JobDetailsPage extends BasePage {
    static at = {
        Pattern pattern = ~/[^#]* #([0-9]){1,}\(.*\)/
         header.text() ==~ pattern
    }

    public static content = {
        header { $(By.xpath('//h1')) }
        errors { module(ErrorsModule)}

        operator { $(By.xpath("//div[@class='col-sm-1' and contains (text(),'Operator')]")) }

        panel { String panelTitle ->
            $(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), '${panelTitle}')]"))
        }

        company {
            panel("Company Contact Details").find(By.xpath("//div[div[text()='Company:']]/div[@class='col-lg-10']"))
        }

        shiftPanel { panel("Shift Overview")}
        shiftTable { shiftPanel.$("table") }
        shiftTableRows { shiftTable.$('tbody > tr[data-toggle="collapse"]').moduleList(ShiftRow) }

        automatchingTable { $(By.xpath("//table[@class='table table-hover table-small' and .//th[contains(text(),'Scheduled Matching')]]")) }
        appliedTable { $(By.xpath("//div[@class='col-sm-12' and ./p/strong[contains(text(),'applied')]]//table[@class='table table-hover table-small zj-table']")) }
        appliedTableRows{appliedTable.$('tbody > tr').moduleList(AppliedRow) }

        bookedTable { $(By.xpath("//div[@class='col-sm-12' and ./p/strong[contains(text(),'booked')]]//table[@class='table table-hover table-small zj-table']")) }
        bookedTableRows{bookedTable.$('tbody > tr[data-toggle="collapse"]').moduleList(BookedRow)  }


        zendeskOrderKey {
            panel("Job Details").find(By.xpath("//div[@class='row']//div[text()='Zendesk Order Key']/following-sibling::div/strong/a"))
        }
        region {
            panel("Job Details").find(By.xpath("//div[@class='row']//div[text()='Region']/following-sibling::div/strong"))
        }
        location {
            panel("Job Details").find(By.xpath("//div[@class='row']//div[text()='Location']/following-sibling::div/strong"))
        }
        jobDefinition {
            panel("Job Details").find(By.xpath("//div[@class='row']//div[text()='Job Definition']/following-sibling::div"))
        }

        jobStatus {
            panel("Job Details").find(By.xpath(".//div[contains(text(),'Head Count')]/following-sibling::div/span"))
        }
        tags {
            panel("Job Details").find(By.xpath("//div[@class='row']//div[text()='Tags:']/following-sibling::div"))
        }

        jobDueDate {panel("Job Details").find(By.xpath("//div[contains(text(),'Job Due Date')]/following-sibling::div")) }
        jobDueTime {panel("Job Details").find(By.xpath("//div[contains(text(),'Job Due Time')]/following-sibling::div")) }
        description {panel("Job Details").find(By.xpath("//div[contains(text(),'Description')]/following-sibling::div")) }
        instructions {panel("Job Details").find(By.xpath("//div[contains(text(),'Instructions')]/following-sibling::div")) }
        workingHours {panel("Job Details").find(By.xpath(".//div[contains(text(),'Working hours')]/following-sibling::div")) }
        duplicateJobButton {panel("Job Details").find(By.xpath("//a[contains(text(),'Duplicate Job')]"))}
        editJobButton(required: false) {panel("Job Details").find(By.xpath("//a[contains(text(),'Edit Job')]"))}
        cancelJobButton(required: false) {panel("Job Details").find(By.xpath("//a[contains(text(),'Cancel Job')]"))}

        pricingConfiguration  {panel("Pricing Configuration")}

        companyInternalComment {panel("Company Internal Comment")}

        basislohnEmployeeEarning
                {panel("Pricing Configuration").find(By.xpath("//div[contains(text(),'Basislohn')]/following-sibling::div")) }

        basislohnSellingPrice
                {panel("Pricing Configuration").find(By.xpath("//div[@class='col-lg-9' and .//div[contains(text(),'Basislohn')]]/following-sibling::div/div[1]/div[@class='col-lg-12']")) }

        nachtzuschlagEmployeeEarning {panel("Pricing Configuration").find(By.xpath("//div[contains(text(),'Nachtzuschlag')]/following-sibling::div")) }

        nachtzuschlagSellingPrice
                {panel("Pricing Configuration").find(By.xpath("//div[@class='col-lg-9' and .//div[contains(text(),'Basislohn')]]/following-sibling::div/div[2]/div[@class='col-lg-12']")) }

        sonntagszuschlagEmployeeEarning{panel("Pricing Configuration").find(By.xpath("//div[contains(text(),'Sonntagszuschlag')]/following-sibling::div")) }
        sonntagszuschlagSellingPrice  {panel("Pricing Configuration").find(By.xpath("//div[@class='col-lg-9' and .//div[contains(text(),'Basislohn')]]/following-sibling::div/div[3]/div[@class='col-lg-12']")) }
        feiertagszuschlagEmployeeEarning {panel("Pricing Configuration").find(By.xpath("//div[contains(text(),'Feiertagszuschlag')]/following-sibling::div")) }
        feiertagszuschlagSellingPrice {panel("Pricing Configuration").find(By.xpath("//div[@class='col-lg-9' and .//div[contains(text(),'Basislohn')]]/following-sibling::div/div[4]/div[@class='col-lg-12']")) }
        internalcomment { $(By.xpath("//div[@class='current_internal_comment']/p[1]")) }
        startMatchingButton {$(By.xpath("//a[contains(text(),'Start Matching')]"))}
        jobOverview {$(By.xpath("//nav[@class='navbar navbar-default navbar-secondary']//a[@href='/ops/job/list']"))}
        stopAutomatchingButton {$(By.xpath("//a[contains(text(),'Stop Auto matching')]"))}

        appliedTab {$(By.xpath("//ul[@id='matchingTabs']//a[contains(text(),'Applied')]"))}

        matchingTab { String tab ->
            $(By.xpath("//ul[@id='matchingTabs']//a[contains(text(),'${tab}')]"))
        }

        buttonBook {$(By.xpath("//a[contains(@href,'/ops/job/book/')]"))}
    }

    void checkCompanyContactDetails(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            if (fieldName == "Company") {
                assert company.text() == fieldValue
            } else {
                // implemented only for company
                false
            }
        }
    }

    void checkShiftOverview(Integer shiftNumber, Map<String, String> data) {
        shiftPanel.click()
        data.each { String fieldName, String fieldValue ->

            if (fieldName in ["Checkin", "Checkout"]) {
                def dateFromFeature  = (fieldValue =~ /@\{(.*)\}/)[0][0]

                Date date = Date.parse( 'yyyy-MM-dd', parseFieldValue(dateFromFeature) )
                String parseDate = date.format( 'dd.MM.YYYY' )

                def fieldValue2 = fieldValue.replace(dateFromFeature, parseDate)

                if (fieldName == "Checkin") {
                    assert shiftTableRows[shiftNumber].checkin.contains(fieldValue2)
                } else if (fieldName == "Checkout") {
                    assert shiftTableRows[shiftNumber].checkout.contains(fieldValue2)
                }
            }

            else if (fieldName == "Head Count") {
                assert shiftTableRows[shiftNumber].headCount.contains(fieldValue)
            }
        }
    }

    void checkJobDetails(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            if (fieldName == "Zendesk Order Key") {
                assert zendeskOrderKey.text() == fieldValue
            } else if (fieldName == "Region") {
                assert region.text() == fieldValue
            } else if (fieldName == "Location") {
                assert location.text() == fieldValue
            } else if (fieldName == "Job Definition") {
                assert jobDefinition.text() == fieldValue
            } else if (fieldName == "Job Due Date") {
                assert jobDueDate.text() == parseFieldValue(fieldValue)
            } else if (fieldName == "Job Due Time") {
                assert jobDueTime.text() == fieldValue
            } else if (fieldName == "Description") {
                assert description.text() == fieldValue
            } else if (fieldName == "Instructions") {
                assert instructions.text() == fieldValue
            }
            else if (fieldName == "Working hours") {
                assert workingHours.text() == fieldValue
            }
            else {
               assert true
                println "assert method is not implemented for the field: " + fieldName
            }
        }
    }

     void checkDefaultEmployeePricingTable() {
        assert basislohnEmployeeEarning.text().contains("EUR 10.50")
        assert basislohnSellingPrice.text().contains("EUR 15.00")
        assert nachtzuschlagEmployeeEarning.text().contains("EUR 2.63")
        assert nachtzuschlagSellingPrice.text().contains("EUR 3.75")
        assert sonntagszuschlagEmployeeEarning.text().contains("EUR 5.25")
        assert sonntagszuschlagSellingPrice.text().contains("EUR 7.50")
        assert feiertagszuschlagEmployeeEarning.text().contains("EUR 10.50")
        assert feiertagszuschlagSellingPrice.text().contains("EUR 15.00")
    }

    void checkOperator() {
       assert  operator.text().contains(Configuration.instance.getConfigProperty('opsDashboardUser'))

    }

    void checkInternalComment(Map<String, String> data){
        data.each { String fieldName, String fieldValue ->
            assert internalcomment.text() == fieldValue
        }
    }

    def elementExists(Navigator navigator)
    {
        if (navigator.isEmpty())  {
            false
        }
        else {
            true
        }
    }

    void jobOverviewClick(){
        jobOverview.click()
    }

    def getJobId(){
        def jobId =  (header.text() =~ /(?<=#).*[0-9]/)
    }

    int checkShiftsCount() {
        shiftTableRows.size()
    }

    void stopAutomatchingClick() {
        stopAutomatchingButton.click()

    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void tabClick(String tab) {
        sleep(2000)

        try{
            matchingTab("${tab}").click()
        }catch(Exception e){
            matchingTab("${tab}").click()
        }


        //TODO: replace with proper wait
        sleep(2000)
    }

    void tabClickInTheTabApplied() {
        println('hhhhhhhhh hhhhhh hhhhh')


        sleep(4000)

        try{
            appliedTab.click()
        }catch(Exception e){
            appliedTab.click()
        }

        buttonBook.click()

        println('hhhhhhhhh hhhhhh hhhhh 222')
        sleep(4000)
    }



    void bookTalent(String name) {
        for (int i=0; i < appliedTableRows.size(); i++) {
            if (appliedTableRows[i].talentName.contains(name)) {
                appliedTableRows[i].bookButton.click()
                break
            }
        }
    }

    void bookTalentFor_E2ECases(String name) {
        for (int i=0; i < appliedTableRows.size(); i++) {
            if (appliedTableRows[i].talentName.contains(firstNameOfTheTalent)) {
                appliedTableRows[i].bookButton.click()
                break
            }
        }
    }

    void checkTalentInAppliedTab_ToImproveTheQualityOfTheCode(String name) {
        println "talent that we are looking for: " + firstNameOfTheTalent
        boolean talentFound = false

        sleep(4444)
        for (int i=0; i < appliedTableRows.size(); i++) {
            sleep(1111)
            println('ooookokokokok')
            println('kkkoooooo == '+appliedTableRows[i].text())
            if (appliedTableRows[i].text().contains(firstNameOfTheTalent)) {
                println('ooooooouuuuuuuuuuuu 111111')

                talentFound = true
                break
            }
        }
        assert talentFound
    }



    void checkTalentInAppliedTab(String name) {
        boolean talentFound = false
        for (int i=0; i < appliedTableRows.size(); i++) {
            if (appliedTableRows[i].talentName.contains(name)) {
                talentFound = true
                break
            }
        }
        assert talentFound
    }

    void checkTalentInBookedTab(String name) {
        boolean talentFound = false
        for (int i=0; i < bookedTableRows.size(); i++) {
            if (bookedTableRows[i].talentName.contains(name)) {
                talentFound = true
                break
            }
        }
        assert talentFound
    }


}

class ShiftRow extends Module {
    static content = {
        cell { $("td") }
        rowNumber { cell[0].text() }
        checkin { cell[1].text() }
        checkout { cell[2].text() }
        effectiveWork { cell[3].text() }
        pause { cell[4].text() }
        hourlyEarning { cell[5].text() }
        totalEarning { cell[6].text() }
        hourlySellingPrice { cell[7].text() }
        totalSellingPrice { cell[8].text() }
        headCount { cell[9].text() }
    }
}

class MatchingRow extends Module {
    static content = {
        cell { $("td") }
        scheduledMatchingRun { cell[0].text() }
        state { cell[1].text() }
        scheduledRunTime { cell[2].text() }
        executedRunTime { cell[3].text() }
        statusComment { cell[4].text() }
        currentConfigSelector { cell[5].text() }
        repeatEvery { cell[6].text() }
        fixedCount { cell[8].text() }
        automatchingProcessdD { cell[9].text() }
        active { cell[10].text() }
        doneAmount { cell[11].text() }
        totalAmount { cell[12].text() }
        failedAmount { cell[13].text() }
        successfulAmount { cell[14].text() }
    }
}

class AppliedRow extends Module {
    static content = {
        cell { $("td") }
        talentName { cell[0].$("a").text() }
        tags { cell[1].text() }
        lastMatched { cell[2].text() }
        matched { cell[3].text() }
        lastBooked { cell[4].text() }
        hourlyEarning { cell[5].text() }
        totalEarning { cell[6].text() }
        hourlySellingPrice { cell[8].text() }
        totalSellingPrice { cell[9].text() }
        bookButton { cell[10].$(By.xpath("//a[contains(@href,'job/book/')]")) }
        declineButton { cell[10].$(By.xpath("//a[contains(@href,'job/decline/')]")) }

    }
}

class BookedRow extends Module {
    static content = {
        cell { $("td") }
        talentName { cell[0].$("a").text() }
        tags { cell[1].text() }
        lastMatched { cell[2].text() }
        matched { cell[3].text() }
        lastBooked { cell[4].text() }
        hourlyEarning { cell[5].text() }
        totalEarning { cell[6].text() }
        hourlySellingPrice { cell[8].text() }
        totalSellingPrice { cell[9].text() }
        contract { cell[10].text() }
        shiftPanelButton { cell[11].$(By.xpath("//a")) }
        cancelEmployeeShiftButton { cell[11].$(By.xpath("//a")) }

    }
}