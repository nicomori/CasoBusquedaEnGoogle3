package framework.src.Util

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.interactions.Actions

class LocatorUtil extends BaseUtil{

    public void mouseOverAction(String locator){
        Actions action = new Actions(getDriver())
        WebElement we = getDriver().findElement(By.xpath(optionDateCurrentDateLocator))
        action.moveToElement(we).moveToElement(getDriver().findElement(By.xpath(optionDateCurrentDateLocator))).click().build().perform();

    }
}
