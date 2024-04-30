package tests

import appium.driver.config.DriverConfigurator.getDriver
import pageobjects.BaseScreen
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod

open class BaseTest {

    private val automationDriver = getDriver()

    @BeforeMethod
    fun initDriver() {
        automationDriver.startDriver()
    }

    @AfterMethod(description = "Screenshot | Video", alwaysRun = true)
    fun afterTest(result: ITestResult) {
        automationDriver.quitDriver()
    }

    inline operator fun <reified T : BaseScreen> T.invoke(func: T.() -> Unit) = with(T::class) {
        apply { func() }
    }
}
