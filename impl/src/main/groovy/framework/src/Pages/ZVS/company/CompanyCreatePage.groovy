package framework.src.Pages.ZVS.company


import geb.navigator.Navigator
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Modules.ErrorsModule
import framework.src.Util.utils.TokenParser

class CompanyCreatePage extends BasePage {

    static url = 'https://staging-main.zenjob.org/ops/company/create'
    //static at = { title == 'Create Company' }

    static content = {
        form { $(By.xpath('//form')) }
        companyname { $(By.id('name')) }
        CreatecompLink { $(By.xpath("//a[@href='/ops/company/create']")) }
       //submitButton { $(By.xpath('//input[@type="submit"]')) }
        submitButton { $(By.xpath("//input[@id='createCompany']")) }
        Companyalretmsg { $(By.xpath("//div[@class='alert alert-danger']")) }
        Errormsg_name {$(By.xpath("//li[contains(text(),'\"name\"may not be empty.')]"))}

        panel { String panelTitle ->
            $(By.xpath("//div[@class='alert alert-danger']"))
        }

        // To select desired checkbox
        linkWithText { Navigator nav, String text ->
            nav.find(By.xpath("//li[contains(text(),'${text})]"))
        }

        // Navigator for state filter to select the desired checkbox
        checkerrormsg() { String text ->
            linkWithText(panel, text)
        }
        errors { module(ErrorsModule)}
    }

    boolean validateCompanyName() {
        waitFor {
            companyname
        }
        return $(Companyalretmsg).text().contains('"name" may not be empty.')
    }

    boolean validateStream() {
        waitFor {
            companyname
        }
        return $(Companyalretmsg).text().contains('"stream" may not be empty.')
    }
    boolean validateProfileName() {
        waitFor {
            companyname
        }
        return $(Companyalretmsg).text().contains('"profileName" may not be empty.')
    }

    void createcompany(){
        CreatecompLink.click()
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->

            if("${fieldName}"=="name" && fieldValue.length()>0)
            {
                Date date = new Date()
                //String datePart = date.format("dd/MM/yyyy")
                String datePart = date[Calendar.YEAR].toString()+date[Calendar.MONTH].toString()+date[Calendar.DAY_OF_MONTH].toString()+date[Calendar.HOUR].toString()+date[Calendar.MINUTE].toString()
                fieldValue=fieldValue+datePart
                println("at creation"+fieldValue)
            }

            if("${fieldName}"=="urlKey")
            {
                Date date = new Date()
                String datePart = date[Calendar.YEAR].toString()+date[Calendar.MONTH].toString()+date[Calendar.DAY_OF_MONTH].toString()+date[Calendar.HOUR].toString()+date[Calendar.MINUTE].toString()
                fieldValue=fieldValue+datePart
            }


            form."${fieldName}" = parseFieldValue(fieldValue)
        }

    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void submit() {
        submitButton.click()
    }

    void checkmsg() {

        validateCompanyName()
        validateStream()
        validateProfileName()


    }


}