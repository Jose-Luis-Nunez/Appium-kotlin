import org.example.appium.robot.BeermatScreenRobot
import org.testng.annotations.Test
import strikt.api.expect
import strikt.assertions.isEqualTo

class BeermatTest : BaseTest() {

    private val beermat = BeermatScreenRobot()

    @Test
    fun checkPriceForBeer() {
        expect {
            that(beermat.getTotalPrice()).isEqualTo("3,00")
        }
    }
}
