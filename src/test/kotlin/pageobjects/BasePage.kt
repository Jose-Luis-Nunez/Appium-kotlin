package pageobjects

import appium.driver.builder.BaseAutomationDriver
import appium.driver.config.DriverConfigurator.getDriver
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration;

private const val TIMEOUT_IN_SECONDS = 10L
private const val TIMEOUT_IN_MILLISECONDS = 300L

open class BasePage {
    private val automationDriver: BaseAutomationDriver = getDriver()
    protected val driver: AppiumDriver get() = automationDriver.driver

    fun getText(webElement: WebElement) = webElement.text!!

    fun clickOnElement(element: WebElement) {
        waitForElementToBeVisible(element)
        element.click()
    }

    fun WebElement.type(text: String) {
        waitForElementToBeVisible(this)
        this.apply {
            click()
            clear()
            sendKeys(text)
        }
    }

    private fun waitForElementToBeVisible(webElement: WebElement) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS))
        wait.until { webElement.isDisplayed }
    }

    private fun fluentWaitForElementToBeVisible(webElement: WebElement) {
        val wait = FluentWait(driver)
            .withTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
            .pollingEvery(Duration.ofMillis(TIMEOUT_IN_MILLISECONDS))
            .ignoring(ElementNotInteractableException::class.java)

        wait.until {
            webElement.isDisplayed
        }
    }
}
