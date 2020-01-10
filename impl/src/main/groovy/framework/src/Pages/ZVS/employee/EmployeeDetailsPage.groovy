package framework.src.Pages.ZVS.employee

import geb.error.RequiredPageContentNotPresent
import geb.navigator.Navigator
import org.openqa.selenium.By
import framework.src.Pages.BasePage

import framework.src.Modules.ErrorsModule
import framework.src.Mapping.jsonMapping
import framework.src.Util.utils.TokenParser
/*
import zenjob.testautomation.mapping.jsonMapping
import zenjob.testautomation.modules.ErrorsModule
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.utils.TokenParser
*/
import java.util.regex.Pattern

class EmployeeDetailsPage extends BasePage {


   // static url =  BasePage.getUrlParameter()+'/ops/employee/show'

    static at = {
        println(" details page: url is  "+url)
        Pattern pattern = ~/((\w)+ )*#([0-9])+ Show in Shift Panel/
        header.text() ==~ pattern

    }


    def i

    static content = {
        header { $(By.xpath('//h1')) }

        panel { String panelTitle ->
            $(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), '${panelTitle}')]"))
        }
        createTestEmployeeButton { $(By.xpath("//a[@href='/ops/employee/createEmployee']")) }
        field { String fieldName, String panelName ->
            // second of the two divs in the row where there is fieldName
            panel(panelName).find(By.xpath(".//div[@class='row' and .//text()='${fieldName}']/div[2]"))
        }

        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath(".//a[normalize-space()='${text}']"))
        }

        // for most of the panels
        editButton { Navigator panel ->
            linkWithText(panel, 'Edit')
        }

        // for contracts panel
        addNewButton { Navigator panel ->
            linkWithText(panel, 'Add New')
        }

        contractsPanel {
            panel('Contracts')
        }

        updateCommentButton { Navigator panel ->
            linkWithText(panel, 'Update Comment')
        }

        internalZenjobComment {$(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), 'Internal zenjob comment')]//div[@class='row']/div[@class='col-lg-12']"))}


        contractsSection { String type ->
            // TODO exception handling
            int positionInPanel = 0

            if (type == 'Scheduled Contract') {
                positionInPanel = 1
            }

            else if (type == 'Active Contract') {
                positionInPanel = 2
            }

            else if (type == 'Inactive Contracts') {
                positionInPanel = 3
            }

            panel('Contracts').find(By.xpath(".//div[@class='panel panel-default panel-danger']//tbody//tr[${positionInPanel}]"))
        }

        professionField {$(By.xpath('//div/strong[@text()="profession"]'))}
        employmentCity {$(By.xpath('//select[@name="employmentCity"]'))}
        employmentCityOther {$(By.xpath('//input[@name="employmentCityOther"]'))}
        aboutMe {$(By.xpath('//textarea[@id="aboutMe"]'))}
        jobCategories {$(By.xpath('//select[@name="categories"]'))}
        tags {$(By.xpath('//div[@class="bootstrap-tagsinput"]/input'))}
        employeeAvatar { $(By.xpath('//img[@alt="Employee avatar"]')) }
        errors { module(ErrorsModule)}

        terminateButton { Navigator panel ->
            linkWithText(panel, 'Terminate')
        }

        addImmaButton {$(By.xpath("//div[strong[contains(text(),'Immatriculation')]]/following-sibling::div//a[contains(text(),'Add')]"))}
        addWorkingPermitButton {$(By.xpath("//div[strong[contains(text(),'Working Permit')]]/following-sibling::div//a[contains(text(),'Add')]"))}
        onboardedFlag { $(By.xpath("//div[strong='Onboarded']/following-sibling::div[@class='col-lg-2']")) }

        workingDocumentsPanel {
            panel('Working Documents')
        }

        onboardedBeforeButton{$(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[4]/div[1]/div[1]/div[1]/div[2]/div[4]/div[3]/div[1]"))}
        confirmOnboardedBeforeButton{$(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[6]/div[1]/div[1]/form[1]/div[2]/input[1]"))}

        importEmployeeDataButton {$(By.xpath("//a[normalize-space()='Import Employee Data']"))}
        internalZenjobComment {$(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), 'Internal zenjob comment')]//div[@class='row']/div[@class='col-lg-12']"))}
        primaryOtherJobs {$(By.xpath("//div[contains(@class, 'panel ') and contains(.//h3[@class='panel-title']//text(), 'Primary / other jobs')]//div[@class='row']/div[@class='col-lg-12']"))}
    }

    boolean activeContractExists() {
        contractsPanel.find(By.xpath(".//div[@class='panel panel-default panel-info' and contains(.//text(),'No active contract exists')]"))
    }

    boolean noInactiveContractsExist() {
        contractsPanel.find(By.xpath(".//div[@class='panel panel-default panel-danger' and contains(.,'No inactive contracts exist')]"))
    }

    void clickAddNewEmployeeContractButton() {
        addNewButton(panel('Contracts')).click()
    }

    void editGeneralDetails() {
        editButton(panel('General Details')).click()
    }
    void editPersonnelMasterData() {
        editButton(panel('Personnel Master Data')).click()
    }
    String getEmail(){
        String res = panel('General Detail').find(By.xpath('//*[@id="dashboard"]/div[3]/div[1]/div/div/div[2]/div[5]/div[2]')).text()
        if (res == null)
            res = panel('General Detail').find(By.xpath('//*[@id="dashboard"]/div[4]/div[1]/div/div/div[2]/div[5]/div[2]')).text()
        return res
    }
    void clickCreateTestEmployeeButton() {
        //waitfor(createTestEmployeeButton.present())
        createTestEmployeeButton[0].click()
        browser.page(EmployeeCreationPage)
    }

    void editAboutAndPreferences() {
        editButton(panel('About & Preferences')).click()
    }

    void editDocumentsAndMedia() {
        editButton(panel('Documents & Media')).click()
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }


    void editPrimaryComment() {
        //add or update comment in primary job comment section
        waitFor {updateCommentButton(panel('Primary'))}
        updateCommentButton(panel('Primary')).click()
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            form."${fieldName}" = parseFieldValue(fieldValue)
        }
    }


    void checkFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            def expectedOPSValue = fieldValue
            def opsField = fieldName

            if (opsField == "Job Categories") {
                String[] categories = parseFieldValue(fieldValue).split(',')
                for (String value : categories) {

                    def valueWithoutSpaces = value.trim()
                    println "Job Category exists: " + expectedOPSValue
                    assert $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']/span[contains(.,'${expectedOPSValue}')]")).size() == 1

                }
            }
            else if (opsField== "Tags") {
                String[] tags = parseFieldValue(expectedOPSValue).split(',')
                for (String value : tags) {

                    def valueWithoutSpaces = value.trim()
                    println "Tag exists:" + value
                    assert $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']/span[contains(.,'${valueWithoutSpaces}')]")).size() == 1
                }
            }
            else if (opsField in ["Social insurance number", "Tax ident number","IBAN","BIC"]) {
                assert  $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']")).text() == parseFieldValue(expectedOPSValue)
            }
            else if (opsField in ["Internal zenjob comment"]) {
                assert  internalZenjobComment.text().contains(parseFieldValue(expectedOPSValue))
            }
            else if (opsField in ["Primary / other jobs"]) {
                assert  primaryOtherJobs.text().trim()== parseFieldValue(expectedOPSValue).trim()
            }
            else {
                assert $(By.xpath("//div[@class='row' and div/strong/text()='${opsField}']/div[@class='col-lg-7']")).text() == parseFieldValue(expectedOPSValue)
            }
        }

    }

    void checkFieldsFromJson(String email) {
        def jsonFile = jsonMapping.readJsonFromFile()
        jsonFile.each { String fieldName, def fieldValue ->
            def expectedOPSValue = jsonMapping.getExpectedOPSValue(fieldName,fieldValue,email)
            def opsField = jsonMapping.getOPSField(fieldName,fieldValue)
            if (opsField != null) {

                println "Json field: '${fieldName}' " + "OPSField: '${opsField}' " + "value: '${expectedOPSValue}'"
                if (opsField == "Job Categories" && fieldValue == true) {

                    println "Job Category exists: " + expectedOPSValue
                    assert $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']/span[contains(.,'${expectedOPSValue}')]")).size() == 1
                }
                else if (opsField == "Job Categories" && fieldValue == false) {

                    println "Job Category doesn't exist:" + expectedOPSValue
                    assert $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']/span[contains(.,'${expectedOPSValue}')]")).size() == 0
                }
                else if (opsField== "Tags") {
                    String[] tags = parseFieldValue(expectedOPSValue).split(',')
                    for (String value : tags) {

                        def valueWithoutSpaces = value.trim()
                        println "Tag exists:" + value
                        assert $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']/span[contains(.,'${valueWithoutSpaces}')]")).size() == 1
                    }
                }
                else if (opsField in ["Social insurance number", "Tax ident number","IBAN","BIC"]) {
                    assert  $(By.xpath("//div[@class='row' and contains(.,'${opsField}')]/div[@class='col-lg-7']")).text() == parseFieldValue(expectedOPSValue)
                }
                else if (opsField in ["Internal zenjob comment"]) {
                    assert  internalZenjobComment.text().contains(parseFieldValue(expectedOPSValue))
                }
                else if (opsField in ["Primary / other jobs"]) {
                    assert  primaryOtherJobs.text().trim()== parseFieldValue(expectedOPSValue).trim()
                }
                else {
                    assert $(By.xpath("//div[@class='row' and div/strong/text()='${opsField}']/div[@class='col-lg-7']")).text() == parseFieldValue(expectedOPSValue)
                }
            }

        }


    }

    void importEmployeeData() {
        importEmployeeDataButton.click()
    }

    void checkAvatar() {
        assert employeeAvatar.size() == 1
    }

    void checkOnboarded() {
        assert onboardedFlag.text() == "YES"
    }

    boolean hasImma() {
        return ((workingDocumentsPanel.find(By.xpath("//div[@class='row' and contains(.,'No active immatriculation')]/div[@class='col-lg-7']"))).text() == null)
    }

    boolean hasWorkingPermit() {
        return ((workingDocumentsPanel.find(By.xpath("//div[@class='row' and contains(.,'No active working permit')]/div[@class='col-lg-7']"))).text() == null)
    }

    void addImmaButtonClick() {
        addImmaButton.click()
    }

    void addWorkingPermitButtonClick() {
        addWorkingPermitButton.click()
    }

    void clickOnboardedBefore() {
        onboardedBeforeButton.click()
        sleep(1000)
        confirmOnboardedBeforeButton.click()
    }

    void clickMarkEmployeeAsOnboarded() {
        //confirmOnboardedBeforeButton.click()
    }

    void removeContract() {
        withConfirm(true) {
            terminateButton(panel('Contracts')).click()
        }
    }

    boolean hasInfoLike(String err) {
        try {
            return errors.checkInfoLike([err])
        }
        catch (RequiredPageContentNotPresent e) {
            return true
        }
    }

    void clickActionButton(String name, String opposite){
        boolean done=false
        while(!done){
            try {
                withConfirm(true) {
                    linkWithText(panel('Status and Actions'),name).click()
                }
                done=true
            }
            catch (RequiredPageContentNotPresent e) {
                println("already "+name)
                clickActionButton(opposite)

            }
        }
    }


    void clickActionButton(String name){
        try {
            withConfirm(true) {
                linkWithText(panel('Status and Actions'),name).click()
            }
        }
        catch (RequiredPageContentNotPresent e) {
            println("already UN "+name)


        }
    }

    boolean checkStatus(int i, String value)
    {
        if (i >=3)
            i=i+1
        if (panel('Status and Actions').find(By.xpath("*//div[@class='col-lg-2']")).size()>i)
            return (value==(panel('Status and Actions').find(By.xpath("*//div[@class='col-lg-2']"))[i].text()))
        else
            return false
    }

    boolean processActions(List<List<String>> data){
        for(int i=0;i<data.size();i++){
            println("___________________________")
            println(data[i][0])
            clickActionButton(data[i][0],data[i][3])
            if(!checkStatus(i,data[i][2]))
                return false
        }
        //checkStatus(1)
        return true
    }

    void editInternalComment() {
        waitFor {updateCommentButton(panel('Internal zenjob comment'))}
        updateCommentButton(panel('Internal zenjob comment')).click()
    }
}
