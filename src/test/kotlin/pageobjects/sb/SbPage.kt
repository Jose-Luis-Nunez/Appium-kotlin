package pageobjects.sb

import org.openqa.selenium.By
import pageobjects.BasePage
import org.openqa.selenium.WebElement

class SbPage : BasePage() {

    private val element: WebElement get() = driver.findElement(By.id(""))

}
