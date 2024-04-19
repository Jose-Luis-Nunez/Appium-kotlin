package appium.util

import appium.driver.AndroidAutomationDriver
import appium.driver.AutomationDriver
import appium.driver.IosAutomationDriver
import org.koin.core.module.Module
import org.koin.dsl.module

object DependencyInjectionModules {

    fun getModule(platform: Platform): Module = when (platform) {
        Platform.Android -> androidDependencies
        Platform.IOS -> iOSDependencies
    }

    private val androidDependencies = module {
        single<AutomationDriver> { AndroidAutomationDriver() }
    }

    private val iOSDependencies = module {
        single<AutomationDriver> { IosAutomationDriver() }
    }
}

enum class Platform {
    IOS,
    Android
}
