package zenjob.testautomation.sync.executors

import zenjob.testautomation.sync.schemas.FileSystemSchema

class FileSystemSchemaExecutor implements ExternalPlatformSchemaExecutor {
    FileSystemSchema fss
    List<FileSystemSchema> fssList

    FileSystemSchemaExecutor(FileSystemSchema fss) {
        this.fss = fss
    }

    FileSystemSchemaExecutor(List<FileSystemSchema> fssList) {
        this.fssList = fssList
    }

    boolean execute() {
        if (!fssList.empty) {
            fssList.each {FileSystemSchema fss ->
                fss.folders.each {
                    makeDirectory(fss, directoryNameParser(it.name))
                    makeDirectoriesRecursive(fss, it.children)
                }
            }
        } else {
            fss.folders.each {
                makeDirectory(fss, directoryNameParser(it.name))
                makeDirectoriesRecursive(fss, it.children)
            }
        }
        true
    }

    private void makeDirectory(FileSystemSchema fss, String name) {
        println("DIR: " + fss.baseDir)
        File newDirectory = new File("${fss.baseDir}/${name}")


        if (!newDirectory.mkdir()) println "ERROR, failed to create ${newDirectory.name}"
        println "making directory ${newDirectory.canonicalPath}"
    }

    private void makeDirectoriesRecursive(FileSystemSchema fss, def folders) {
        folders.each { FileSystemSchema.Folder folder ->

            // leaf node only
            if (folder.featureFiles) {
                folder.featureFiles.each {
                    File featureFile = new File("${fss.baseDir}/${getFolderPath(folder.parent)}/${fileNameParser(it.name)}.feature")
                    featureFile.createNewFile()
                    // clear it for now
                    featureFile.text = ''

                    featureFile.append("Feature: ${it.name}\n")
                    featureFile.append("\n")

                    if (it.background) {
                        // no tid for a background, otherwise there is an error on compilation
                        featureFile.append("Background: \n")
                        featureFile.append("${it.background.content}\n")
                        featureFile.append("\n")
                    }

                    it.scenarios.each {
                        if (it.content) {
                            featureFile.append("@tid${it.id}\n")
                            featureFile.append("Scenario: ${it.name}\n")
                            featureFile.append("${it.content}\n")
                            featureFile.append("\n")
                        }
                    }

                }
            } else if (folder.children.size() > 0) {
                makeDirectory(fss, getFolderPath(folder))
                makeDirectoriesRecursive(fss, folder.children)
            }
        }
    }

    private String getFolderPath(FileSystemSchema.Folder folder) {
        if (folder.parent == null) {
            return "/${directoryNameParser(folder.name)}"
        } else {
            return "${getFolderPath(folder.parent)}/${directoryNameParser(folder.name)}"
        }
    }

    static String directoryNameParser(String name) {
        name.replaceAll(/[^\w]/, '').toLowerCase()
    }

    static String fileNameParser(String name) {
        name.uncapitalize().replaceAll(/( \w)/) { group -> group[1].replace(' ', '').toUpperCase() }
    }
}
