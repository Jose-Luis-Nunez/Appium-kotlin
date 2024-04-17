package appium.driver

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

interface DriverSpecification {
    var driver: AppiumDriver<WebElement>
    fun initDriver()
    fun findElementById(id: String): WebElement
    fun findElementsById(id: String): List<WebElement>
    fun findElementByXpath(xPath: String): WebElement
    fun findElementsByXpath(xPath: String): List<WebElement>
    fun findElementByText(text: String): WebElement
    fun findElementByPartialText(text: String): WebElement
}
