package framework.src.Steps.CompanyApp

import framework.src.Util.TestConstants2

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

def date = new Date()
def talentName = TestConstants2.UsersTalent.TALENT_USER_FIRSTNAME+" "+TestConstants2.UsersTalent.TALENT_USER_LASTNAME

Then(~/^I see booked talent on Booked shifts tab$/) { ->
    println('kkkkk ooooo uuuuuu ')
    //we need to verify why we send the next step, why we are making that, and what is the content of the value DATE


    page.setStartDayFilter(date)
    page.checkTalentIsPresentedOnPage(talentName, date)
    sleep(10000)
}