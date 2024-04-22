package appium.driver.config

import appium.driver.builder.AndroidAutomationDriver
import appium.driver.builder.BaseAutomationDriver
import appium.driver.builder.IosAutomationDriver
import appium.driver.config.Platform.IOS
import appium.driver.config.Platform.Android

object DriverConfigurator {
    fun getDriver(platform: Platform = EnvironmentManager.platform): BaseAutomationDriver =
        when (platform) {
            Android -> AndroidAutomationDriver
            IOS -> IosAutomationDriver
        }
}

enum class Platform {
    IOS,
    Android
}
