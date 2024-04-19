package pageobjects

import appium.driver.AutomationDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.openqa.selenium.WebElement


private const val TIMEOUT_IN_SECONDS = 30L

open class BasePage : KoinComponent {
    val driver by inject<AutomationDriver>()

    fun getText(webElement: WebElement) = webElement.text!!

    fun clickOnElement(element: WebElement) {
        driver.waitForElementToBeVisible(element)
        element.click()
    }

    fun WebElement.type(text: String) {
        driver.waitForElementToBeVisible(this)
        if (this.isDisplayed && this.isEnabled) {
            this.click()
            this.clear()
            this.sendKeys(text)
        } else {
            throw Exception("Element is not interactable at locator: $this")
        }
    }

//    private fun waitForElementToBeVisible(webElement: WebElement) {
//        val wait = WebDriverWait(driver.driver, TIMEOUT_IN_SECONDS)
//        wait.until(ExpectedConditions.visibilityOf(webElement))
//    }

//    private fun waitForElementToBeClickable(webElement: WebElement) {
//        val wait = WebDriverWait(driver.driver, TIMEOUT_IN_SECONDS)
//        wait.until(ExpectedConditions.elementToBeClickable(webElement))
//    }
}
