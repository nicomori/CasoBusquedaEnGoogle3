package zenjob.testautomation.scripts

import groovy.json.JsonSlurper
import zenjob.testautomation.sync.TestCaseResult
import zenjob.testautomation.sync.TestRailResultPusher


TestRailResultPusher pusher = new TestRailResultPusher()

println 'Pushing results to TestRail'

final String CUCUMBER_JSON = 'build/reports/results.json'


def jsonFile = new File(CUCUMBER_JSON)
//Set default runId value not to fail Build
def runId = System.getProperty("runId").isEmpty() ? 38 : Long.valueOf(System.getProperty("runId"))

if (!jsonFile.exists()) {
    throw new Exception("the json file `${CUCUMBER_JSON}` does not exist")
}

if (jsonFile.text == '') {
    throw new Exception("the json file `${CUCUMBER_JSON}` is empty")
}

JsonSlurper jsonSlurper = new JsonSlurper()
def json = jsonSlurper.parse(jsonFile)

List<TestCaseResult> results = pusher.parseResultsFromJson(json)
pusher.pushResults(results, runId)
