package framework.src.Pages.CompanyApp


import org.openqa.selenium.By
import framework.src.Pages.BasePage

class MenuBarPage extends BasePage {
    static at = { title == 'Zenjob - Company App' }

    //Locators
    static content = {
        buttonMenuBurguer { $(By.xpath("(//button[contains(@class,'Hamburger')])[1]"))}
        buttonMenuLogout { $(By.xpath("//*[contains(@class,'LogoutButton')]"))}
        optionMenuOrders { $(By.xpath("//span[contains(text(),'Personalbuchungen')]"))}
        optionMenuShifts { $(By.xpath("//span[contains(text(),'Gebucht')]"))}
    }

    //methods section
    /**
     * this method make click in the burger menue icon!.
     *
     */
    void makeClickInTheBurgerMenu(){
        buttonMenuBurguer.click()
    }

    /**
     * This method make click in the element menu option orders.
     *
     */
    void makeClickInTheOtionMenuOrders() {
        optionMenuOrders.click()
    }

    /**
     * This method make click in the button logout.
     *
     */
    void makeClickInTheButtonLogOut() {
        buttonMenuLogout.click()
    }

    /**
     * This method make click in the element menu option shifts.
     *
     */
    void makeClickInTheOtionMenuShifts() {
        optionMenuShifts.click()
    }
}