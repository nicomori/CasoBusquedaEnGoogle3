package framework.src.Pages.CompanyApp

import framework.src.Pages.BasePage
import framework.src.Pages.CompanyApp.modules.FiltersModule
import geb.Module
import org.openqa.selenium.By

import java.util.regex.Pattern

class BookedShiftsPage extends BasePage {

    static at = {
        Pattern pattern = ~/Gebucht und Beendet/
        header.text() ==~ pattern
    }

    static content = {
        HEADER_BAR_BUTTON_TODAY { $(By.xpath(" (//label[contains(@class,'Switch')])[1]")) }
        header { $('[class*="ShiftsView__ViewWrapper"] h1[class*="Headline"]') }
        bookedTalentsRows { $('[class*="ShiftsView"] table tbody tr').moduleList(BookedShiftRow) }
        filterPanel { module FiltersModule }
    }

    String getPageTitle() {
        println(title)
    }

    String getLabelInTheButtonTodayInHeaderBar() {
        println(getDriver().findElement(HEADER_BAR_BUTTON_TODAY).getText())
        sleep(3000)
    }

    void setStartDayFilter(Date date) {
        println("jjjjjj uuuuu ttrttrtfggbg  222222222")
        waitFor (10, { bookedTalentsRows.first().isDisplayed() })
        println("jjjjjj uuuuu ttrttrtfggbg  444444444")
        filterPanel.startDateField.click()
        println("jjjjjj uuuuu ttrttrtfggbg  6666666666")
        waitFor { filterPanel.calendarDays.first().isDisplayed() }
        println("jjjjjj uuuuu ttrttrtfggbg  3333333333")
        filterPanel.selectDay(date[Calendar.DAY_OF_MONTH])
        println("jjjjjj uuuuu ttrttrtfggbg  22221112121232")
    }

    void checkTalentIsPresentedOnPage(String talent, Date shiftDate) {

        println("jjjjjj uuuuu ttrttrtfggbg  ")


        waitFor (10, { bookedTalentsRows.first().isDisplayed() })

        println("jjjjjj oo oooooo iiiiiiiii ")

        println("firstNameOfTheTalent firstNameOfTheTalent == "+firstNameOfTheTalent)
        println("tem.talentName.text() tem.talentName.text() == ")
        bookedTalentsRows.each { item ->
            println(item.talentName.text())

        }

        def result = false
        bookedTalentsRows.each { item ->
            if(item.talentName.text().contains(firstNameOfTheTalent)) {
                result = true
                return true
            }
        }

//        assert result : "Talent wasn't found"


    }

    boolean isDatesEqual(Date expected, String actual) {
        //TODO: make strings comparison
        def actualDate = new Date().parse("dd.MM.yyyy", actual+new Date()[Calendar.YEAR])
        return actualDate.equals(expected)
    }

    void selectJobDefinitionFilter(String filter) {
        filterPanel.selectJobDefinitionFilter(filter)
    }
}

class BookedShiftRow extends Module {
    static content = {
        talentName { $('[class*="NamePositionContainer"] div:nth-child(1) h3[class*="FullName"]') }
        //NB: this 1 depends on the browser window size. 1 is for "middle" resolution. 0 for highest and lowest
        shiftDate { $('td:nth-child(2) span')[1] }
        shiftTime { $('td:nth-child(3) span') }
        shiftStatus { $('label[class*="StatusLabelWrapper"]') }
        shiftLocation { $('td:nth-child(5) span') }
        jobDefinition { $('td:nth-child(6) span') }
    }
}