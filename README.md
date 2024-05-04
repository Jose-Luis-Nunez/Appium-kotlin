# Appium Kotlin Project


This **[Kotlin](https://kotlinlang.org)** project is an example of the implementation of an **[Appium](http://appium.io/)** test project using [TestNG](https://testng.org/) for organizing tests and [Strikt](https://strikt.io/) for assertions. 
This setup is designed to support the latest version of Appium v9.

To run the tests locally:
1. start emulator
2. start appium server
3. run test or execute command in terminal `./gradlew clean test`
> the test fails if the emulator or the appium server is not running
## Prerequisites
### Appium
#### Install appium
`npm install -g appium`
#### Start appium server
`appium`

### Emulator
#### Find installed emulators
`emulator -list-avds`
#### Start specific emulator
`emulator -avd [emulator name]`
### run tests with java 16
`to avoid issues use java 16 for the project`

## Write tests
#### Create Page Object
The Page Object Pattern can be used to specify elements etc.
- Every page object class inherits from BasePage()
- use driver.findElement and selector to identify elements

``` kotlin
class BeermatPage : BasePage() {

    private val totalPrice: WebElement get() = driver.findElement(By.id("tv_total_price_of_line"))
    
    .
    .
    .
}
```

#### Test example
Every test class inherits from BaseTest()
``` kotlin
class BeermatTest : BaseTest() {

    private val beermat = BeermatPage()

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
