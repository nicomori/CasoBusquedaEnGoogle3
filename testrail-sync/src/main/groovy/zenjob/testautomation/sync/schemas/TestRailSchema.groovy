package zenjob.testautomation.sync.schemas

class TestRailSchema implements ExternalPlatformSchema {
    static class TestCase {
        long id
        String name
        String content
        boolean isBackground = false

        TestCase(long id, String name, String content) {
            this.id = id
            this.name = name
            this.content = content
        }
    }

    static class Section {
        long id
        String name
        long depth
        String description
        List<TestCase> testCases = []
        List<Section> sections = []

        Section(long id, String name, long depth) {
            this.id = id
            this.name = name
            this.depth = depth
        }

    }

    static class Suite {
        long id
        String name
        List<Section> sections = []

        Suite(long id, String name) {
            this.id = id
            this.name = name
        }
    }

    static class Project {
        long id
        String name
        List<Suite> suites = []
        String folder

        Project(long id, String name, String folder) {
            this.id = id
            this.name = name
            this.folder = folder
        }
    }

    List<Project> projects = []
}

