package framework.src.Pages.ZVS.employee


import org.openqa.selenium.By
import framework.src.Pages.BasePage
import framework.src.Modules.ErrorsModule
import framework.src.Util.utils.FileUtils
/*
import zenjob.testautomation.modules.ErrorsModule
import zenjob.testautomation.pages.BasePage
import zenjob.testautomation.utils.FileUtils
*/

import java.util.regex.Pattern

class EmployeeDocumentsAndMediaPage extends BasePage {
    static at = {
        Pattern pattern = ~/Update Employee Media Files DETAILS/
        header.text() ==~ pattern
    }

    static filename = '/impl/src/main/resources/employeeAvatar.png'

    static content = {
        form { $(By.xpath('//form')) }
        header { $(By.xpath('//*[@class="page-header"]')) }
        editEmployeeAvatarButton { $(By.xpath('//label[contains (text(), "Edit Employee Avatar")]')) }
        errors { module(ErrorsModule)}
        inputFileElement { $(By.xpath('//input[@name="avatar"]')) }
        employeeAvatar { $(By.xpath('//div[@id="employeeAvatar"]//img[@id = "employeeAvatarImageUpdate" and @src !=""]')) }
        detailsButton { $(By.xpath('//a[@href and contains (., "Details")]'))}
    }

     void clickEditEmployeeAvatarButton() {
        editEmployeeAvatarButton.click()
    }

     static File getFile(){
        def defaultPathBase = new File( "." ).getCanonicalPath()
        File avatarFile = new File(defaultPathBase, filename)
        return avatarFile
     }

     void inputAvatar() {
         FileUtils.copyFileToDocker(
                 FileUtils.getFileFromResource('employeeAvatar.png'),
                 '/employeeAvatar.png')
         inputFileElement.value("/employeeAvatar.png")
         sleep(300)
     }

     void checkAvatarExists() {
        assert employeeAvatar.size() == 1
     }

     void goToEmployeeDetailsPage() {
        detailsButton.click()

     }
}
