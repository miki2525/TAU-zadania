import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Scenario1 {

   private WebDriver googleDriver;
   private WebDriver fireFoxDriver;
   private final static String EXPECTED_TITLE =  "Suplementy i odżywki: Największy sklep online w PL";
   private final static String URL =  "https://sklep.sfd.pl";
   private final static String COOCKIE_DIALOG = "cm";

   private final static String INPUT_SEARCH_FIELD = "Białko";

    @BeforeAll
    static void setupChromeDriver() {
        WebDriverManager.chromedriver().setup();
        WebDriverManager.firefoxdriver().setup();
    }

    @BeforeEach
    public void setup(){
        googleDriver = new ChromeDriver();
//        fireFoxDriver = new FirefoxDriver();
    }

    @AfterEach
    public void close(){
        googleDriver.quit();
//        fireFoxDriver.quit();
    }

    @Test
    public void demoTest(){

        //CHROME
        String actualTitle = "";
        googleDriver.get(URL);

        actualTitle = googleDriver.getTitle();
        Assertions.assertEquals(actualTitle, EXPECTED_TITLE);

        if(googleDriver.findElement(By.id(COOCKIE_DIALOG)).isDisplayed()){
            googleDriver.findElement(By.id("c-p-bn")).click(); //accept all
        }

        WebElement searchField = googleDriver.findElement(By.id("ctl00_SklepHeaderControl1_DefaultHeaderControl_ZnajdzControl1_tbSzukaj"));
        searchField.sendKeys(INPUT_SEARCH_FIELD);
        searchField.sendKeys(Keys.ENTER);
        String resultString = googleDriver.findElement(By.id("ctl00_panFlashInfo")).getText();
        Assertions.assertFalse(resultString.contentEquals("NIE ZNALEZIONO PRODUKTÓW"));
        resultString = resultString.replaceAll("\\D+","");
        Integer num = Integer.parseInt(resultString);
        Assertions.assertTrue(num > 0);

        googleDriver.findElement(By.id("ctl00_ContentPlaceHolder1_ProduktyControl1_repProdukty_ctl00_ProduktControlItem_container")).click();
        googleDriver.findElement(By.id("ctl00_ContentPlaceHolder1_btnDodajDoKoszyka")).click();
        WebElement tasteDialog = googleDriver.findElement(By.id("warianty"));
        Assertions.assertTrue(tasteDialog.isDisplayed());


        //FIREFOX

    }

}
