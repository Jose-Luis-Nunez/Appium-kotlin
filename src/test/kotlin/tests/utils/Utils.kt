package tests.utils

import io.appium.java_client.AppiumDriver
import io.appium.java_client.remote.SupportsContextSwitching
import java.util.UUID

fun generateRandomEmail(): String {
    val randomPart = UUID.randomUUID().toString().take(10)
    return "appiumAutomationAndroid$randomPart@gmail.com"
}

fun logCurrentAndAvailableContexts(driver: AppiumDriver) {
    val driverContext = driver as SupportsContextSwitching
    val currentContext = driverContext.context
    val availableContexts = driverContext.contextHandles

    println("current context: $currentContext")
    println("available context: $availableContexts")
}
