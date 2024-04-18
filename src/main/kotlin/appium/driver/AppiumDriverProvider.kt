package appium.driver

import appium.AppiumServerManager
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.net.URL
import java.util.concurrent.TimeUnit

abstract class AppiumDriverProvider: DriverSpecification {

    override var driver: AppiumDriver<WebElement> by ThreadLocalDelegate.lateinit()

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
}

class AndroidAppiumDriver: AppiumDriverProvider() {
    override fun initDriver() {
        initializeDriver {
            AndroidDriver(URL(AppiumServerManager.serverAddress), CapabilitiesConfigurator.android())
        }
    }

    override fun findElementById(id: String): WebElement {
        return driver.findElementById(id)
    }

    override fun findElementsById(id: String): List<WebElement> {
        return driver.findElementsById(id)
    }
}

class IosAppiumDriver: AppiumDriverProvider() {
    override fun initDriver() {
        initializeDriver {
            IOSDriver(URL(AppiumServerManager.serverAddress), CapabilitiesConfigurator.iOS())
        }
    }

    override fun findElementById(id: String): WebElement {
        return driver.findElementByAccessibilityId(id)
    }

    override fun findElementsById(id: String): List<WebElement> {
        return driver.findElementsByAccessibilityId(id)
    }
}
