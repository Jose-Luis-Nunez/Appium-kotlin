package appium.driver

import org.openqa.selenium.WebElement

interface AutomationDriver {
    fun startDriver()
    fun closeDriver()
    fun waitForElementToBeVisible(webElement: WebElement)
    fun waitForElementToBeClickable(webElement: WebElement)
    fun findElementById(id: String): WebElement
    fun findElementsById(id: String): List<WebElement>
    fun findElementByXpath(xPath: String): WebElement
    fun findElementsByXpath(xPath: String): List<WebElement>
    fun findElementByText(text: String): WebElement
    fun findElementByPartialText(text: String): WebElement
}
