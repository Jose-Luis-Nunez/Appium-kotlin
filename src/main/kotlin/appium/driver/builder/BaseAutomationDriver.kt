package appium.driver.builder

import appium.driver.config.ServerManager
import appium.util.DriverInitializationException
import io.appium.java_client.AppiumDriver
import io.appium.java_client.android.AndroidDriver
import io.appium.java_client.ios.IOSDriver
import org.openqa.selenium.WebElement
import java.net.URL
import java.util.concurrent.TimeUnit

abstract class BaseAutomationDriver : AutomationDriver {

    override var driver: AppiumDriver<WebElement> by ThreadLocalDelegate.lateinit()

    protected fun initializeDriver(supplier: () -> AppiumDriver<WebElement>) {
        try {
            driver = supplier()
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS)
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
            AndroidDriver(URL(ServerManager.serverAddress), CapabilitiesConfigurator.android())
        }
    }
}

class IosAutomationDriver : BaseAutomationDriver() {
    override fun startDriver() {
        initializeDriver {
            IOSDriver(URL(ServerManager.serverAddress), CapabilitiesConfigurator.iOS())
        }
    }
}
