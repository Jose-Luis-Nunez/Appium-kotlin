package org.example.appium.robot

import appium.driver.AutomateDriver
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.openqa.selenium.WebElement

open class BaseRobot: KoinComponent {
    val driver by inject<AutomateDriver>()

    fun getText(webElement: WebElement) = webElement.text!!
}
