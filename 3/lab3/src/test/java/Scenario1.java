import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class Scenario1 {

    private WebDriver googleDriver;
    private WebDriver fireFoxDriver;
    private final static String EXPECTED_TITLE = "Suplementy i odżywki: Największy sklep online w PL";
    private final static String URL = "https://sklep.sfd.pl";
    private final static String COOCKIE_DIALOG = "cm";
    private final static String INPUT_SEARCH_FIELD = "Białko";
    private final static String RESULT_NOT_FOUND = "NIE ZNALEZIONO PRODUKTÓW";

    private final static String ADDED_TO_BASKET_CONFIRM = "PRODUKT ZOSTAŁ DODANY";

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup() {
        googleDriver = new ChromeDriver();
        fireFoxDriver = new FirefoxDriver();
    }

    @AfterEach
    public void close() {
        googleDriver.quit();
        fireFoxDriver.quit();
    }

    @Test
    public void addToBasketChrome() {
        //CHROME
        String actualTitle = "";
        googleDriver.get(URL);

        actualTitle = googleDriver.getTitle();
        Assertions.assertEquals(actualTitle, EXPECTED_TITLE);

        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(COOCKIE_DIALOG)));
        } catch (TimeoutException ignored) {
        }
        if (googleDriver.findElement(By.id(COOCKIE_DIALOG)).isDisplayed()) {
            googleDriver.findElement(By.id("c-p-bn")).click(); //accept all
        }
        WebElement searchField = googleDriver.findElement(By.id("ctl00_SklepHeaderControl1_DefaultHeaderControl_ZnajdzControl1_tbSzukaj"));
        searchField.sendKeys(INPUT_SEARCH_FIELD);
        searchField.sendKeys(Keys.ENTER);
        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_panFlashInfo")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: ctl00_panFlashInfo not found");
        }
        String resultString = googleDriver.findElement(By.id("ctl00_panFlashInfo")).getText();
        Assertions.assertFalse(resultString.contentEquals(RESULT_NOT_FOUND));
        resultString = resultString.replaceAll("\\D+", "");
        int num = Integer.parseInt(resultString);
        Assertions.assertTrue(num > 0);

        googleDriver.findElement(By.id("ctl00_ContentPlaceHolder1_ProduktyControl1_repProdukty_ctl00_ProduktControlItem_container")).click();
        googleDriver.findElement(By.id("ctl00_ContentPlaceHolder1_btnDodajDoKoszyka")).click();
        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("warianty")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: warianty not found");
        }
        WebElement tasteDialog = googleDriver.findElement(By.id("warianty"));
        Assertions.assertTrue(tasteDialog.isDisplayed());

        List<WebElement> tasteVariants = googleDriver.findElements(By.className("choose-variant-box__list-item"));
        Assertions.assertFalse(tasteVariants.isEmpty());

        tasteVariants.get(0).click();
        googleDriver.findElement(By.className("add-to-basket-js")).click();
        try {
            WebDriverWait wait = new WebDriverWait(googleDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basket-popup")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: basket-popup not found");
        }
        Assertions.assertTrue(googleDriver.findElement(By.id("basket-popup")).isDisplayed());
        String confirmationText = googleDriver.findElement(By.className("basket-popup-message")).getText();
        Assertions.assertEquals(confirmationText, ADDED_TO_BASKET_CONFIRM);
    }

    @Test
    public void addToBasketFirefox() {
        //FIREFOX
        String actualTitle = "";
        fireFoxDriver.get(URL);

        actualTitle = fireFoxDriver.getTitle();
        Assertions.assertEquals(actualTitle, EXPECTED_TITLE);

        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id(COOCKIE_DIALOG)));
        } catch (TimeoutException ignored) {
        }
        if (fireFoxDriver.findElement(By.id(COOCKIE_DIALOG)).isDisplayed()) {
            fireFoxDriver.findElement(By.id("c-p-bn")).click(); //accept all
        }
        WebElement searchField = fireFoxDriver.findElement(By.id("ctl00_SklepHeaderControl1_DefaultHeaderControl_ZnajdzControl1_tbSzukaj"));
        searchField.sendKeys(INPUT_SEARCH_FIELD);
        searchField.sendKeys(Keys.ENTER);
        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("ctl00_panFlashInfo")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: ctl00_panFlashInfo not found");
        }
        String resultString = fireFoxDriver.findElement(By.id("ctl00_panFlashInfo")).getText();
        Assertions.assertFalse(resultString.contentEquals(RESULT_NOT_FOUND));
        resultString = resultString.replaceAll("\\D+", "");
        int num = Integer.parseInt(resultString);
        Assertions.assertTrue(num > 0);

        fireFoxDriver.findElement(By.id("ctl00_ContentPlaceHolder1_ProduktyControl1_repProdukty_ctl00_ProduktControlItem_container")).click();
        fireFoxDriver.findElement(By.id("ctl00_ContentPlaceHolder1_btnDodajDoKoszyka")).click();
        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("warianty")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: warianty not found");
        }
        WebElement tasteDialog = fireFoxDriver.findElement(By.id("warianty"));
        Assertions.assertTrue(tasteDialog.isDisplayed());

        List<WebElement> tasteVariants = fireFoxDriver.findElements(By.className("choose-variant-box__list-item"));
        Assertions.assertFalse(tasteVariants.isEmpty());

        tasteVariants.get(0).click();
        fireFoxDriver.findElement(By.className("add-to-basket-js")).click();
        try {
            WebDriverWait wait = new WebDriverWait(fireFoxDriver, Duration.ofMillis(5000));
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("basket-popup")));
        } catch (TimeoutException e) {
            throw new TimeoutException("id: basket-popup not found");
        }
        Assertions.assertTrue(fireFoxDriver.findElement(By.id("basket-popup")).isDisplayed());
        String confirmationText = fireFoxDriver.findElement(By.className("basket-popup-message")).getText();
        Assertions.assertEquals(confirmationText, ADDED_TO_BASKET_CONFIRM);
    }

}
