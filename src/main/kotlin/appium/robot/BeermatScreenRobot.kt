package org.example.appium.robot

import org.openqa.selenium.WebElement

class BeermatScreenRobot : BaseRobot() {
    private val totalPrice: WebElement get() = driver.findElementById("tv_total_price_of_line")

    fun getTotalPrice() = getText(totalPrice).replace("[^0-9]€".toRegex(), "")
}
