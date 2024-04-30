package tests.utils

import appium.driver.builder.BaseAutomationDriver
import appium.driver.config.DriverConfigurator.getDriver
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.By
import org.openqa.selenium.ElementNotInteractableException
import org.openqa.selenium.StaleElementReferenceException
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.FluentWait
import org.openqa.selenium.support.ui.WebDriverWait
import java.time.Duration

private const val TIMEOUT_IN_SECONDS = 15L
private const val TIMEOUT_IN_MILLISECONDS = 300L

class WebDriverWaits {
    private val automationDriver: BaseAutomationDriver = getDriver()
    private val driver: AppiumDriver get() = automationDriver.driver

    fun waitForElementToBeVisible(webElement: WebElement) {
        val wait = WebDriverWait(driver, Duration.ofSeconds(TIMEOUT_IN_SECONDS))
        wait.until(ExpectedConditions.visibilityOf(webElement))
    }

    fun waitForCondition(
        condition: () -> Boolean
    ) {
        val wait = FluentWait(driver)
            .withTimeout(Duration.ofSeconds(TIMEOUT_IN_SECONDS))
            .pollingEvery(Duration.ofMillis(TIMEOUT_IN_MILLISECONDS))
            .ignoring(NoSuchElementException::class.java)
            .ignoring(StaleElementReferenceException::class.java)
            .ignoring(ElementNotInteractableException::class.java)

        wait.until { condition() }
    }

    fun waitForElementToBeVisible(by: By) {
        waitForCondition {
            val element = driver.findElement(by)
            element.isDisplayed
        }
    }

    fun waitForElementToBeClickable(by: By) {
        waitForCondition {
            val element = driver.findElement(by)
            element.isDisplayed && element.isEnabled
        }
    }

    fun waitForElementToBeClickable(element: WebElement) {
        waitForCondition {
            element.isDisplayed && element.isEnabled
        }
    }
}
