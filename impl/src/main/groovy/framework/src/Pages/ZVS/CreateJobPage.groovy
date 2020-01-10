package framework.src.Pages.ZVS

import framework.src.Pages.BasePage
import org.openqa.selenium.By

import java.text.SimpleDateFormat
import java.time.YearMonth

class CreateJobPage extends BasePage {
    //Locators
    static content = {
        header { $(By.xpath('//h1')) }
        labelPageTitle { $(By.xpath("//h1")) }
        editfieldJobTitle { $(By.xpath("//input[@name='jobTitle']")) }
        checkBoxAutoMatching { $(By.xpath("//input[@name='autoMatch']")) }
        buttonCreateJob { $(By.xpath("//*[@id='createJob']")) }
        buttonCreateJob22222 { $(By.xpath("(//*[@class='bootstrap-tagsinput'])[1]")) }

        buttonOpenCalendar { $(By.xpath("//*[@id='startDatePicker0']/span")) }
        editfieldShiftStartDate { $(By.xpath("//*[@id='startDatePicker0']/input")) }
        editfieldShiftStartTime { $(By.xpath("//*[@id='jobShiftStartTime-0']")) }
        editfieldShiftFinishDate { $(By.xpath("//*[@id='jobShiftEndTime-0']")) }

    }

    //methods section
    /**
     * this method make click in the button create job in the page order details
     *
     */
    void fillTheFormAndCreateAJobWithoutAutoMarching() {
        waitFor { labelPageTitle.isDisplayed() }

        String currentWindowHandle = browser.driver.getWindowHandle();

        //Get the list of all window handles
        ArrayList<String> windowHandles = new ArrayList<String>(browser.driver.getWindowHandles());

        for (String window : windowHandles) {

            //if it contains the current window we want to eliminate that from switchTo();
            if (window != currentWindowHandle) {
                //Now switchTo new Tab.
                browser.driver.switchTo().window(window);
                //Do whatever you want to do here.
            }
        }

        editfieldJobTitle.value('Demo E2E - automation testing.')

        checkBoxAutoMatching.click()
        buttonCreateJob.click()
    }


    /**
     * this method make click in the button create job in the page order details
     *
     */
    void setAfterEffectiveSpecialsDays() {
        Date today = new Date()

        SimpleDateFormat format = new SimpleDateFormat("yyyy/MM/dd");

        String DateToStr = format.format(today)

        int currentDay = 0
        int currentMonth = 0
        int currentYear = 0

        int currentHour = 0
        int currentMinutes = 0

        format = new SimpleDateFormat("dd");
        currentDay = Integer.parseInt(format.format(today))

        format = new SimpleDateFormat("MM");
        currentMonth = Integer.parseInt(format.format(today))

        format = new SimpleDateFormat("yyyy");
        currentYear = Integer.parseInt(format.format(today))

        format = new SimpleDateFormat("HH");
        currentHour = Integer.parseInt(format.format(today));

        format = new SimpleDateFormat("mm");
        currentMinutes = Integer.parseInt(format.format(today));


        int dayToUseInAfterEffective = 0 //----
        int endShiftHourToUseInAfterEffective = 0 //----
        int endShiftMinutesToUseInAfterEffective = 0 //----
        int startShiftHourToUseInAfterEffective = 0 //----
        int monthToUseInAfterEffective = 0 //----
        int minutesLess2 = 1
        int minutesMore = 25

        String dayToUseInAfterEffectiveString = ''
        String endShiftHourToUseInAfterEffectiveString = ''
        String endShiftMinutesToUseInAfterEffectiveString = ''
        String startShiftHourToUseInAfterEffectiveString = ''
        String monthToUseInAfterEffectiveString = ''

        YearMonth yearMonthObject = YearMonth.of(currentYear, currentMonth - 1)

        // section Shift Start Date
        // complete the part of the days for auto effective (2 days before of the current day)
        if (currentDay - 2 <= 0) {

            int totalDaysInTheLastMonth = yearMonthObject.lengthOfMonth()

            if (currentDay - 2 == 0) {
                dayToUseInAfterEffective = totalDaysInTheLastMonth - 1
            }

            if (currentDay - 2 == -1) {
                dayToUseInAfterEffective = totalDaysInTheLastMonth - 2
            }
        } else {
            dayToUseInAfterEffective = currentDay - 2
        }


        // section Shift End Time
        // this part is where we make the logic to define the day and the hour
        if (currentMinutes + minutesMore <= -1) {
            endShiftHourToUseInAfterEffective = currentHour - 1
            endShiftMinutesToUseInAfterEffective = 60 + (currentMinutes + minutesMore)
        } else {
            endShiftHourToUseInAfterEffective = currentHour


            // we need to make this fix beacuse in case the current minutes is bigger of 30, at the moment to
            // put the quantity of minutes this value is going to be more bigger in relation with 60 minutes.
            if (currentMinutes >= 30) {
                //we increase the hour in 1 because is more of 60 minutes the difference
                endShiftHourToUseInAfterEffective = endShiftHourToUseInAfterEffective + 2
                endShiftMinutesToUseInAfterEffective = 60 - (currentMinutes + minutesMore)
            }
        }

        // section Shift Start Time
        //in this section we define what happen if the start day is < to 0
        if (endShiftHourToUseInAfterEffective - 6 < 0) {

            //in case if the day 1 of the month we need to put the last day of the month, and change the hour too.
            if (currentDay == 1) {
                dayToUseInAfterEffective = yearMonthObject.lengthOfMonth()
                startShiftHourToUseInAfterEffective = (endShiftHourToUseInAfterEffective - 6) + 24
                monthToUseInAfterEffective = currentMonth - 1
            }
        } else {
            startShiftHourToUseInAfterEffective = endShiftHourToUseInAfterEffective - 6
            monthToUseInAfterEffective = currentMonth
        }


        if (dayToUseInAfterEffective < 10) {
            dayToUseInAfterEffectiveString = '0' + dayToUseInAfterEffective
        } else {
            dayToUseInAfterEffectiveString = dayToUseInAfterEffective + ''
        }

        if (endShiftHourToUseInAfterEffective < 10) {
            endShiftHourToUseInAfterEffectiveString = '0' + endShiftHourToUseInAfterEffective
        } else {
            endShiftHourToUseInAfterEffectiveString = endShiftHourToUseInAfterEffective + ''
        }

        endShiftMinutesToUseInAfterEffective = currentMinutes

        if (endShiftMinutesToUseInAfterEffective < 10) {
            endShiftMinutesToUseInAfterEffectiveString = '0' + endShiftMinutesToUseInAfterEffective
        } else {
            endShiftMinutesToUseInAfterEffectiveString = endShiftMinutesToUseInAfterEffective + ''
        }

        if (startShiftHourToUseInAfterEffective < 10) {
            startShiftHourToUseInAfterEffectiveString = '0' + startShiftHourToUseInAfterEffective
        } else {
            startShiftHourToUseInAfterEffectiveString = startShiftHourToUseInAfterEffective + ''
        }



        // This is maked to use in case the day is one of the days of the start of the month
        if (currentDay < 4) {
            monthToUseInAfterEffective = currentMonth -1
        } else {
            monthToUseInAfterEffective = currentMonth
        }

        if (monthToUseInAfterEffective < 10) {
            monthToUseInAfterEffectiveString = '0' + monthToUseInAfterEffective
        } else {
            monthToUseInAfterEffectiveString = monthToUseInAfterEffective + ''
        }


        waitFor { labelPageTitle.isDisplayed() }

        String currentWindowHandle = browser.driver.getWindowHandle();

        //Get the list of all window handles
        ArrayList<String> windowHandles = new ArrayList<String>(browser.driver.getWindowHandles());

        for (String window : windowHandles) {

            //if it contains the current window we want to eliminate that from switchTo();
            if (window != currentWindowHandle) {
                //Now switchTo new Tab.
                browser.driver.switchTo().window(window);
                //Do whatever you want to do here.
            }
        }




        println('Current hour: '+currentHour)
        println('Current minutes: '+currentMinutes)
        println('--------------------------')
        println('checkout hour to use in Auto Effective: '+endShiftHourToUseInAfterEffectiveString)
        println('minutes to use in Auto Effective: '+endShiftMinutesToUseInAfterEffectiveString)
        println('day to use in Auto Effective: '+dayToUseInAfterEffectiveString)
        println('month to use in Auto Effective: '+monthToUseInAfterEffectiveString)

        valuesToUseInTheTestDate = Integer.parseInt(dayToUseInAfterEffectiveString)
        valuesToUseInTheTestHourCheckIn = Integer.parseInt(startShiftHourToUseInAfterEffectiveString)
        valuesToUseInTheTestHourCheckout = Integer.parseInt(endShiftHourToUseInAfterEffectiveString)
        valuesToUseInTheTestMinutesCheckout = Integer.parseInt(endShiftMinutesToUseInAfterEffectiveString)


        println('--------------------------')
        println('--------------------------')


        editfieldJobTitle.value('Demo E2E - automation testing.')


        sleep(3000)

        editfieldShiftStartDate.value(currentYear + '-' + monthToUseInAfterEffectiveString + '-' + dayToUseInAfterEffectiveString)
        sleep(3000)
        editfieldShiftStartTime.value(startShiftHourToUseInAfterEffectiveString + ':00')
        sleep(3000)
        editfieldShiftFinishDate.value(endShiftHourToUseInAfterEffectiveString + ':' + endShiftMinutesToUseInAfterEffectiveString)
        sleep(3000)

        checkBoxAutoMatching.click()
        buttonCreateJob.click()

    }


    /**
     * this method return the numbers of days of the last month
     *
     * @return int with the total number of the days of the last month
     */
    int verifyQuantityOfDaysOfTheLastMonth(int currentMonthNumber, int year) {
        println('Starting to get the total of days of the last month')
        YearMonth yearMonthObject = YearMonth.of(2019, 3);


        return yearMonthObject.lengthOfMonth()

    }
}