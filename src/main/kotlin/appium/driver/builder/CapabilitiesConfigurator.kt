package appium.driver.builder

import appium.driver.config.EnvironmentManager
import appium.util.PropertiesReader
import io.appium.java_client.remote.IOSMobileCapabilityType
import io.appium.java_client.remote.MobileCapabilityType
import org.openqa.selenium.remote.CapabilityType
import org.openqa.selenium.remote.DesiredCapabilities
import java.io.File

object CapabilitiesConfigurator {

    fun android(): DesiredCapabilities {
        val capabilities = createCommonCapabilities()

        return capabilities.apply {
            setCapability("appium:newCommandTimeout", 300)
            setCapability("appium:noSign", true)
            setCapability(CapabilityType.PLATFORM_NAME, "Android")
            setCapability(MobileCapabilityType.DEVICE_NAME, "Android")
            setCapability(MobileCapabilityType.AUTOMATION_NAME, "UIAutomator2")
            setCapability("appium:autoGrantPermissions", true)
            setCapability("appium:enforceAppInstall", true)
            setCapability("appium:appActivity", "de.hajo.beermat.MainActivity")
            setCapability("appium:appWaitActivity", "de.hajo.beermat.MainActivity")
        }
    }

    fun iOS(): DesiredCapabilities {
        val capabilities = createCommonCapabilities()

        capabilities.apply {
            setCapability("appium:newCommandTimeout", 120)
            setCapability("appium:platformName", "iOS")
            setCapability("appium:deviceName", "iPhone 11")
            setCapability("appium:automationName", "XCUITest")
            setCapability("appium:noReset", false)
            setCapability("appium:connectHardwareKeyboard", "false")
            setCapability("appium:useNewWDA", "true")
            setCapability("appium:waitForQuiescence", "false")
            setCapability("appium:maxTypingFrequency", 10)
        }

        return when (EnvironmentManager.isRealIosDevice) {
            true ->
                capabilities.apply {
                    setCapability(IOSMobileCapabilityType.XCODE_ORG_ID, "<ID>")
                    setCapability(IOSMobileCapabilityType.XCODE_SIGNING_ID, "<SIGN ID>")
                    setCapability(MobileCapabilityType.UDID, "<PHONE UDID>")
                }

            false -> capabilities
        }
    }
    private fun createCommonCapabilities(): DesiredCapabilities = DesiredCapabilities().apply {
        setCapability(MobileCapabilityType.APP, getTestAppPath())
    }

    private fun getTestAppPath(): String {
        val propertiesReader = PropertiesReader()
        return File(propertiesReader.getProp("android.app.path") as String).absolutePath
    }
}
