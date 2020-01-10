package zenjob.testautomation.sync

import groovy.json.JsonSlurper
import spock.lang.Specification

class TestRailResultPusherSpec extends Specification {
    void 'init'() {
        given:
        TestRailResultPusher pusher = new TestRailResultPusher()

        println 'executing'

        final String CUCUMBER_JSON = '../build/reports/results.json'


        def jsonFile = new File(CUCUMBER_JSON)

        if (!jsonFile.exists()) {
            throw new Exception("the json file `${CUCUMBER_JSON}` does not exist")
        }

        if (jsonFile.text == '') {
            throw new Exception("the json file `${CUCUMBER_JSON}` is empty")
        }

        JsonSlurper jsonSlurper = new JsonSlurper()
        def json = jsonSlurper.parse(jsonFile)


        when:
        List<TestCaseResult> results = pusher.parseResultsFromJson(json)

        then:
        pusher.pushResults(results)
        //pusher.pushResults(results)
    }
}
