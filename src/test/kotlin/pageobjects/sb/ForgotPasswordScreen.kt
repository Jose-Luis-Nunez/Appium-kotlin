package tests.pageobjects.sb

import org.openqa.selenium.By
import org.openqa.selenium.WebElement
import pageobjects.BaseScreen

class ForgotPasswordScreen: BaseScreen() {
    private val inptForgotEmail: WebElement get() = driver.findElement(By.id("id_input_password_recovery_email"))
    private val resetPasswordButton: WebElement get() = driver.findElement(By.id("id_button_password_recovery_reset_pw"))

    fun enterForgotEmail(email: String) {
        inptForgotEmail.type(email)
    }

    fun clickOnResetPasswordButton() {
        clickOnElement(resetPasswordButton)
    }
}
