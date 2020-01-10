package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import org.junit.Assert
import org.openqa.selenium.By
import org.openqa.selenium.Keys
import org.openqa.selenium.WebElement

class ShiftsPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    String valuesToUseInTheTestHourCheckInPlusOneString = ''
    String valuesToUseInTheTestHourCheckoutPlusOneString = ''

    int orderIndex = 1

    //Locators
    static content = {
        buttonOngoingAndFinished { $(By.xpath("(//*[contains(@class,'ShiftsView')])[2]//li[2]")) }
        buttonSeeDetailsOfTheShift222 {
            $(By.xpath("(//li[contains(@class,'CompleteShiftTabletEntry')]//div[contains(@class,'CompleteShiftTabletEntry')]/div/button)[%s]"))
        }
        editFieldCheckIngTimeShiftDeatils { $(By.xpath("//*[@name='companyCheckinTime']")) }
        editFieldCheckOutTimeShiftDeatils { $(By.xpath("//*[@name='companyCheckoutTime']")) }
        dropdownListReasonShiftDetails { $(By.xpath("//label[contains(@class,'Dropdown')]")) }
        option2DropdownListReasonShiftDetails { $(By.xpath("//ul[contains(@class,'Dropdown')]//li[2]")) }
        textAreaShiftDetails { $(By.xpath("//textarea[contains(@class,'ShiftsViewPanelForm')]")) }
        buttonAcceptChangesInTheShiftDetails {$(By.xpath("(//*[contains(@class,'ShiftsViewPanelContainer')]//button)[2]"))}
    }

    String listContainerDates = "//li[contains(@class,'CompleteShiftTable')]/div[contains(@class,'CompleteShiftTable')][2]/div[contains(@class,'CompleteShiftTable')][1]"
    String buttonSeeDetailsOfTheShift = "((//li[contains(@class,'CompleteShiftTabletEntry')])[%s]//button)[1]"
    String checkInAndCheckOutContainerData = "(//li[contains(@class,'CompleteShiftTable')]/div[contains(@class,'CompleteShiftTable')][2]/div[2])[%s]"

    //methods section
    /**
     * This method make click in the button Ongoing and Finished.
     */
    void makeClickInTheButtonOnGoingAndFinished() {
        sleep(2000)
        waitFor { buttonOngoingAndFinished.isDisplayed() }
        buttonOngoingAndFinished.click()
    }

    /**
     * This method get all the shift in the screen and store all the texts inside of the calendar in a list to return this value.
     *
     * @return List<String> with the content of all the shift in the screen.
     */
    List<String> getListOfShiftWithAllDatesInformations() {
        List<WebElement> listWithTheDateInfoOfShifts = getDriver().findElements(By.xpath(listContainerDates))
        List<String> listOfTheTextInsideOfTheDatesOfTheShifts = new ArrayList<String>()

        for (int i = 0; i < listWithTheDateInfoOfShifts.size(); i++) {
            listOfTheTextInsideOfTheDatesOfTheShifts.add(listWithTheDateInfoOfShifts.get(i).getText())
        }

        return listOfTheTextInsideOfTheDatesOfTheShifts;
    }


    /**
     * This method make click in the button Ongoing and Finished.
     */
    void compareTheElementsinsideOfTheDatesToAccessToTheRightShift() {
        openTheShiftDetailsPanel()

        sleep(3000)

        println('Shift checking hour used: ' + valuesToUseInTheTestHourCheckIn)
        println('Shift checkout hour used: ' + valuesToUseInTheTestHourCheckout)

        int valuesToUseInTheTestHourCheckInPlusOne = valuesToUseInTheTestHourCheckIn + 2
        int valuesToUseInTheTestHourCheckoutPlusOne = valuesToUseInTheTestHourCheckout + 2

//        String valuesToUseInTheTestHourCheckInPlusOneString
//        String valuesToUseInTheTestHourCheckoutPlusOneString


        if(valuesToUseInTheTestHourCheckInPlusOne < 10){
            valuesToUseInTheTestHourCheckInPlusOneString = "0"+valuesToUseInTheTestHourCheckInPlusOne + ":00"
        }else{
            valuesToUseInTheTestHourCheckInPlusOneString = valuesToUseInTheTestHourCheckInPlusOne + ":00"
        }

        if(valuesToUseInTheTestHourCheckoutPlusOne < 10){
            valuesToUseInTheTestHourCheckoutPlusOneString = "0"+valuesToUseInTheTestHourCheckoutPlusOne + ":00"
        }else{
            valuesToUseInTheTestHourCheckoutPlusOneString = valuesToUseInTheTestHourCheckoutPlusOne + ":00"
        }

        println('Fake Shift checking hour to setup: ' + valuesToUseInTheTestHourCheckInPlusOneString)
        println('Fake Shift checkout hour to setup: ' + valuesToUseInTheTestHourCheckoutPlusOneString)

        println('Starting to send the value for CheckIn: '+valuesToUseInTheTestHourCheckInPlusOneString)
        sleep(1111)
        getDriver().findElement(By.name('companyCheckinTime')).sendKeys(Keys.chord(Keys.CONTROL, "a"), valuesToUseInTheTestHourCheckInPlusOneString)

        println('Starting to send the value for CheckOut: '+valuesToUseInTheTestHourCheckoutPlusOneString)
        sleep(1111)
        getDriver().findElement(By.name('companyCheckoutTime')).sendKeys(Keys.chord(Keys.CONTROL, "a"), valuesToUseInTheTestHourCheckoutPlusOneString)

        sleep(2222)
        dropdownListReasonShiftDetails.click()
        sleep(2222)
        option2DropdownListReasonShiftDetails.click()

        sleep(2222)
        textAreaShiftDetails.click()

        sleep(2222)
        textAreaShiftDetails.value('Automation testing.')

        sleep(3000)
        buttonAcceptChangesInTheShiftDetails.click()
    }

    /**
     * This method retreive all the information related to the checking and checkout data in the shift.
     *
     * @return String with all the information about the checkoin and checkout times.
     */
    String getInformationAboutCheckingAndCheckoutData() {
        println("Starting to get the information of checking and checkout data in the shift")
        sleep(2222)
        int indexWithTheRightShift = compareAllTheShiftAndRetriveTheIndexOfTheRightShift() + 1

        String dataValue = getDriver().findElement(By.xpath(String.format(checkInAndCheckOutContainerData, indexWithTheRightShift))).getText()

        println(dataValue)

        return dataValue
    }


    /**
     * This method retreive all the information related to the checking and checkout data in the shift.
     *
     * @return String with all the information about the checkoin and checkout times.
     */
    String verifyIfTheFakeCheckingHourAndFakeCheckoutHourAppearInTheShift() {
        println("Starting to validate if the fake hours of checking and checkout appear properly in the shift.")
        sleep(2222)
        int indexWithTheRightShift = compareAllTheShiftAndRetriveTheIndexOfTheRightShift() + 1

        String dataValue = getDriver().findElement(By.xpath(String.format(checkInAndCheckOutContainerData, indexWithTheRightShift))).getText()

        println('Starting to validate if the value == '+valuesToUseInTheTestHourCheckInPlusOneString+', of fake checkin, appear properly in the shift')
        Assert.assertTrue(dataValue.contains(valuesToUseInTheTestHourCheckInPlusOneString))

        println('Starting to validate if the value == '+valuesToUseInTheTestHourCheckoutPlusOneString+', of fake checkout, appear properly in the shift')
        Assert.assertTrue(dataValue.contains(valuesToUseInTheTestHourCheckoutPlusOneString))
    }

    /**
     * This method open the shift details panel.
     */
    void openTheShiftDetailsPanel() {
        //adding 1 to the index, because the zero index in java not exit in the web.
        int indexWithTheRightShift = compareAllTheShiftAndRetriveTheIndexOfTheRightShift() + 1

        try {
            println('starting to open the details of right shift')
            // open the right shift details to edit.
            getDriver().findElement(By.xpath(String.format(buttonSeeDetailsOfTheShift, indexWithTheRightShift))).click()
        }catch(Exception e){
            println('Atention: Appear an issue at the moment to open the details of the right shift, trying agatin')
            sleep(2000)
            // open the right shift details to edit.
            getDriver().findElement(By.xpath(String.format(buttonSeeDetailsOfTheShift, indexWithTheRightShift))).click()
        }
    }


    /**
     * this method get all the shift, and retrive the index with the right shift
     *
     * @return int with the right index position.
     */
    int compareAllTheShiftAndRetriveTheIndexOfTheRightShift() {
        List<String> listOfTheTextInsideOfTheDatesOfTheShifts = getListOfShiftWithAllDatesInformations()

        int indexWithTheRightShift = 0
        String hourCheckOutString = " - " + valuesToUseInTheTestHourCheckout + ":"
        String shiftMinutesCheckoutInString = valuesToUseInTheTestMinutesCheckout

        if (valuesToUseInTheTestMinutesCheckout < 10) {
            shiftMinutesCheckoutInString = "0" + valuesToUseInTheTestMinutesCheckout
        }

        for (int i = 0; i < listOfTheTextInsideOfTheDatesOfTheShifts.size(); i++) {

            //This condition verify if the item date appear properly
            if (listOfTheTextInsideOfTheDatesOfTheShifts.get(i).contains('\n' + valuesToUseInTheTestDate)) {
                //This condition verify if the item hour checking appear properly
                if (listOfTheTextInsideOfTheDatesOfTheShifts.get(i).contains('\n' + valuesToUseInTheTestHourCheckIn + ':')) {
                    //This condition verify if the item hour checkout appear properly
                    if (listOfTheTextInsideOfTheDatesOfTheShifts.get(i).contains(hourCheckOutString)) {
                        //This condition verify if the item minutes checkout appear properly
                        if (listOfTheTextInsideOfTheDatesOfTheShifts.get(i).contains(shiftMinutesCheckoutInString + '\n')) {
                            println(listOfTheTextInsideOfTheDatesOfTheShifts.get(i))
                            indexWithTheRightShift = i

                            println('We find the shift in the index: ' + i)
                        }
                    }
                }
            }
        }
        return indexWithTheRightShift
    }
}