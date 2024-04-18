package appium.util

import appium.driver.AndroidAppiumDriver
import appium.driver.DriverSpecification
import appium.driver.IosAppiumDriver
import org.koin.core.module.Module
import org.koin.dsl.module

fun getModule(platform: Platform): Module {
    return if (platform == Platform.Android) AndroidDependencies else IOSDependencies
}

val AndroidDependencies = module {
    single<DriverSpecification> { AndroidAppiumDriver() }
}

val IOSDependencies = module {
    single<DriverSpecification> { IosAppiumDriver() }
}
