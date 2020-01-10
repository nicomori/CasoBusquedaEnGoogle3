package zenjob.testautomation.utils

class FileUtils {
    static File getFileFromResource(String resourceName) {
        // forward slash because file is in the root of the resource folder
         new File('employeeAvatar.png')
    }

    static void copyFileToDocker(File localFile, String targetFileName) {
        def commandFindContainer = "docker ps".execute() | "grep selenium".execute()
        commandFindContainer.waitFor()

        def result = commandFindContainer.in.text
        def containerId  = (result =~ /([^ ]*) *[\w]*.*/)[0][1]
        def commandCopyFileToDocker = "docker cp ${localFile.canonicalPath} ${containerId}:/${targetFileName}".execute()
        commandCopyFileToDocker.waitFor()
    }
}
