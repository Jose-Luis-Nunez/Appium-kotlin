package pageobjects

import appium.driver.builder.BaseAutomationDriver
import appium.driver.config.DriverConfigurator.getDriver
import io.appium.java_client.AppiumDriver
import io.appium.java_client.remote.SupportsContextSwitching
import org.openqa.selenium.By
import org.openqa.selenium.JavascriptExecutor
import org.openqa.selenium.WebElement

import tests.utils.WebDriverWaits

open class BaseScreen {
    private val automationDriver: BaseAutomationDriver = getDriver()
    protected val driver: AppiumDriver get() = automationDriver.driver
    private val waits = WebDriverWaits()

    fun getText(webElement: WebElement) = webElement.text!!

    fun clickOnElement(element: WebElement) {
        waits.waitForElementToBeVisible(element)
        element.click()
    }

    fun WebElement.type(text: String) {
        waits.waitForElementToBeVisible(this)
        this.apply {
            click()
            clear()
            sendKeys(text)
        }
    }

    fun findAndSwitchToContext(appContext: AppContext) {
        val driverContext = driver as SupportsContextSwitching
        waits.waitForCondition {
            val contextHandles = driverContext.contextHandles
            val desiredContext = contextHandles.find { it.contains(appContext.context) }
            when (desiredContext) {
                null -> false
                else -> {
                    driverContext.context(desiredContext)
                    true
                }
            }
        }
    }

    fun clickWithRetry(findElement: () -> WebElement, maxRetries: Int = 3) {
        var element: WebElement? = null

        repeat(maxRetries) { attempt ->
            try {
                element = findElement()
                waits.waitForElementToBeClickable(element!!)
                element!!.click()
                return

            } catch (e: Exception) {
                println("click failed: $element ${e.message}. total retry ${attempt + 1} of $maxRetries.")
            }
        }
        throw Exception("max retry failed, element not clickable.")
    }
    fun clickOnShadowElement(shadowHostCssSelector: String, shadowElementCssSelector: String) {
        try {
            val shadowHost = driver.findElement(By.cssSelector(shadowHostCssSelector))
            val shadowRoot =
                (driver as JavascriptExecutor).executeScript("return arguments[0].shadowRoot", shadowHost) as WebElement
            val shadowElement = shadowRoot.findElement(By.cssSelector(shadowElementCssSelector))

            shadowElement.click()
        } catch (e: Exception) {
            e.printStackTrace()
            throw RuntimeException("failed to click on element in shadow root", e)
        }
    }
}

enum class AppContext(val context: String) {
    NATIVE("NATIVE"),
    WEBVIEW("WEBVIEW")
}
