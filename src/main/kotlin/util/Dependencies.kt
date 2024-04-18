package util

import appium.driver.AndroidAutomateDriver
import appium.driver.DriverSpecification
import appium.driver.IOSAutomateDriver
import org.koin.core.module.Module
import org.koin.dsl.module

fun getModule(platform: Platform): Module {
    return if (platform == Platform.Android) AndroidDependencies else IOSDependencies
}

val AndroidDependencies = module {
    single<DriverSpecification> { AndroidAutomateDriver() }
}

val IOSDependencies = module {
    single<DriverSpecification> { IOSAutomateDriver() }
}
