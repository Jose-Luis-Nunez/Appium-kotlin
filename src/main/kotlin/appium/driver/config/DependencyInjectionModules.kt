package appium.driver.config

import appium.driver.builder.AndroidAutomationDriver
import appium.driver.builder.AutomationDriver
import appium.driver.builder.IosAutomationDriver
import appium.driver.config.Platform.*
import org.koin.core.module.Module
import org.koin.dsl.module

object DependencyInjectionModules {

    fun getModule(platform: Platform): Module = when (platform) {
        Android -> androidDependencies
        IOS -> iOSDependencies
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
