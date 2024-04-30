package tests.pageobjects.sb

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pageobjects.AppContext
import pageobjects.BaseScreen

class LoginScreen : BaseScreen() {
    private val forgotPasswordButton: WebElement get() = driver.findElement(By.id("id_button_login_password_recovery"))
    private val successInfo: WebElement get() = driver.findElement(By.cssSelector(".alert-success .text-label-sm"))

    fun clickOnForgotPasswordBtn() {
        findAndSwitchToContext(appContext = AppContext.WEBVIEW)
        clickOnElement(forgotPasswordButton)
    }

    fun getSuccessInfoAlert(): WebElement {
        return successInfo
    }
}
