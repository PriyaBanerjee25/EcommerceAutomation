package FirefoxLoginSearchATC;
//import FirefoxLoginSearchATC.ConfigResource;
//import FirefoxLoginSearchATC.XpathResources;
import java.io.IOException;
import java.util.Set;
import java.util.concurrent.TimeUnit;
//import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;


public class LoginTest extends ConfigResource implements XpathResources{
	private static Logger logger;
    private static WebDriver driver;
    private static ConfigResource ref;
    private WebDriverWait wait;

    public LoginTest() {
        logger = Logger.getLogger(LoginTest.class.getName());
        ref = new ConfigResource();
        if(getBrowserName().equals("chrome")) {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver();
        }else if(getBrowserName().equals("firefox")){
            WebDriverManager.firefoxdriver().setup();
            driver=new FirefoxDriver();
            System.out.println("LoginTest");
        }
        driver.manage().window().maximize();
    }


    @BeforeTest
    public void createDriver() throws IOException {
    	LoginTest obj = new LoginTest();
        driver.get(obj.getUrlValue());
        logger.fine("Driver instantiated Successfully");
        wait = new WebDriverWait(driver, 15);
        WebElement popUp = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(popUpUserID)));
        Assert.assertTrue(popUp.isDisplayed(), "Worked");
    }

    @BeforeClass
    public void loginFunctionality() {
        try {
            WebElement loginID = driver.findElement(By.xpath(popUpUserID));
            loginID.sendKeys(ref.getUserId());
            WebElement pwd = driver.findElement(By.xpath(popUpPassword));
            pwd.sendKeys(ref.getPasswordValue());
            WebElement submit = driver.findElement(By.xpath(popUpLoginButton));
            submit.click();
            wait.until(ExpectedConditions.invisibilityOf(submit));
        } catch (ElementNotVisibleException e) {
            logger.info("Different Element is present");
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Assert.assertTrue(driver.findElement(By.xpath(search)).isDisplayed());
        }
    }

    @Test(priority = 1)
    public void searchItemFunctionality() {
        SoftAssert softAssert = new SoftAssert();
        WebElement searchField = driver.findElement(By.xpath(search));
        searchField.sendKeys(ref.getProductName());
        WebElement product = driver.findElement(productXpath);
        softAssert.assertTrue(product.isDisplayed());
        searchField.submit();
        softAssert.assertAll();
    }

    @Test(priority = 2)
    public void itemSelectionFunctionality(){
    	System.out.println("itemSelectionFunctionality");
        WebElement shirt = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(oneTshirt)));
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        shirt.click();
        String Parent = driver.getWindowHandle();
        Set<String> winHandle = driver.getWindowHandles();
        for (String s : winHandle) {
            if (!s.equals(Parent))
                driver.switchTo().window(s);
            logger.info(s);
        }
        wait.until(ExpectedConditions.invisibilityOf(shirt));
    }

    // @Test(priority = 3)
    public void purchaseFunctionality() {
        logger.info("Will write later");
    }

    @Override
    @Test(priority = 1)
    public void enterPin() {
        logger.info("PIN is 824512");
    }

    @AfterClass
    public static void tearDown() {
    	System.out.println("Quit");
        driver.quit();
    }
}
