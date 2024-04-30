package tests.sb

import org.testng.annotations.Test
import strikt.api.expect
import tests.BaseTest
import tests.pageobjects.sb.ForgotPasswordScreen
import tests.pageobjects.sb.LandingScreen
import tests.pageobjects.sb.LoginScreen
import tests.utils.generateRandomEmail
import tests.utils.isDisplayed

class SbTest : BaseTest() {

    private val landingScreen = LandingScreen()
    private val forgetPasswordScreen = ForgotPasswordScreen()
    private val loginScreen = LoginScreen()

    @Test
    fun example() {
        val email = generateRandomEmail()

        landingScreen {
            clickOnLogin()
            clickOnAcceptAllBtn()
        }
        loginScreen {
            clickOnForgotPasswordBtn()
        }
        forgetPasswordScreen {
            enterForgotEmail(email)
            clickOnResetPasswordButton()
        }
        expect {
            that(loginScreen.getSuccessInfoAlert()).isDisplayed()
        }
    }
}
