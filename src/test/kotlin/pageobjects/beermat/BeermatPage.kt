package pageobjects.beermat

import org.openqa.selenium.By
import pageobjects.BasePage
import org.openqa.selenium.WebElement

class BeermatPage : BasePage() {

    private val totalPrice: WebElement get() = driver.findElement(By.id("tv_total_price_of_line"))
    private val addBeerButton: WebElement get() = driver.findElement(By.id("button_add"))
    private val price: WebElement get() = driver.findElement(By.id("et_price"))

    private val amount: WebElement get() = driver.findElement(By.id("tv_beer_count"))

    fun getTotalPrice() = getText(totalPrice).replace("[^0-9]€".toRegex(), "")
    fun insertNewPrice(newPrice: String) = price.type(newPrice)
    fun getAmount() = getText(amount).toInt()

    fun addBeers(numberOfBeers: Int) {
        for (i in 2..numberOfBeers) {
            clickOnElement(addBeerButton)
        }
    }
}
