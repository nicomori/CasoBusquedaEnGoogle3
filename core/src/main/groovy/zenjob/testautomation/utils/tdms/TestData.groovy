package zenjob.testautomation.utils.tdms

import groovy.json.JsonSlurper

class TestData {
    final String TDMS_DIRECTORY = getClass().getResource('testData').toURI().toString()

    Map get(String id) {
        File data = new File("${TDMS_DIRECTORY}/${id}")
        JsonSlurper jsonSlurper = new JsonSlurper()
        return jsonSlurper.parseText(data.text) as Map
    }
}
