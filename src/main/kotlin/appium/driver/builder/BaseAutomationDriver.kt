package appium.driver.builder

import appium.driver.config.ServerManager
import appium.util.DriverInitializationException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import java.net.URL
import java.time.Duration

abstract class BaseAutomationDriver : AutomationDriver {

    override var driver: AppiumDriver by ThreadLocalDelegate.lateinit()

    protected fun initializeDriver(supplier: () -> AppiumDriver) {
        try {
            driver = supplier()
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10))
        } catch (e: Exception) {
            throw DriverInitializationException("Driver Initialization Failed", e)
        }
    }

    override fun quitDriver() {
        driver.quit()
    }
}

class AndroidAutomationDriver : BaseAutomationDriver() {
    override fun startDriver() {
        initializeDriver {
            AndroidDriver(URL(ServerManager.serverAddress), DriverOptionsConfigurator.android())
        }
    }
}

class IosAutomationDriver : BaseAutomationDriver() {
    override fun startDriver() {
        initializeDriver {
            IOSDriver(URL(ServerManager.serverAddress), DriverOptionsConfigurator.iOS())
        }
    }
}
