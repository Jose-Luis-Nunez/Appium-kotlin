package appium.driver

import appium.util.EnvironmentManager.isRemoteSeleniumGrid

object ServerManager {
    val serverAddress = if (isRemoteSeleniumGrid) "<IP>:<PORT>/wd/hub" else "http://127.0.0.1:4723/"
}
