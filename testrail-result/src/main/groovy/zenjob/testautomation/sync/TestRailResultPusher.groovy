package zenjob.testautomation.sync

import zenjob.testautomation.sync.testrailapi.TestRailApiWrapper

class TestCaseResult {
    int id
    boolean status
}

class TestRailResultPusher {
    private static int parseIdFromTag(String tag) {
        String prefix = '@tid'
        tag.substring(prefix.length()).toInteger()
    }


    static List<TestCaseResult> parseResultsFromJson(Object json) {
        List<TestCaseResult> testCaseResults = []

        json[0].elements.each { def element ->
            if (element.tags) {
                int id = parseIdFromTag(element.tags[0].name as String)
                boolean allPassed = true
                element.steps.each { def step ->
                    if(step.result.status == "failed") allPassed = false
                }
                testCaseResults << new TestCaseResult(id: id, status: allPassed)
            }
        }
        testCaseResults
    }

    static void pushResults(List<TestCaseResult> results, long runId) {
        // logging
        results.each { TestCaseResult tc ->
            String statusOutputTemplate = tc.status ? 'passed' : 'failed'
            println "The test case with id: ${tc.id} ${statusOutputTemplate}"
        }


        TestRailApiWrapper api = new TestRailApiWrapper()

        // add results to test run
        // We would not create new run every time when we're running tests, so we'll use a parameter to provide already created runId
        api.addResultsForCases(runId, results)
    }
}
