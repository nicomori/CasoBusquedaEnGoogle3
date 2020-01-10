package framework.src.Orchestration

import geb.Browser
import framework.src.Pages.ZVS.job.JobCreationPage
import framework.src.Pages.ZVS.job.JobDetailsPage
import framework.src.Pages.ZVS.job.JobOverviewPage
import framework.src.Util.utils.UrlUtil

class UIJob {
    // Orchestration entities work on one browser
    private Browser browser

    int id

    // create a job from job overview page
    // store the id
    // the core of the management module is the starting point for the entity Orchestration

    private int getJobIdFromUrl() {
        Integer.parseInt(UrlUtil.getUrlSegments('/', browser.driver.getCurrentUrl()).last())
    }

    void orchestrateCreation() {

        browser.to(JobOverviewPage)

        JobOverviewPage jobOverviewPage = browser.at(JobOverviewPage)
        jobOverviewPage.createNewJobLinkClick()
        JobCreationPage jobCreationPage = browser.at(JobCreationPage)
        jobCreationPage.fillMandatoryFieldsWithDefaultData()
        jobCreationPage.uncheckAutomatchingCheckBox()
        jobCreationPage.submit()

        browser.at(JobDetailsPage)

        id = getJobIdFromUrl()
    }

    void orchestrateCreationWithAutoMatch() {

        browser.to(JobOverviewPage)

        JobOverviewPage jobOverviewPage = browser.at(JobOverviewPage)
        jobOverviewPage.createNewJobLinkClick()
        JobCreationPage jobCreationPage = browser.at(JobCreationPage)
        jobCreationPage.fillMandatoryFieldsWithDefaultData()
        jobCreationPage.checkAutomatchingCheckBox()
        jobCreationPage.submit()

        browser.at(JobDetailsPage)

        id = getJobIdFromUrl()
    }

    void attachToBrowser(Browser browser) {
        this.browser = browser
    }
}
