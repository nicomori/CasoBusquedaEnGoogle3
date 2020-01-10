package framework.src.Pages.ZVS.job

import geb.Module
import org.openqa.selenium.By
import framework.src.Pages.BasePage
import java.util.regex.Pattern


class JobOverviewPage extends BasePage {
    static at = {
        Pattern pattern = ~/Job Overview ([^"]*)/
        header.text() ==~ pattern
    }

    static url = 'https://staging-main.zenjob.org/ops/job/list'

    static content = {
        header { $(By.xpath('//h1')) }
        createJobLink { $(By.xpath("//a[@href='/ops/job/create']")) }
        form { $(By.xpath('//form')) }
        applyFilterButton { $(By.xpath('//input[@type="submit"]')) }
        jobTable { $("table") }
        tableRows { jobTable.$('tbody > tr').moduleList(JobRow) }
        editButton{String jobId -> $(By.xpath("//a[@href='/ops/job/edit/${jobId}']"))  }
        matchingButton {String jobId -> $(By.xpath("//a[@href='/ops/job/candidates/${jobId}']")) }

        matchingButton2 { $(By.xpath("//a[contains(@href,'/ops/job/candidates/')]")) }


        automatchingFlagTrue { $(By.xpath("/i[@class='fa fa-check fa-fw text-success']")) }
        oderKeyField { $(By.xpath("//input[@id='zendeskOrderKey']")) }

    }


    void createNewJobLinkClick(){
        createJobLink.click()
    }

    void editButtonForJobClick( def jobId){
        editButton("${jobId}").click()
    }

    void editButtonForJobClick2( def jobId){
        for(int i=0;i<tableRows.size();i++)
        {
            if (tableRows[i].id == jobId) {
                assert tableRows[i].editButton.click()
                break
            }
        }

    }

    void checkActionsForJob(def jobId) {

            for(int i=0;i<tableRows.size();i++)
            {
                if (tableRows[i].id == jobId) {
                    assert tableRows[i].actions.find(editButton)
                    assert tableRows[i].actions.find(matchingButton)
                }
            }
        }

    void checkAutomatchingFlagTrue(def jobId) {

        for(int i=0;i<tableRows.size();i++)
        {
            if (tableRows[i].id == jobId) {
                assert tableRows[i].automatching.find(automatchingFlagTrue)
            }
        }
    }

    void checkAutomatchingFalse(def jobId) {

        for(int i=0;i<tableRows.size();i++)
        {
            if (tableRows[i].id == jobId) {
                assert !tableRows[i].automatching.find(automatchingFlagTrue)
            }
        }
    }

    void matchingButtonForAJobClick2(def jobId) {
        matchingButton("${jobId}").click()
    }

    void matchingButtonForAJobClick3(def jobId) {
        println('kkkkkkkkkk 11111111')
        sleep(2000)
        matchingButton2.click()
        println('kkkkkkkkkk 11111111bbbb')
    }

    void matchingButtonForAJobClick( def jobId){
        for(int i=0;i<tableRows.size();i++)
        {
            if (tableRows[i].id == "${jobId}") {
                assert tableRows[i].matchingButton.click()
                break
            }
        }
    }

    void findJobByOderKey(def oderKey) {

        println('we are going to use this order number '+orderNumberCreated)

        oderKeyField.value(orderNumberCreated)
        applyFilterButton.click()
    }

    void findJobByOderKey2(def oderKey) {

        println(orderNumberCreated)
        println('kkkkkkkkkk 2222222222')

        println('we are going to use this order number '+orderNumberCreated)


        println('kkkkkkkkkk 2222222222 c')
        oderKeyField.value(orderNumberCreated)

        println('kkkkkkkkkk 2222222222 e')
        applyFilterButton.click()

        println('kkkkkkkkkk 2222222222 d')
    }
}

class JobRow extends Module {
    static content = {
        cell { $("td") }
        id { cell[0].text() }
        zendeskOderKey { cell[1].text() }
        jobCompany { cell[2].text() }
        region { cell[3].text() }
        cityDistrict { cell[4].text() }
        start { cell[5].text() }
        dueDate { cell[6].text() }
        matches { cell[7].text() }
        applications { cell[8].text() }
        bookingRatio { cell[9].text() }
        timeLeftToStaff { cell[10].text() }
        state { cell[11].text() }
        operator { cell[12].text() }
        automatching { cell[13].text() }
        actions { cell[14].text() }
        editButton { cell[14].$(By.xpath("./a[contains(@href,'/ops/job/edit/')]")) }
        matchingButton { cell[14].$(By.xpath("./a[contains(@href,'/ops/job/candidates/')]")) }
    }
}

