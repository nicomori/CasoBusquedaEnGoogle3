package zenjob.testautomation.sync

import groovy.json.JsonSlurper
import zenjob.testautomation.sync.executors.FileSystemSchemaExecutor
import zenjob.testautomation.sync.obj.Project
import zenjob.testautomation.sync.readers.TestRailReader
import zenjob.testautomation.sync.schemas.FileSystemSchema
import zenjob.testautomation.sync.schemas.TestRailSchema
import zenjob.testautomation.sync.translators.TestRailToFileSystemTranslator

class TestRailSynchronizer {
    List<Project> filters = []

    TestRailSynchronizer onlyProjectsWithIds() {
        def json = new JsonSlurper().parse(new File("testrail-sync/src/main/resources/testRailSyncData.json"))

        json.each { item -> this.filters.add(new Project(item))
        }
        return this
    }


    void synchronize() {
        def testRailReader = new TestRailReader()

        TestRailToFileSystemTranslator tr2fsTranslator = new TestRailToFileSystemTranslator()
        println "filters: ${filters.toString()}"
        TestRailSchema trs = testRailReader.read(filters)
        tr2fsTranslator = new TestRailToFileSystemTranslator(trs)
        List<FileSystemSchema> fss = tr2fsTranslator.translate()
        FileSystemSchemaExecutor fssExecutor = new FileSystemSchemaExecutor(fss)
        fssExecutor.execute()
    }


}
