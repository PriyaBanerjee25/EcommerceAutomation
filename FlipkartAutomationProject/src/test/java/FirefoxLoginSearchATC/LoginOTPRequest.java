package FirefoxLoginSearchATC;

import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeTest;

import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterTest;

public class LoginOTPRequest extends ConfigResource implements XpathResources{
	//POM
	private static Logger logger;
	private static WebDriver driver;
	private static ConfigResource ref;
	private WebDriverWait wait;

	public LoginOTPRequest() {
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

	@Test
	public void loginFunctionality(){
		try {
			WebElement loginID = driver.findElement(By.xpath(popUpUserID));
			loginID.sendKeys(ref.getUserId());
			//			WebElement pwd = driver.findElement(By.xpath(popUpPassword));
			//			pwd.sendKeys(ref.getPasswordValue());
			WebElement submit = driver.findElement(By.xpath(popupRequestOTPBtn));
			submit.click();
			wait.until(ExpectedConditions.invisibilityOf(submit));
			driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
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
		//		WebElement searchField = driver.findElement(By.xpath(search));
		//		searchField.sendKeys(ref.getProductName());
		//		WebElement product = driver.findElement(productXpath);
		//		softAssert.assertTrue(product.isDisplayed());
		//		searchField.submit();
		softAssert.assertAll();

		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys(ref.getProductName());
		//		Thread.sleep(5000);
		List<WebElement> suggestionlist = driver.findElements(By.xpath("//div[contains(@class, 'lrtEPN')]"));
		System.out.println("Print stack from Search Box");
		for(WebElement e:suggestionlist)
		{
			System.out.println(e.getText());
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			WebElement Macbookpro= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("macbook air m1")));
			softAssert.assertTrue(Macbookpro.isDisplayed());
			act.moveToElement(Macbookpro).perform();
		}
		try 
		{
			if(driver.findElement(By.linkText("macbook air m1")).isDisplayed())
			{
				String urlSave = driver.findElement(By.linkText("macbook air m1")).getAttribute("href");
				driver.get(urlSave);
				System.out.println("<!----Top----->>");
				takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}  
	}

	@Test(priority = 2)
	public void itemSelectionFunctionality(){
		
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement sortingitem = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = '_10UF8M'][text()='Price -- Low to High']")));
		sortingitem.click();
		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
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
		
		
//		String searchItem = "//div[@class='_4rR01T'][text()='APPLE 2022 MacBook AIR M2 - (8 GB/512 GB SSD/Mac OS Monterey) MLY23HN/A']";
//		String textst = "APPLE 2022 MacBook AIR M2 - (8 GB/512 GB SSD/Mac OS Monterey) MLY23HN/A";
//		WebDriverWait wait1 = new WebDriverWait(driver, 60);
//		WebElement sortingitem = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = '_10UF8M'][text()='Price -- Low to High']")));
//		sortingitem.click();
//		driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//		List<WebElement> completecalContent = driver.findElements(By.xpath("//div[contains(@class, '_4rR01T')]"));
//		for (int i = 0; i < completecalContent.size(); i++) {
//			System.out.println("Print complete Content : " + completecalContent.get(i).getText());
//			if (completecalContent.get(i).getText().equals(textst)) {
//				// move to a specific element
//				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
//						completecalContent.get(completecalContent.size() - 1));
//				// move slightly up as blue header comes in the picture
//				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
//				// then click on the element
//				completecalContent.get(i).click();
//				driver.manage().timeouts().implicitlyWait(40, TimeUnit.SECONDS);
//			}
//		}
	}

	// @Test(priority = 3)
	public void purchaseFunctionality() {
		logger.info("Will write later");
	}


	@AfterTest
	public static void tearDown() {
		driver.quit();
	}

	@Override
	public void enterPin() {
		// TODO Auto-generated method stub

	}

}
