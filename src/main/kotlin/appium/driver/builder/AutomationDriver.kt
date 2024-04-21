package appium.driver.builder

import io.appium.java_client.AppiumDriver

interface AutomationDriver {
    var driver: AppiumDriver

    fun startDriver()
    fun quitDriver()
}
