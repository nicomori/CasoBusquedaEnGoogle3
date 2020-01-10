package framework.src.Pages.CompanyApp.modules

import geb.Module

class FiltersModule extends Module {

    static base = { $('[class*="FilterPanel"]')}

    static content = {
        header { $('h1') }
        startDateField { $('[class*="DateInput_1"]:nth-child(1) input')}
        endDateField { $('[class*="DateInput_1"]:nth-child(3) input')}
        calendarDays { $('.CalendarMonth[data-visible=true] table td.CalendarDay') }
        jobDefinitionFilters { $('[class*="FilterSection__Wrapper"]:nth-child(2) [class*="MultiSelectFilterOptions"]') }
        showInactiveJobDefinitionsButton { $('[class*="FilterSection__Wrapper"]:nth-child(2) [class*="ActionButton"]') }
        jobLocationFilters { $('[class*="FilterSection__Wrapper"]:nth-child(3) [class*="MultiSelectFilterOptions"]') }
    }

    void selectJobDefinitionFilter(String JobDefinition) {
        //TODO: add jobDefinition select
        //def one = ['Groovy', 'Gradle', 'Grails', 'Spock']

        jobDefinitionFilters.each { filter ->
            if(filter.$('span').text().contains(JobDefinition)) {
                println(filter.$('label').text())
                filter.$('[class*="HiddenCheckbox"]').click()
                return true
            }
        }

//        def location = jobLocationFilters.stream().filter{ s -> s.$('span').text().contains(JobDefinition) }.findFirst()
//        location.$('input').click()
    }

    void selectLocationFilter(String Location) {
        //TODO: add Location select
    }

    void selectDay(Integer day) {
        calendarDays[day-1].click()
    }

}
