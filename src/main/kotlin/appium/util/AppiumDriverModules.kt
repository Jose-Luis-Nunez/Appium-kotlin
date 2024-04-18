package appium.util

import appium.driver.AndroidAppiumDriver
import appium.driver.DriverSpecification
import appium.driver.IosAppiumDriver
import org.koin.core.module.Module
import org.koin.dsl.module

object AppiumDriverModules {

    fun getModule(platform: Platform): Module = when (platform) {
        Platform.Android -> androidDependencies
        Platform.IOS -> iOSDependencies
    }

    private val androidDependencies = module {
        single<DriverSpecification> { AndroidAppiumDriver() }
    }

    private val iOSDependencies = module {
        single<DriverSpecification> { IosAppiumDriver() }
    }
}

enum class Platform {
    IOS,
    Android
}
