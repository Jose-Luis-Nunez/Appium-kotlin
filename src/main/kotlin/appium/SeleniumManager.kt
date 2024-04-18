package appium

import util.EnvironmentManager.isRemoteSeleniumGrid

object SeleniumManager {
    val serverAddress = if (isRemoteSeleniumGrid) "<IP>:<PORT>/wd/hub" else "http://127.0.0.1:4723/"
}
