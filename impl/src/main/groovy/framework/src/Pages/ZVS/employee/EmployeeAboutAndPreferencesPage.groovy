package framework.src.Pages.ZVS.employee


import org.openqa.selenium.By
import org.openqa.selenium.Keys
import framework.src.Pages.BasePage
import framework.src.Modules.ErrorsModule
import framework.src.Util.utils.TokenParser

//import zenjob.testautomation.Modules.ErrorsModule
//import zenjob.testautomation.pages.BasePage
//import zenjob.testautomation.utils.TokenParser

import java.util.regex.Pattern

class EmployeeAboutAndPreferencesPage extends BasePage{
    static at = {
        Pattern pattern = ~/Edit Employee/
        header.text() ==~ pattern
    }

    static content = {
        form { $(By.xpath('//form')) }
        header { $(By.xpath('//*[@class="page-header"]')) }
        updateEmployeeButton { $(By.xpath('//input[@type="submit"]')) }
        professionField {$(By.xpath('//input[@name="profession"]'))}
        employmentCity {$(By.xpath('//select[@name="employmentCity"]'))}
        employmentCityOther {$(By.xpath('//input[@name="employmentCityOther"]'))}
        aboutMe {$(By.xpath('//textarea[@id="aboutMe"]'))}
        jobCategories {$(By.xpath('//select[@name="categories"]'))}
        tags {$(By.xpath('//div[@class="bootstrap-tagsinput"]/input'))}
        errors { module(ErrorsModule)}
    }

    String parseFieldValue(String fieldValue) {
        TokenParser.parseToken(fieldValue)
    }

    void fillFields(Map<String, String> data) {
        data.each { String fieldName, String fieldValue ->
            switch ( fieldName ) {
                case "Profession":
                    professionField = parseFieldValue(fieldValue)
                    break
                case "Employment City (if selected other)":
                    employmentCityOther = parseFieldValue(fieldValue)
                    break
                case "About Me":
                    aboutMe = parseFieldValue(fieldValue)
                    break
                case "Employment City":
                    employmentCity.click()
                    employmentCity = parseFieldValue(fieldValue)
                    break
                case "Job Categories":
                    String[]  categories = parseFieldValue(fieldValue).split(',')
                    for (String values: categories) {
                        interact {
                            keyDown Keys.COMMAND
                            jobCategories = values
                            keyUp Keys.COMMAND
                        }
                    }
                    break
                case "Tags":
                    tags = parseFieldValue(fieldValue)
                    break

                default: println("There is no this field")

            }

        }
    }

    void submit() {
        updateEmployeeButton.click()
    }

}
