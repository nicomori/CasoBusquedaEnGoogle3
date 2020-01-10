package zenjob.testautomation.sync.testrailapi

import com.gurock.testrail.APIClient
import groovy.json.JsonBuilder
import zenjob.testautomation.config.Configuration


class TestRailApiWrapper {
    APIClient client

    Object getProject(long projectId) {
        client.sendGet("get_project/${projectId}")
    }

    List<Object> getSuitesForProject(long projectId) {
        client.sendGet("get_suites/${projectId}")
    }

    List<Object> getSectionsForProjectAndSuite(long projectId, long suiteId) {
        client.sendGet("get_sections/${projectId}&suite_id=${suiteId}")
    }

    List<Object> getTestCasesForSection(long projectId, long suiteId, long sectionId) {
        client.sendGet("get_cases/${projectId}&suite_id=${suiteId}&section_id=${sectionId}")
    }

    void addResultsForCases(long runId, List testCaseResults) {

        def data = [
            results:
                testCaseResults.collect({[
                    case_id: it.id,
                    status_id: it.status ? 1 : 5
                ]})
        ]

        Object json = new JsonBuilder(data)

        client.sendPost("add_results_for_cases/${runId}", json)
    }

    long addRunForProjectAndSuite(long projectId, long suiteId, String runName) {
        def data = [
            suite_id: suiteId,
            name: runName,
            include_all: true
        ]

        Object json = new JsonBuilder(data)
        Object response = client.sendPost("add_run/${projectId}", json)

        long runId = response.id

        runId
    }

    TestRailApiWrapper() {
        client = new APIClient(Configuration.instance.getConfigProperty('testRailUrl'))
        client.setUser(Configuration.instance.getConfigProperty('testRailUser'))
        client.setPassword(Configuration.instance.getConfigProperty('testRailPassword'))
    }
}
