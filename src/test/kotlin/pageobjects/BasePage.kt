package pageobjects

import appium.driver.builder.AutomationDriver
import io.appium.java_client.AppiumDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait

private const val TIMEOUT_IN_SECONDS = 30L

open class BasePage : KoinComponent {
    private val automationDriver by inject<AutomationDriver>()
    protected val driver: AppiumDriver<WebElement>
        get() = automationDriver.driver

    fun getText(webElement: WebElement) = webElement.text!!

    fun clickOnElement(element: WebElement) {
        waitForElementToBeVisible(element)
        element.click()
    }

    fun WebElement.type(text: String) {
        waitForElementToBeVisible(this)
        if (this.isDisplayed && this.isEnabled) {
            this.click()
            this.clear()
            this.sendKeys(text)
        } else {
            throw Exception("Element not interactable at locator: $this")
        }
    }
    fun waitForElementToBeClickable(webElement: WebElement) {
        val wait = WebDriverWait(driver, TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.elementToBeClickable(webElement))
    }
    private fun waitForElementToBeVisible(webElement: WebElement) {
        val wait = WebDriverWait(driver, TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.visibilityOf(webElement))
    }
}
