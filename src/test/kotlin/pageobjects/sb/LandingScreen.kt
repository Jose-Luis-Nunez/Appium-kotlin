package tests.pageobjects.sb

import org.openqa.selenium.WebElement
import pageobjects.BaseScreen
import tests.utils.AppiumDriverExtensions.Companion.findElementByResourceId
import tests.utils.AppiumDriverExtensions.Companion.findElementByText

class LandingScreen : BaseScreen() {
    private val acceptAllButton: WebElement get() = driver.findElementByText("Accept All")

    private val signInBtn: WebElement get() = driver.findElementByResourceId("id_button_signin")
    fun clickOnAcceptAllBtn() {
        clickOnElement(acceptAllButton)
    }

    fun clickOnLogin() {
        clickOnElement(signInBtn)
    }
}
