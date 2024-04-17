package appium.driver

import io.appium.java_client.remote.IOSMobileCapabilityType.XCODE_ORG_ID
import io.appium.java_client.remote.IOSMobileCapabilityType.XCODE_SIGNING_ID
import io.appium.java_client.remote.MobileCapabilityType.*
import org.example.util.EnvironmentManager.isRealIosDevice
import org.openqa.selenium.remote.CapabilityType.PLATFORM_NAME
import org.openqa.selenium.remote.DesiredCapabilities
import util.PropertiesReader
import java.io.File

object Capabilities {

    fun android(): DesiredCapabilities {
        val capabilities = createCommonCapabilities()

        return capabilities.apply {
            setCapability("appium:newCommandTimeout", 300)
            setCapability("appium:noSign", true)
            setCapability(PLATFORM_NAME, "Android")
            setCapability(DEVICE_NAME, "Android")
            setCapability(AUTOMATION_NAME, "UIAutomator2")
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

        return when (isRealIosDevice) {
            true ->
                capabilities.apply {
                    setCapability(XCODE_ORG_ID, "<ID>")
                    setCapability(XCODE_SIGNING_ID, "<SIGN ID>")
                    setCapability(UDID, "<PHONE UDID>")
                }

            false -> capabilities
        }
    }
}

private fun createCommonCapabilities(): DesiredCapabilities = DesiredCapabilities().apply {
    setCapability(APP, getTestAppPath())
}

private fun getTestAppPath(): String {
    val propertiesReader = PropertiesReader()
    return File(propertiesReader.getProp("android.app.path") as String).absolutePath
}
