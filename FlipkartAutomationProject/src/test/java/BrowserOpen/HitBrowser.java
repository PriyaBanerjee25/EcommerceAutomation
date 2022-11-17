package BrowserOpen;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.List;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import java.io.*;
import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
//sele_nium imp_ortor for screen_sht 
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HitBrowser {

	public static WebDriver driver;
	String search = "//input[@name='q']";
	String UserId = "//form[@autocomplete='on']//input[@type='text']";
	String PasswordLgn = "//input[@type='password']";
	String requestOtp = "//button[contains(text(),'Request OTP')]";
	String popUpLoginButton = "//button[@type='submit']/child::span";
	// Code to take screenshot from sele_nium in_bult driver 
	public static void takesnapshot(WebDriver webdriver, String filepath) throws Exception
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File Destn = new File(filepath);
		FileUtils.copyFileToDirectory(SrcFile, Destn);
	}
	//before class to launch all web browser
	@BeforeTest
	public void Launchapp() throws Exception{
		WebDriverManager.firefoxdriver().setup();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		driver.navigate().to("https://www.flipkart.com");	
		driver.manage().window().maximize();
		takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );		
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}

	//Login to flip with desired x-path
	@Test (priority =0)
	public void Login() throws Exception {
		try {
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
//		Actions action = new Actions(driver);
//		WebElement emailaddress= driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']"));
		WebElement emailaddress = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- VJZDxU']")));
		emailaddress.sendKeys("9625189455");
//		WebElement password= driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']"));
		WebElement password = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")));
		password.sendKeys("Priya@123");
		wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"))).click();
//		action.click(lgnBtn).perform();
//		lgnBtn.click();
		takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );
//		String winHandleBefore = driver.getWindowHandle();
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
//		driver.navigate().refresh();
		
		}
		catch(ElementNotVisibleException e)
		{
			System.out.println("Different element present.");
		}
		finally
		{
			Assert.assertTrue(driver.findElement(By.xpath(search)).isDisplayed());
		}
	}
	
	//Print top menu list 
	@Test(priority =1)
	public void MenuList() throws Exception {
		Thread.sleep(2000);
		
		java.util.List<WebElement> Products = driver.findElements(By.xpath("/html/body/div/div/div[2]/div"));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!--Main Menu------->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		}
	}
	
	//Printing submenu all item from Fashion
	@Test(priority =2)
	public void SubmenuItem() throws Exception {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement menu= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Electronics")));
		Actions action = new Actions(driver);
		action.moveToElement(menu).click(menu).perform();
		java.util.List<WebElement> Products = driver.findElements(By.xpath("/html/body/div/div/div[2]/div"));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!----SubMenu Electronics----->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		}
		takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		System.out.println("Succss");
	}
	
	//get into fashion menu and add menu's topwear in cart
	@Test(priority =3)
	public void goToMenTopWearSectionFromFashion() throws InterruptedException
    {
        driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
        WebDriverWait wait1 = new WebDriverWait(driver, 60);

        WebElement FashionHeaderLink= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Fashion")));
        Actions act=new Actions(driver);
        try {
        act.moveToElement(FashionHeaderLink).perform();
        System.out.println("<!----top wera----->>");
        takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
        }
        catch(Exception e)
        {
            act.moveToElement(driver.findElement(By.xpath("//div[@class='_1psGvi SLyWEo']//div[text()='Fashion']"))).perform();
        }
        try {
        	if(driver.findElement(By.linkText("Men's Top Wear")).isDisplayed())
            {
                String urlSave = driver.findElement(By.linkText("Men's Top Wear")).getAttribute("href");
                driver.get(urlSave);
                System.out.println("<!----Top----->>");
                takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
            }
        }catch(Exception e) {e.printStackTrace();}  
    }
	
	@Test(priority=4)
	public void searchboxhandle() throws InterruptedException {
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("Macbook");
		Thread.sleep(5000);
		List<WebElement> suggestionlist = driver.findElements(By.xpath("//div[contains(@class, 'lrtEPN')]"));
		System.out.println("Print stack from Search Box");
		for(WebElement e:suggestionlist)
		{
			System.out.println(e.getText());
		}
	}
	
	//after class last 
	@AfterTest
	public void afterClass() {
		driver.quit();
	}


}
