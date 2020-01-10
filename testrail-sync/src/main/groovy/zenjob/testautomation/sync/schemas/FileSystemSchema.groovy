package zenjob.testautomation.sync.schemas


class FileSystemSchema implements ExternalPlatformSchema {
    static class FeatureFile {
        String name
        Scenario background = null // no background by default
        List<Scenario> scenarios = []

        FeatureFile(String name) {
            this.name = name
        }
    }

    static class Scenario {
        String name
        String id
        String content

        Scenario(id, name, content) {
            this.id = id
            this.name = name
            this.content = content
        }
    }

    static class Folder {
        String name

        File file
        Folder parent
        List<Folder> children = []

        List<FeatureFile> featureFiles = []

        Folder(String name) {
            this.name = name
        }

        void resolveChildren() {
            if (file == null) println 'File is null'
            File dir = file
            dir.eachDir {
                Folder childFolder = new Folder(it.name)
                this.children << childFolder
                childFolder.file = it
                childFolder.resolveChildren()
            }
        }

        void resolveFeatureFiles() {
            File dir = file
            dir.eachFile(groovy.io.FileType.FILES) {
                FeatureFile featureFile = new FeatureFile(it.name)
                featureFiles << featureFile
            }
            children.each {
                it.resolveFeatureFiles()
            }
        }
    }
    File baseDir
    List<Folder> folders = []
}
