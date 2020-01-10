package framework.src.Steps.CompanyApp

import framework.src.Pages.CompanyApp.BookedShiftsPage
import framework.src.Pages.CompanyApp.LoginPage
import framework.src.Pages.CompanyApp.NewOrderPage
import framework.src.Pages.CompanyApp.OrdersConfirmationPage
import framework.src.Pages.CompanyApp.OrdersPage
import framework.src.Pages.CompanyApp.RejectOrderPage
import framework.src.Pages.CompanyApp.SingleOrderPage

this.metaClass.mixin(cucumber.api.groovy.EN)
this.metaClass.mixin(cucumber.api.groovy.Hooks)

And(~/^I am logged in as global manager$/) { ->
    LoginPage companyAppLoginPage = to(LoginPage)
    companyAppLoginPage.setUserCredentials("mariia.rusova@zenjob.com","P@ssw0rd")
    companyAppLoginPage.makeClickInTheLogginButton()
    assert at(BookedShiftsPage)

}

Then(~/^I am on orders page$/) { ->
    println('Starting to validate the name of the popup menu to select a Location')
    NewOrderPage newOrderPage = at(NewOrderPage)
    println(newOrderPage.getLabelTextFromThePopUpSelectorOfLocation())
}

And(~/^I choose location$/) { ->
    println('Starting to select a location in the New Order pop up')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.selectATheLocationFromThePopUp()
}

And(~/^I fill all fields with valid values$/) { ->
    println('Starting to fill all the fields of the new order')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.fillAllTheFieldsWithValidValues()
}

And(~/^I fill all data for shift details$/) { ->
    println('Starting to fill the data of the shift panel')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.fillAllTheFieldsWithValidValuesInTheShitDetails()
}

And(~/^I press in the button to add a new shift$/) { ->
    println('Starting to make click in the button add new shift')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.makeClickInAddNewShift()
}

And(~/^I fill all fields of the second order with valid values$/) { ->
    println('Starting to fill all the values in the second shift')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.fillValuesInTheSecondShift()
}


And(~/^In the order creation I click submit button$/) { ->
    println('Starting to make click in the submit button of the new order page')
    NewOrderPage newOrderPage = at(NewOrderPage)
    newOrderPage.makeClickInSubmitForm()
}

Then(~/^new order is created$/) { ->
    println('Starting to validate the value of the new order, if this exist.')
    OrdersConfirmationPage ordersConfirmationPage = at(OrdersConfirmationPage)
    println('We get the value of order: '+ordersConfirmationPage.getOrderNumber())
    assert (ordersConfirmationPage.getOrderNumber() >= 1)

}
When(~/^I am rejecting order$/) { ->
    OrdersPage ordersPage = at(OrdersPage)
    SingleOrderPage singleOrderPage = at(SingleOrderPage)

    ordersPage.makeClickInTheButtonShowOrderInTheFirstOrderWithStatusPending()
    singleOrderPage.makeClickInTheButtonReject()
    singleOrderPage.writeATextInTheREasonToReject()
}

Then(~/^order become rejected$/) { ->
    println('Starting to reject the order')
    SingleOrderPage singleOrderPage = at(SingleOrderPage)

    println('Starting to validate the message in the confirmation popup')
    assert singleOrderPage.makeClickInTheButtonOkToConfirmTheReject().contains('Die Bestellung wurde abgelehnt')
}

And(~/^I am on rejected order overview page$/) { ->
    println('Access to the page rejected')
    OrdersPage ordersPage = at(OrdersPage)
    ordersPage.makeClickInTheButtonShowOrderInTheFirstOrderWithStatusReject()

    RejectOrderPage rejectOrderPage = at(RejectOrderPage)
    assert rejectOrderPage.getTheContentTextOfTheLabelRejectOrder().contains('Abgelehnt')
}

When(~/^I am editing order$/) { ->
    println('Starting to edit the rejected order')
    RejectOrderPage rejectOrderPage = at(RejectOrderPage)
    rejectOrderPage.makeClickInTheButtonEdit()
}

And(~/^submiting order$/) { ->
    println('Starting to make click in the submit order')
    RejectOrderPage rejectOrderPage = at(RejectOrderPage)

    rejectOrderPage.makeClickInTheButtonEdit()
    rejectOrderPage.makeClickInTheButtonSubmitOrder()
    rejectOrderPage.makeClickInThePopUpConfirmationButtonSubmitOrder()
}

Then(~/^order get resubmited$/) { ->
    println('Starting to make click in the pop up confirmation')
    RejectOrderPage rejectOrderPage = at(RejectOrderPage)

    println(rejectOrderPage.getTheTextOfThePupUpConfirmationOfSubmitOrder())
    assert rejectOrderPage.getTheTextOfThePupUpConfirmationOfSubmitOrder().contains('Personalbuchung ist erfolgreich')
    rejectOrderPage.makeClickInTheConfirmationPopUpOfSubmitOrder()
}

And(~/^submit event is tracked in activities$/) { ->
    println('Starting to validate the text inside of the last activity')
    RejectOrderPage rejectOrderPage = at(RejectOrderPage)
    assert rejectOrderPage.getLastTextOfTheLastActivitie().contains('Bearbeitet und')
}

When(~/^I am approving order$/) { ->
    println('Starting approve a order')
    SingleOrderPage singleOrderPage = at(SingleOrderPage)

    singleOrderPage.makeClickInTheButtonShowOrderInTheFirstOrderWithStatusPending()
    singleOrderPage.makeClickInTheButtonApprove()
}

Then(~/^order become approved$/) { ->
    println('Starting to validate if the order was approved')
    SingleOrderPage singleOrderPage = at(SingleOrderPage)
    assert singleOrderPage.getTextDisplayedInThePopupOfConfirmation().contains('Bestellung')
    singleOrderPage.makeClickInTheButtonOkOftheConfirmationPopUp()
}

And(~/^approval is tracked in activities$/) { ->
    println('Starting to validate if the order is tracked in the activities')
    SingleOrderPage singleOrderPage = at(SingleOrderPage)

    OrdersConfirmationPage ordersConfirmationPage = at(OrdersConfirmationPage)
    int orderNumber = ordersConfirmationPage.getOrderNumber()

    thisIsTheVariableJuan = orderNumber

    assert singleOrderPage.getTextDisplayedInTheLastActivity().contains('Genehmigt')
}

And(~/^I go to new order page$/) { ->
    to(NewOrderPage)
}

Given(~/^I am on shift details form$/) { ->
    page.checkDetailsFormDisplayed()
}
When(~/^I fill all fields for job requirement$/) { ->
    page.fillJobRequeremntsTemplateName()
    page.fillJobDescriptionField()
    page.fillJobSpecialRequirementsField()
}
And(~/^I click save new template button$/) { ->
    page.clickSaveRequirementsTemplateButton()
}
Then(~/^job requirement template was saved$/) { ->
    page.checkTemplateWasSaved()
}

When(~/^I fill all fields for contact person$/) { ->
    page.fillContactTemplateName()
    page.fillContactNumber()
    page.fillContactEmail()
    page.clickSaveContactTemplateButton()
}
Then(~/^contact person template was saved$/) { ->
    page.checkTemplateWasSaved()
}