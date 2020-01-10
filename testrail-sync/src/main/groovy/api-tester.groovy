//import com.gurock.testrail.APIClient
//import groovy.json.JsonOutput
//import groovy.json.JsonParser
//import groovy.transform.TupleConstructor
//
//import java.nio.file.FileSystem
//
//final String BASE_URL = 'https://zenjob.testrail.io'
//final String USER = 'asha.rani@zenjob.com'
//final String PASSWORD = 'Pass0815'
//
//APIClient client = new APIClient(BASE_URL)
//
//
///*
//
//GENERAL OVERVIEW
//
//A common schema for directory structure. A hierarchical group of objects.
//TestRail -> Codebase: convert TestRail structure to the common schema, compare with old common schema, convert actions to codebase.
//Codebase -> TestRail: convert Codebase structure to the common schema, compare with old common schema, convert actions to TestRail.
//
///*
//
//THE COMMON SCHEMA
//
//- features
//- scenarios/test cases
//
//? how do we add scenario backgrounds? Test data?
//
//*/
//
//
//class CommonSchema {
//    class Scenario {
//        String name
//        String content
//    }
//
//    class Feature {
//        String name
//        List<Scenario> scenarios
//    }
//
//    class Project {
//        String name
//        List<Feature> features
//    }
//
//    // projects -> features -> scenarios
//    List<Project> projects
//}
//
//
///*
//
//THE CODEBASE STRUCTURE
//
//projects -> sections -> features (-> scenarios)
//
//*/
//
//
//class FileSystemSchema {
//    static class FeatureFile {
//        String name
//
//        FeatureFile(String name) {
//            this.name = name
//        }
//    }
//
//    static class Folder {
//        String name
//
//        File file
//
//        Folder parent
//        List<Folder> children = []
//
//        List<FeatureFile> featureFiles = []
//
//        Folder(String name) {
//            this.name = name
//        }
//
//        void resolveChildren() {
//            if (file == null) println 'File is null'
//            File dir = file
//            dir.eachDir {
//                Folder childFolder = new Folder(it.name)
//                this.children << childFolder
//                childFolder.file = it
//                childFolder.resolveChildren()
//            }
//        }
//
//        void resolveFeatureFiles() {
//            File dir = file
//            dir.eachFile(groovy.io.FileType.FILES) {
//                FeatureFile featureFile = new FeatureFile(it.name)
//                featureFiles << featureFile
//            }
//            children.each {
//                it.resolveFeatureFiles()
//            }
//        }
//    }
//
//    static class Project {
//        String name
//
//        List<Folder> folders = []
//
//        Project(String name) {
//            this.name = name
//        }
//    }
//
//    File baseDir = new File('../resources/features')
//    List<Project> projects = []
//
//}
//
//
//
//
///*
//
//THE TESTRAIL STRUCTURE
//
//section -> n * subsection -> test case.
//
//- a feature should be named after the (n-1)th section.
//- a feature file should contain all the test cases from the (n-1)th section as scenarios.
//
// */
//
//class zenjob.testautomation.sync.schemas.TestRailSchema {
//    static class TestCase {
//        int id
//        String name
//
//        TestCase(int id, String name) {
//            this.id = id
//            this.name = name
//        }
//    }
//
//    static class Section {
//        int id
//        String name
//        int indentationLevel
//        List<TestCase> testCases = []
//
//        Section(int id, String name) {
//            this.id = id
//            this.name = name
//        }
//    }
//
//    static class Suite {
//        int id
//        String name
//        List<Section> sections = []
//
//        Suite(int id, String name) {
//            this.id = id
//            this.name = name
//        }
//    }
//
//    static class Project {
//        int id
//        String name
//        List<Suite> suites = []
//
//        Project(int id, String name) {
//            this.id = id
//            this.name = name
//        }
//    }
//
//    List<Project> projects = []
//}
//
////interface Translator {
////    zenjob.testautomation.sync.schemas.TestRailSchema testRailSchema
////    FolderSchema folderSchema
////    CommonSchema translate()
////}
////
////class TestRailToCommonTra implements Translator {
////    zenjob.testautomation.sync.schemas.TestRailSchema testRailSchema
////    FolderSchema folderSchema
////    CommonSchema translate() {
////        CommonSchema commonSchema = new CommonSchema()
////    }
////}
//
//
///*
//
//FUNCTIONAL PROPOSAL
//
//[the CommonSchema from a previous run is stored (there can be more instances referring to different versions of
//
//From this example TestRail structure
//
//section1
//    section1.1
//        tc1.1.1
//        tc1.1.2
//    section1.2
//        section1.2.1
//            tc1.2.1.1
//            tc1.2.1.2
//        section1.2.2
//            tc1.2.2.1
//    section1.3
//        tc1.3.1
//        tc1.3.2
//section2
//    section 2.1
//        tc2.1.1
//    tc2.1
//    tc2.2
//    tc2.3
//section3
//    section 3.1
//        section3.1.1
//            tc3.1.1.1
//            tc3.1.1.2
//        section3.1.2
//        tc3.1.3
//        tc3.1.4
//        tc3.1.5
//    section 3.2
//        tc3.2.1
//        tc3.2.2
//        tc3.2.3
//section4
//    tc4.1
//    tc4.2
//    tc4.3
//    tc4.4
//
//
//The following folder structure should be generated
//
//
//folder1
//    feature1.1 with tc1.1.1, tc1.1.2
//    folder1.2
//        feature 1.2.1 with tc1.2.1.1, tc1.2.1.2
//        feature 1.2.2 with tc1.2.2.1
//    feature1.3 with tc1.3.1, tc1.3.2
//folder2
//    feature2.1 with tc2.1.1
//feature2 with tc2.1, tc2.2, tc2.3
//folder3
//    folder3.1
//        feature3.1.1 with tc3.1.1.1, tc3.1.1.2
//        feature3.1.2 with [empty]
//    feature3.1 with tc3.1.3, tc3.1.4, tc3.1.5
//    feature3.2 with tc3.2.1, tc3.2.2, tc3.2.3
//section4
//    tc4.1
//    tc4.2
//    tc4.3
//    tc4.4
//
//
//
//Test cases under a section are always grouped together into a section, even if there are other test cases under the same section. That way there can be folders and features with the same name at the same place (proposal).
//Exceptions can be added later on, this works flexibly on all levels. The only restriction is: there should be no test cases in level 0.
//
//run
//it will:
//- check for synchronization status - is the CommonSchema up to date? Which part is behind? What needs to be changed?
//
//
//
// */
//
//
//client.setUser(USER)
//client.setPassword(PASSWORD)
//
//
//class Indentation {
//    String indentationStep
//    int indentationLevel = 0
//
//    Indentation(String indentationStep) {
//        this.indentationStep = indentationStep
//    }
//
//
//    Indentation next() {
//        this.indentationLevel++
//        this
//    }
//
//    Indentation previous() {
//        this.indentationLevel--
//        this
//    }
//
//    String toString() {
//        String value = ''
//        (1..indentationLevel).each {
//           value = value << indentationStep
//        }
//        value
//    }
//}
//
//
//// read into TRS
//
//void readIntoTRS() {
//    List<Object> allProjects = client.sendGet('get_projects')
//    List<Object> sections = []
//    List<Object> suites = []
//
//    Indentation ind = new Indentation('...')
//    zenjob.testautomation.sync.schemas.TestRailSchema trs = new zenjob.testautomation.sync.schemas.TestRailSchema()
//    allProjects.each {
//
//        //add project to trs
//        zenjob.testautomation.sync.schemas.TestRailSchema.Project project = new zenjob.testautomation.sync.schemas.TestRailSchema.Project(it.id as Integer, it.name as String)
//        trs.projects << project
//
//
//        println it.name
//        int projectId = it.id
//
//        suites = client.sendGet("get_suites/${projectId}")
//
//        ind++
//        suites.each {
//
//            // add suite to to trs
//            zenjob.testautomation.sync.schemas.TestRailSchema.Suite suite = new zenjob.testautomation.sync.schemas.TestRailSchema.Suite(it.id as Integer, it.name as String)
//            project.suites << suite
//
//            println "${ind}${it.name}"
//            int suiteId = it.id
//            sections = client.sendGet("get_sections/${projectId}&suite_id=${suiteId}")
//
//            ind++
//            sections.each {
//
//                // add section to trs
//                zenjob.testautomation.sync.schemas.TestRailSchema.Section section = new zenjob.testautomation.sync.schemas.TestRailSchema.Section(it.id as Integer, it.name as String)
//                section.setDepth(it.depth)
//
//
//                if (it.depth == 0) {
//                    suite.sections << section
//                }
//
////                else if (it.parent_id == section.parent.id && it.depth == section.) {
////
////                }
//
//                int sectionId = it.id
//                (it.depth).times { ind++ }
//
//                println "${ind}${it.name}"
//
//                List<Object> testCases = client.sendGet("get_cases/${projectId}&suite_id=${suiteId}&section_id=${sectionId}")
//
//                ind++
//                testCases.each {
//                    zenjob.testautomation.sync.schemas.TestRailSchema.TestCase testCase = new zenjob.testautomation.sync.schemas.TestRailSchema.TestCase(it.id as Integer, it.title as String) // with a TestRail test case the name is 'title'
//
//                    section.testCases << testCase
//
//                    println "${ind}${it.title}"
//                }
//                ind--
//
//                (it.depth).times { ind-- }
//            }
//            ind--
//        }
//        ind--
//    }
//
//    println trs.projects.get(0).suites.get(0).sections.get(0).testCases.get(0).name
//    println trs.projects.get(0).suites.get(0).sections.get(0).testCases.get(0).id
//    println trs.projects.get(0).suites.get(0).sections.get(0).testCases.get(0)
//}
////readIntoTRS()
//
//
//
//// translate TRS into FS
//
//class TestRailToFolderTranslator {
//    zenjob.testautomation.sync.schemas.TestRailSchema testRailSchema
//    FileSystemSchema fileSystemSchema
//
//    FileSystemSchema translate() {
//        testRailSchema.projects.each { zenjob.testautomation.sync.schemas.TestRailSchema.Project testRailProject ->
//            FileSystemSchema.Project fileSystemSchemaProject = new FileSystemSchema.Project(testRailProject.name)
//            fileSystemSchema.projects << fileSystemSchemaProject
//            testRailProject.suites.each { zenjob.testautomation.sync.schemas.TestRailSchema.Suite testRailSuite ->
//                fileSystemSchemaProject.folders << new FileSystemSchema.Folder(testRailSuite.name)
//
//                testRailSuite.sections.each { zenjob.testautomation.sync.schemas.TestRailSchema.Section testRailSection ->
//
//                }
//            }
//        }
//
//        fileSystemSchema
//    }
//
//    TestRailToFolderTranslator(zenjob.testautomation.sync.schemas.TestRailSchema testRailSchema, FileSystemSchema fileSystemSchema) {
//        this.testRailSchema = testRailSchema
//        this.fileSystemSchema = fileSystemSchema
//    }
//}
//
//
///*
//    Read into FSS
// */
//
//def readIntoFSS() {
//    FileSystemSchema fss = new FileSystemSchema()
//
//    // read projects
//
//    fss.baseDir.eachDir { File projectDir ->
//        FileSystemSchema.Project project = new FileSystemSchema.Project(projectDir.name)
//        fss.projects << project
//
//        projectDir.eachDir { File folderDir ->
//            FileSystemSchema.Folder folder = new FileSystemSchema.Folder(folderDir.name)
//            folder.setFile(folderDir.canonicalFile)
//
//            project.folders << folder
//
//            folder.resolveChildren()
//            folder.resolveFeatureFiles()
//
//        }
//    }
//}
//// readIntoFSS()
