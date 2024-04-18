package pageobjects.beermat

import pageobjects.BasePage
import org.openqa.selenium.WebElement

class BeermatPage : BasePage() {
    private val totalPrice: WebElement get() = driver.findElementById("tv_total_price_of_line")
    private val addBeerButton: WebElement get() = driver.findElementById("button_add")
    private val price: WebElement get() = driver.findElementById("et_price")
    private val amount: WebElement get() = driver.findElementById("tv_beer_count")

    fun getTotalPrice() = getText(totalPrice).replace("[^0-9]€".toRegex(), "")
    fun insertNewPrice(newPrice: String) = price.type(newPrice)
    fun getAmount() = getText(amount).toInt()

    fun addBeers(numberOfBeers: Int) {
        for (i in 2..numberOfBeers) {
            clickOnElement(addBeerButton)
        }
    }
}
