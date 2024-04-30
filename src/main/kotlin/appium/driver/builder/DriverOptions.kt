package appium.driver.builder

import appium.driver.config.EnvironmentManager
import appium.util.PropertiesReader
import io.appium.java_client.android.options.UiAutomator2Options
import io.appium.java_client.ios.options.XCUITestOptions
import java.io.File
import java.time.Duration.ofSeconds

object DriverOptions {
    fun android(): UiAutomator2Options {
        return UiAutomator2Options().apply {
            setNewCommandTimeout(ofSeconds(300))
            setNoSign(true)
            setPlatformName("Android")
            setDeviceName("Android")
            setAutomationName("UiAutomator2")
            setAutoGrantPermissions(true)
            setEnforceAppInstall(true)
//            setAppActivity("de.hajo.beermat.MainActivity") //app-debug.apk
//            setAppWaitActivity("de.hajo.beermat.MainActivity") //app-debug.apk
            setAppWaitActivity("com.wso.smartbroker.presentation.main.MainActivity") //sb.apk
            setApp(getTestAppPath())
        }
    }

    fun iOS(): XCUITestOptions {
        val options = XCUITestOptions()
        options.apply {
            setNewCommandTimeout(ofSeconds(120))
            setPlatformName("iOS")
            setDeviceName("iPhone 11")
            setAutomationName("XCUITest")
            setNoReset(false)
            setConnectHardwareKeyboard(false)
            setUseNewWDA(true)
            setWaitForQuiescence(false)
            setMaxTypingFrequency(10)
            setApp(getTestAppPath())
        }
        return when (EnvironmentManager.isRealIosDevice) {
            true -> options.setUdid("<PHONE UDID>")
            false -> options
        }
    }

    private fun getTestAppPath(): String {
        val propertiesReader = PropertiesReader()
        return File(propertiesReader.getProp("android.app.path") as String).absolutePath
    }
}
