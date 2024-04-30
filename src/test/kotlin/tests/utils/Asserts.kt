package tests.utils

import strikt.api.Assertion
import org.openqa.selenium.WebElement

fun Assertion.Builder<WebElement>.isDisplayed() = assert("is displayed") { element ->
    if (element.isDisplayed) {
        pass()
    } else {
        fail()
    }
}

fun Assertion.Builder<WebElement>.isNotDisplayed() = assert("is not displayed") { element ->
    if (!element.isDisplayed) {
        pass()
    } else {
        fail(description = "Element is displayed when it should not be.")
    }
}
