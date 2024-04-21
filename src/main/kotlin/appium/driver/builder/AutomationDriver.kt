package appium.driver.builder

import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

interface AutomationDriver {
    var driver: AppiumDriver<WebElement>

    fun startDriver()
    fun quitDriver()
}
