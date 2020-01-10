package framework.src.Steps.ZVS

import framework.src.Pages.ZVS.employee.EmployeeDetailsPage
import framework.src.Pages.ZVS.employee.EmployeeDocumentsAndMediaPage

this.metaClass.mixin(cucumber.api.groovy.Hooks)
this.metaClass.mixin(cucumber.api.groovy.EN)


And(~/^I am on the 'Update Employee Media Files' page$/) { ->
    assert at(EmployeeDocumentsAndMediaPage)
}

And(~/^I upload an image in the correct format$/) { ->
    EmployeeDocumentsAndMediaPage documentsAndMediaPage = at(EmployeeDocumentsAndMediaPage)
    documentsAndMediaPage.inputAvatar()
}
Then(~/^I should see selected image on 'Update Employee Media Files' page$/) { ->
    at (EmployeeDocumentsAndMediaPage)
    page.checkAvatarExists()
}
Then(~/^I should see selected image in the 'Documents & Media' panel on the 'Employee Details' page$/) { ->
    // Write code here that turns the phrase above into concrete actions
    page.goToEmployeeDetailsPage()
    assert at(EmployeeDetailsPage)
    page.checkAvatar()
}
When(~/^I click on 'Edit' button in 'Documents & Media' section$/) { ->
    page.editDocumentsAndMedia()
}