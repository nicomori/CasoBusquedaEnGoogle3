package zenjob.testautomation.sync.readers

import zenjob.testautomation.sync.obj.Project
import zenjob.testautomation.sync.schemas.TestRailSchema
import zenjob.testautomation.sync.testrailapi.TestRailApiWrapper

class TestRailReader {
    TestRailApiWrapper api
    TestRailSchema schema

    TestRailReader() {
        api = new TestRailApiWrapper()
        schema = new TestRailSchema()
    }

    /*
        filters: [
            projectIds: [1, 2, 3],
        ]
     */

    TestRailSchema read(List<Project> filters) {
        filters.each { filter ->
            def project_ = api.getProject(filter.project)
            TestRailSchema.Project project = new TestRailSchema.Project(project_.id, project_.name, filter.folder)
            schema.projects.add(project)

            api.getSuitesForProject(project.id).each {
                if (((filter.suites !=null) && filter.suites.contains((int)it.id)) || filter.suites==null) {
                    TestRailSchema.Suite suite = new TestRailSchema.Suite(it.id, it.name)
                    project.suites.add(suite)

                    // assuming there is only one parent section
                    // assuming that the sections come in sorted based on depth

                    List<TestRailSchema.Section> allSections = []
                    api.getSectionsForProjectAndSuite(project.id, suite.id).each { Object sectionToBeInserted ->
                        // depth 0 are usually zenjob.testautomation.modules
                        if (sectionToBeInserted.depth == 0) {
                            TestRailSchema.Section s = new TestRailSchema.Section(sectionToBeInserted.id, sectionToBeInserted.name, sectionToBeInserted.depth)
                            s.setDescription(sectionToBeInserted.description)
                            suite.sections.add(s)

                            allSections += suite.sections
                        }

                        // depth 1 atm are features
                        else {
                            TestRailSchema.Section parentSection = allSections.find { TestRailSchema.Section existingSection ->
                                sectionToBeInserted.parent_id == existingSection.id
                            }

                            // parentSection is null if it has not been created yet
                            if (parentSection != null) {
                                TestRailSchema.Section section = new TestRailSchema.Section(sectionToBeInserted.id, sectionToBeInserted.name, sectionToBeInserted.depth)
                                section.description = sectionToBeInserted.description

                                // get test cases
                                // pick only ones that were reviewed
                                section.testCases = api.getTestCasesForSection(project.id, suite.id, section.id)
                                        .grep { it.custom_reviewed }
                                        .collect { new TestRailSchema.TestCase(it.id, it.title, it.custom_gherkin) }

                                parentSection.sections.add(section)
                                allSections += parentSection.sections
                            } else {
                                println "ERROR   ${sectionToBeInserted.id} ${sectionToBeInserted.name} ${sectionToBeInserted.depth} ${sectionToBeInserted.parent_id}"
                            }
                        }
                    }
                }
            }
        }
        schema
    }

    TestRailReader(TestRailApiWrapper testRailApiWrapper) {
        this.api = testRailApiWrapper
    }
}
