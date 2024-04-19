package appium.driver

import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait
import java.net.URL
import java.util.concurrent.TimeUnit

private const val TIMEOUT_IN_SECONDS = 30L

abstract class BaseAutomationDriver : AutomationDriver {

    var driver: AppiumDriver<WebElement> by ThreadLocalDelegate.lateinit()

    protected fun setImplicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
    }

    protected fun initializeDriver(supplier: () -> AppiumDriver<WebElement>) {
        try {
            driver = supplier()
            setImplicitWait()
        } catch (e: Exception) {
            throw RuntimeException("Driver Initialization Failed", e)
        }
    }

    override fun findElementByXpath(xPath: String): WebElement {
        return driver.findElement(By.xpath(xPath))
    }

    override fun findElementsByXpath(xPath: String): List<WebElement> {
        return driver.findElements(By.xpath(xPath))
    }

    override fun findElementByText(text: String): WebElement {
        return driver.findElement(By.linkText(text))
    }

    override fun findElementByPartialText(text: String): WebElement {
        return driver.findElement(By.partialLinkText(text))
    }

    override fun waitForElementToBeClickable(webElement: WebElement) {
        val wait = WebDriverWait(driver, TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.elementToBeClickable(webElement))
    }

    override fun waitForElementToBeVisible(webElement: WebElement) {
        val wait = WebDriverWait(driver, TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.visibilityOf(webElement))
    }
    override fun closeDriver() {
        driver.quit()
    }
}

class AndroidAutomationDriver : BaseAutomationDriver() {
    override fun startDriver() {
        initializeDriver {
            AndroidDriver(URL(ServerManager.serverAddress), CapabilitiesConfigurator.android())
        }
    }

    override fun findElementById(id: String): WebElement {
        return driver.findElementById(id)
    }

    override fun findElementsById(id: String): List<WebElement> {
        return driver.findElementsById(id)
    }
}

class IosAutomationDriver : BaseAutomationDriver() {
    override fun startDriver() {
        initializeDriver {
            IOSDriver(URL(ServerManager.serverAddress), CapabilitiesConfigurator.iOS())
        }
    }

    override fun findElementById(id: String): WebElement {
        return driver.findElementByAccessibilityId(id)
    }

    override fun findElementsById(id: String): List<WebElement> {
        return driver.findElementsByAccessibilityId(id)
    }
}
