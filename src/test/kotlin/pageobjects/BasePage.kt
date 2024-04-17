package pageobjects

import appium.driver.DriverSpecification
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import org.openqa.selenium.WebElement
import org.openqa.selenium.support.ui.ExpectedConditions
import org.openqa.selenium.support.ui.WebDriverWait


private const val TIMEOUT_IN_SECONDS = 30L
open class BasePage: KoinComponent {
    val driver by inject<DriverSpecification>()

    fun getText(webElement: WebElement) = webElement.text!!

    fun clickOnElement(element: WebElement) {
        waitForElementToBeVisible(element)
        element.click()
    }

    fun WebElement.type(text: String) {
       waitForElementToBeVisible(this)
        if (this.isDisplayed && this.isEnabled) {
            this.click()
            this.clear()
            this.sendKeys(text)
        } else {
            throw Exception("Element is not interactable at locator: $this")
        }
    }

    private fun waitForElementToBeVisible(webElement: WebElement) {
        val wait = WebDriverWait(driver.driver, TIMEOUT_IN_SECONDS)
        wait.until(ExpectedConditions.visibilityOf(webElement))
    }
}
