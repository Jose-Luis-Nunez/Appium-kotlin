package appium.driver

import framework.recording.SeleniumGridManager
import io.appium.java_client.MobileElement
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import java.net.URL
import java.util.concurrent.TimeUnit

interface AutomateDriver {
    var driver:AppiumDriver<MobileElement>
    fun initDriver()
    fun findElementById(id: String): WebElement
    fun findElementsById(id: String): List<WebElement>
    fun findElementAccessibilityById(id: String): WebElement
    fun findElementByXpath(xPath: String): WebElement
    fun findElementsByXpath(xPath: String): List<WebElement>
    fun findElementByText(text: String): WebElement
    fun findElementByPartialText(text: String): WebElement
}

abstract class BaseAutomateDriver: AutomateDriver {
    override var driver: AppiumDriver<MobileElement> by ThreadLocalDelegate.lateinit()

    protected fun setImplicitWait() {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
    }

    override fun findElementAccessibilityById(id: String): WebElement {
        return driver.findElementByAccessibilityId(id)
    }

//    override fun findElementsAccessibilityById(id: String): List<WebElement> {
//        return driver.findElementByAccessibilityId(id)
//    }

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

class AndroidAutomateDriver: BaseAutomateDriver() {

    override fun initDriver() {
        try {
            driver = AndroidDriver(URL(SeleniumGridManager.serverAddress), Capabilities.android())
            setImplicitWait()
        } catch (e: Exception) {
            println("Fehler bei der Initialisierung des Android Drivers: ${e.message}")
            e.printStackTrace()
        }
    }

//    override fun findElementById(id: String): WebElement {
//        return driver.findElement(By.id(id))
//    }
//
//    override fun findElementsById(id: String): List<WebElement> {
//        return driver.findElements(By.id(id))
//    }

    override fun findElementById(id: String): MobileElement {
        return driver.findElementById(id)
    }

    override fun findElementsById(id: String): List<MobileElement> {
        return driver.findElementsById(id)
    }

}

class IOSAutomateDriver: BaseAutomateDriver() {
    override fun initDriver() {
        try {
            driver = IOSDriver(URL(SeleniumGridManager.serverAddress), Capabilities.iOS())
            setImplicitWait()
        } catch (e: Exception) {
            println("Fehler bei der Initialisierung des iOS Drivers: ${e.message}")
            e.printStackTrace()
        }
    }

    override fun findElementById(id: String): MobileElement {
        return driver.findElementByAccessibilityId(id)
    }

    override fun findElementsById(id: String): List<MobileElement> {
        return driver.findElementsByAccessibilityId(id)
    }
}

//    override fun findElementById(id: String): WebElement {
//        return driver.findElement(By.id(id))
//    }
//
//    override fun findElementsById(id: String): List<WebElement> {
//        return driver.findElements(By.id(id))
//    }
