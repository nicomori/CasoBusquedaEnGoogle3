package zenjob.testautomation.sync

import zenjob.testautomation.sync.executors.FileSystemSchemaExecutor
import zenjob.testautomation.sync.readers.TestRailReader
import zenjob.testautomation.sync.schemas.FileSystemSchema
import zenjob.testautomation.sync.schemas.TestRailSchema
import zenjob.testautomation.sync.translators.TestRailToFileSystemTranslator
import spock.lang.Shared
import spock.lang.Specification

class TranslatorSpec extends Specification {
    @Shared TestRailToFileSystemTranslator tr2fsTranslator
    void 'test'() {
        given:
        TestRailReader testRailReader = new TestRailReader()
        //TestRailSchema trs = testRailReader.read()
        Map<String, List<Long>> filters = [projectIds: []] // add projet ids here (but only after once having done all projects
        TestRailSchema trs = testRailReader.read(filters)
        //TestRailSchema trs = testRailReader.readProject('Zenjob Value Stream')
        tr2fsTranslator = new TestRailToFileSystemTranslator(trs)

        when:
        FileSystemSchema fss = tr2fsTranslator.translate()
        FileSystemSchemaExecutor fssExecutor = new FileSystemSchemaExecutor(fss)

        then:
        assert fssExecutor.execute()
    }
}
