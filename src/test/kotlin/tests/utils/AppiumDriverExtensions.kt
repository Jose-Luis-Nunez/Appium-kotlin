package tests.utils

import io.appium.java_client.AppiumBy
import io.appium.java_client.AppiumDriver
import org.openqa.selenium.WebElement

class AppiumDriverExtensions {
    companion object {

        fun AppiumDriver.findElementByText(text: String): WebElement =
            findElement(AppiumBy.androidUIAutomator("new UiSelector().text(\"$text\")"))

        fun AppiumDriver.findByClassName(className: String): WebElement =
            findElement(AppiumBy.androidUIAutomator("new UiSelector().className(\"$className\")"))

        fun AppiumDriver.findElementByResourceId(resourceId: String): WebElement =
            findElement(AppiumBy.androidUIAutomator("new UiSelector().resourceId(\"$resourceId\")"))
    }
}
