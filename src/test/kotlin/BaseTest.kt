import appium.driver.builder.AutomationDriver
import pageobjects.BasePage
import appium.driver.config.DependencyInjectionModules.getModule
import appium.driver.config.EnvironmentManager
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.koin.core.context.startKoin
import org.testng.ITestResult
import org.testng.annotations.AfterMethod
import org.testng.annotations.BeforeMethod
import org.testng.annotations.BeforeSuite

open class BaseTest : KoinComponent {

    private val automationDriver by inject<AutomationDriver>()

    @BeforeSuite
    fun initModule() {
        startKoin {
            printLogger()
            modules(getModule(EnvironmentManager.platform))
        }
    }

    @BeforeMethod
    fun initDriver() {
        automationDriver.startDriver()
    }

    @AfterMethod(description = "Screenshot | Video", alwaysRun = true)
    fun afterTest(result: ITestResult) {
        automationDriver.quitDriver()
    }

    inline operator fun <reified T : BasePage> T.invoke(func: T.() -> Unit) = with(T::class) {
        apply { func() }
    }
}
