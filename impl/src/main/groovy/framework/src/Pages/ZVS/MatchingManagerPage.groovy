package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import framework.src.Util.TestConstants2
import org.openqa.selenium.By

import java.text.SimpleDateFormat

class MatchingManagerPage extends BasePage {
    //Locators
    static content = {
        editFieldLastName { $(By.xpath("//*[@id='lastName']")) }
        editFieldFirstName { $(By.xpath("//*[@id='firstName']")) }
        buttonApplyFilters { $(By.xpath("//*[@id='search']")) }
        checkboxFirstTalent { $(By.xpath("(//*[contains(@id,'matchCheckbo')])[1]")) }
        buttonMatchSelectedTalent { $(By.xpath("//*[contains(@id,'matchSelectedButton')]")) }
        labelTabMatched { $(By.xpath("(//li[@role='presentation'])[5]")) }
        labelNameOfTheFirstPersonOfTheList {
            $(By.xpath("((//table[contains(@class,'table-hover')])[last()]//tbody[@class='panel-body']//tr/td)[2]"))
        }
        alertWeAreWaitingUntilTheExecutionRun { $(By.xpath("//*[contains(@class,'alert alert-info')]")) }


    }

    //methods section
    /**
     * this method fill the values first name and last name and press in the button apply filters
     *
     */
    void fillFiltersOfAUserAndPressButtonApplyFilter() {
        boolean continueChecking = true
        waitFor(120, { editFieldLastName.isDisplayed() })


        editFieldLastName.value(TestConstants2.UsersTalent.TALENT_USER_LASTNAME)
        editFieldFirstName.value(TestConstants2.UsersTalent.TALENT_USER_FIRSTNAME)


        sleep(5555)

        buttonApplyFilters.click()


        sleep(5555)


        buttonMatchSelectedTalent.click()

        String talentNumber = 'null'

        try {

            println('Starting to get the name of the talent')
            println('this is the complete name of the talent = ' + labelNameOfTheFirstPersonOfTheList.text())
            println('Starting to extract the number of the talent')

            println('Starting to extract the number of the talent === ' + labelNameOfTheFirstPersonOfTheList.text().substring(labelNameOfTheFirstPersonOfTheList.text().size() - 4, labelNameOfTheFirstPersonOfTheList.text().size()))


            talentNumber = labelNameOfTheFirstPersonOfTheList.text().substring(labelNameOfTheFirstPersonOfTheList.text().size() - 4, labelNameOfTheFirstPersonOfTheList.text().size())


            firstNameOfTheTalent = labelNameOfTheFirstPersonOfTheList.text().substring(labelNameOfTheFirstPersonOfTheList.text().size() - 15, labelNameOfTheFirstPersonOfTheList.text().size())

            TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_MIDDLE = talentNumber


            println('We find this talent user number = ' + TestConstants2.UsersTalent.TALENT_USER_EMAIL_USERNAME_PART_MIDDLE)

            sleep(5555)
            println('Making click in the first checkbox of the first talent at the moment to select 1 to match')
            checkboxFirstTalent.click()
        } catch (Exception e) {
            println('Warning - appear a problem at the moment to try to find the talent user ' + TestConstants2.UsersTalent.TALENT_USER_LASTNAME + " - " + TestConstants2.UsersTalent.TALENT_USER_FIRSTNAME)
            println(e)
        }


        sleep(5555)
        buttonMatchSelectedTalent.click()

        sleep(5555)
        println(labelTabMatched.value())

        browser.driver.navigate().refresh()

        String textInTheMatchingTab = 'some value'

        println('Starting to wait until the tab in Matching apper with the number 1')
        while (continueChecking) {
            textInTheMatchingTab = browser.driver.findElement(By.xpath("(//li[@role='presentation'])[5]")).getText()

            println("In the tab of matching we find the text: " + textInTheMatchingTab)

            waitFor(40, { labelTabMatched.isDisplayed() })


            try {
                if (textInTheMatchingTab.contains('1')) {
                    println('We can see the value 1 in the tab Matched')
                    textInTheMatchingTab.contains('1')
                } else {
                    println('The content of the tab Matched dont have the value 1, starting to refresh and wait.')
                }

                if (textInTheMatchingTab.contains('1')) {
                    println('we find the value 1 in the tab Matching')
                    continueChecking = false

                } else {
                    println('Starting to refresh the browser, looking to the match in 1. ')

                    if (verifyIfWeAreWaitingForTheBotRun()) {
                        println('Starting to refresh the browser, looking to the match in 1. ')
                        browser.driver.navigate().refresh()
                    } else {
                        println('Starting to re select the talent and refreshing the browser ')
                        sleep(2222)
                        buttonMatchSelectedTalent.click()

                        sleep(1111)
                        println(labelTabMatched.value())

                        sleep(1111)
                        browser.driver.navigate().refresh()
                    }


                }

            } catch (Exception ex) {
                println("Catching the exception")
                browser.driver.navigate().refresh()
            }

            sleep(5555)


        }
    }


    boolean verifyIfWeAreWaitingForTheBotRun() {
        println('Starting to verify if the alert waiting, for the bot appear properly')

        String messagePopUp = ' '

        try {
            messagePopUp = alertWeAreWaitingUntilTheExecutionRun.text()
        }catch(Exception e){
            println('The message is not displayed, returning false   ')
            return false
        }


        if (messagePopUp.length() > 2) {
            return true
        } else {
            println('The message is not displayed, returning false   ')
            return false
        }

    }

    int getTheCurrentMinutes() {
        Date today = new Date()
        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        int currentMinutes = 0

        format = new SimpleDateFormat("mm");
        currentMinutes = Integer.parseInt(format.format(today));

        return Integer.parseInt(format.format(today))
    }

}