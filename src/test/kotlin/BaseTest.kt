import appium.driver.DriverSpecification
import pageobjects.BasePage
import appium.util.EnvironmentManager.platform
import appium.util.getModule
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

open class BaseTest : KoinComponent {

    protected val automateDriver by inject<DriverSpecification>()

    @BeforeSuite
    fun initModule() {
        startKoin {
            printLogger()
            modules(getModule(platform))
        }
    }

    @BeforeMethod
    fun initDriver() {
        automateDriver.initDriver()
    }

    @AfterMethod(description = "Screenshot | Video", alwaysRun = true)
    fun afterTest(result: ITestResult) {
        automateDriver.driver.quit()
    }

    inline operator fun <reified T : BasePage> T.invoke(func: T.() -> Unit) = with(T::class) {
        apply { func() }
    }
}
