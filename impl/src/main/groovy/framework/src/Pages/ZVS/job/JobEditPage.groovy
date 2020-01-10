package framework.src.Pages.ZVS.job

import geb.module.RadioButtons
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Util.utils.TokenParser
import framework.src.Modules.ErrorsModule


import java.util.regex.Pattern

class JobEditPage extends BasePage {
    static at = {
        Pattern pattern = ~/Edit Job Edit and configure job offer/
        header.text() ==~ pattern
    }

    static content = {
        header { $(By.xpath('//h1')) }
        errors { module(ErrorsModule)}
        createJobButton { $(By.xpath('//input[@type="submit"]')) }
        form { $(By.xpath('//form')) }
        zendeskOrderKey {$(By.xpath('//input[@name="zendeskOrderKey"]'))}
        company  { $(By.xpath("//label[@for='company']/following-sibling::div//button[@type='button']")) }
        companyInputField {$(By.xpath("//label[@for='company']/following-sibling::div//input[@type='text']"))}
        companyRadios { $(By.xpath("//label[@for='company']/following-sibling::div//li//label[@class='radio']/input")).module(RadioButtons)}
        location  { $(By.xpath("//label[@for='jobLocation']/following-sibling::div//button[@type='button']")) }
        locationInputField {$(By.xpath("//label[@for='jobLocation']/following-sibling::div//input[@type='text']"))}
        definition  { $(By.xpath("//label[@for='jobDefinition']/following-sibling::div//button[@type='button']")) }
        definitionInputField {$(By.xpath("//label[@for='jobDefinition']/following-sibling::div//input[@type='text']"))}
        headCount {$(By.xpath('//input[@name="headCount"]'))}
        jobDueDate {$(By.xpath('//input[@name="jobDueDate"]'))}
        jobTitle {$(By.xpath('//input[@name="jobTitle"]'))}
        addShift {$(By.xpath("//button[@id='addShift']"))}
        jobShiftStartDate {$(By.xpath('//input[@name="jobShiftStartDate-0"]'))}
        jobShiftStartTime {$(By.xpath('//input[@name="jobShiftStartTime-0"]'))}
        jobShiftEndTime {$(By.xpath('//input[@name="jobShiftEndTime-0"]'))}
        description {$(By.xpath("//textarea[@name='description']"))}
        instructions {$(By.xpath("//textarea[@name='instructions']"))}
        internalComment {$(By.xpath("//div[@id='internalComment']//p"))}
        tags {$(By.xpath("//div[@class='row']//label[@for='tags']/following-sibling::div/input"))}
        excludeTags {$(By.xpath("//div[@class='row']//label[@for='tags']/following-sibling::div/input"))}
        tagGroup1 {$(By.xpath("//div[@class='row']//label[@for='tagGroup1']/following-sibling::div/input"))}
        tagGroup2 {$(By.xpath("//div[@class='row']//label[@for='tagGroup2']/following-sibling::div/input"))}
        tagGroup3 {$(By.xpath("//div[@class='row']//label[@for='tagGroup3']/following-sibling::div/input"))}
        tagGroup4 {$(By.xpath("//div[@class='row']//label[@for='tagGroup4']/following-sibling::div/input"))}
        tagGroup5 {$(By.xpath("//div[@class='row']//label[@for='tagGroup5']/following-sibling::div/input"))}
        autoMatching {$(By.id("autoMatch"))}
        cancelButton {$(By.xpath("//a[text()='Cancel']"))}

    }

    void submit() {
        createJobButton.click()
    }

    void addShiftButtonClick() {
        addShift.click()
    }

    void cancelButtonClick() {
        cancelButton.click()
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            switch (fieldName) {
                case "Zendesk Order Key":
                    zendeskOrderKey = parseFieldValue(fieldValue)
                    break
                case "Company":
                    selectCompany(fieldValue)
                    break
                case "Location":
                    selectLocation(fieldValue)
                    break
                case "Definition":
                    selectDefinition(fieldValue)
                    break
                case "Head Count":
                    headCount = parseFieldValue(fieldValue)
                    break
                case "Title":
                    jobTitle = parseFieldValue(fieldValue)
                    break
                case "Description":
                    description = parseFieldValue(fieldValue)
                    break
                case "Instructions":
                    instructions = parseFieldValue(fieldValue)
                    break
                case "Shift Start Date":
                    jobShiftStartDate = parseFieldValue(fieldValue)
                    break
                case "Shift Start Time":
                    jobShiftStartTime = parseFieldValue(fieldValue)
                    break
                case "Shift End Time":
                    jobShiftEndTime = parseFieldValue(fieldValue)
                    break
                case "Tags":
                    tags = parseFieldValue(fieldValue)
                    break
                case "Exclude Tags":
                    excludeTags = parseFieldValue(fieldValue)
                    break
                case "Tag Group 1":
                    tagGroup1 = parseFieldValue(fieldValue)
                    break
                case "Tag Group 2":
                    tagGroup2 = parseFieldValue(fieldValue)
                    break
                case "Tag Group 3":
                    tagGroup3 = parseFieldValue(fieldValue)
                    break
                case "Tag Group 4":
                    tagGroup4 = parseFieldValue(fieldValue)
                    break
                case "Tag Group 5":
                    tagGroup5 = parseFieldValue(fieldValue)
                    break
            // case "Internal comment":
            //   internalComment = fieldValue
            // break
                default: println("This field is not filled: "+ fieldName)

            }
        }
    }


    void selectCompany(String companyName) {
        company.click()
        companyInputField = companyName
        $(By.xpath("//label[@for='company']/following-sibling::div//li//label[@class='radio' and contains (text(),'${companyName}')]")).click()
    }

    void selectLocation(String jobLocation) {
        waitFor {$(By.xpath("//label[@for='jobLocation']/following-sibling::div//li//label[@class='radio' and contains (text(),'${jobLocation}')]")).click() }
        location.click()
        locationInputField = jobLocation
        $(By.xpath("//label[@for='jobLocation']/following-sibling::div//li//label[@class='radio' and contains (text(),'${jobLocation}')]")).click()
    }

    void selectDefinition(String jobDefinition) {
        waitFor {$(By.xpath("//label[@for='jobDefinition']/following-sibling::div//li//label[@class='radio' and contains (text(),'${jobDefinition}')]")).click() }
        definition.click()
        definitionInputField = jobDefinition
        $(By.xpath("//label[@for='jobDefinition']/following-sibling::div//li//label[@class='radio' and contains (text(),'${jobDefinition}')]")).click()
    }

    void fillMandatoryFieldsWithDefaultData() {

        zendeskOrderKey = parseFieldValue("12345")
        selectCompany("Zenjob")
        selectLocation("Zenjob HQ")
        selectDefinition("Atze an der Tür")
        jobTitle = "automation test: job with only mandatory data"
        addShiftButtonClick()
        jobShiftStartDate = parseFieldValue("@{TOMORROW}")
        jobShiftStartTime = parseFieldValue("09:00")
        jobShiftEndTime = parseFieldValue("15:00")
    }

    void checkAutomatchingCheckBox() {
        if ( $(By.xpath("//input[@id='autoMatch' and @checked = 'checked']")).displayed) {
            assert true
        }
        else { autoMatching.click()}
    }

    void uncheckAutomatchingCheckBox() {
        if ( !$(By.xpath("//input[@id='autoMatch' and @checked = 'checked']")).displayed) {
            assert true
        }
        else { autoMatching.click()}
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

}
