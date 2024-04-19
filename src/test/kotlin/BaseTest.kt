import appium.driver.AutomationDriver
import pageobjects.BasePage
import appium.util.DependencyInjectionModules.getModule
import appium.util.EnvironmentManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

open class BaseTest : KoinComponent {

    protected val automateDriver by inject<AutomationDriver>()

    @BeforeSuite
    fun initModule() {
        startKoin {
            printLogger()
            modules(getModule(EnvironmentManager.platform))
        }
    }

    @BeforeMethod
    fun initDriver() {
        automateDriver.startDriver()
    }

    @AfterMethod(description = "Screenshot | Video", alwaysRun = true)
    fun afterTest(result: ITestResult) {
        automateDriver.closeDriver()
    }

    inline operator fun <reified T : BasePage> T.invoke(func: T.() -> Unit) = with(T::class) {
        apply { func() }
    }
}
