package appium.driver.builder

import appium.driver.config.ServerManager
import appium.util.DriverInitializationException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import java.net.URL
import java.time.Duration

abstract class BaseAutomationDriver {

    var driver: AppiumDriver by ThreadLocalDelegate.lateinit()

    fun startDriver() {
        try {
            driver = supplier()
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
        } catch (e: Exception) {
            throw DriverInitializationException("Driver Initialization Failed", e)
        }
    }

    fun quitDriver() {
        driver.quit()
    }

    abstract fun supplier(): AppiumDriver
}

object AndroidAutomationDriver : BaseAutomationDriver() {
    override fun supplier() = AndroidDriver(URL(ServerManager.serverAddress), DriverOptions.android())
}

object IosAutomationDriver : BaseAutomationDriver() {
    override fun supplier() = IOSDriver(URL(ServerManager.serverAddress), DriverOptions.iOS())
}
