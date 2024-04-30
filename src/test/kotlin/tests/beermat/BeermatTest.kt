package tests.beermat

import org.testng.annotations.Ignore
import pageobjects.beermat.BeermatScreen
import org.testng.annotations.Test
import strikt.api.expect
import strikt.assertions.isEqualTo
import tests.BaseTest

class BeermatTest : BaseTest() {

    private val beermat = BeermatScreen()

    @Ignore
    @Test
    fun checkPriceForBeer() {
        val expectedAmount = 4
        val expectedTotalPrice = "10,00"

        beermat {
            insertNewPrice("2,50")
            addBeers(4)
        }
        expect {
            that(beermat.getAmount()).isEqualTo(expectedAmount)
            that(beermat.getTotalPrice()).isEqualTo(expectedTotalPrice)
        }
    }
}
