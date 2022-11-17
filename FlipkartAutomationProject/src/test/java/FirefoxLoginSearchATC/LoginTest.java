package FirefoxLoginSearchATC;

import org.testng.annotations.Test;
import org.testng.Assert;
import org.testng.annotations.*;
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

public class LoginTest extends BasePage {


	String search = "//input[@name='q']";


	//Login to flip with desired x-path
	@BeforeTest
	public void Login() throws Exception {
		getDriver();
		getURL();
		try {
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			WebElement emailaddress = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- VJZDxU']")));
			emailaddress.sendKeys("9625189455");
			WebElement password = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")));
			password.sendKeys("Priya@123");
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"))).click();
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );
			timewait(driver);
			//			driver.navigate().refresh();
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
	@Test(priority =0)
	public void MenuList() throws Exception {
		Thread.sleep(2000);
		java.util.List<WebElement> Products = driver.findElements(By.xpath("/html/body/div/div/div[2]/div"));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!--Main Menu------->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			timewait(driver);
		}
	}

	//Printing submenu all item from Fashion
	@Test(priority =1)
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
			timewait(driver);
		}
		//WebElement sub_menu = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//svg[@class='_7DCQC6']")));
		takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
		timewait(driver);
		System.out.println("Succss");
	}

	//get into fashion menu and add menu's topwear in cart
	@Test(priority =2)
	public void goToMenTopWearSectionFromFashion() throws InterruptedException
	{
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement FashionHeaderLink= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Fashion")));
		Actions act=new Actions(driver);
		try 
		{
			act.moveToElement(FashionHeaderLink).perform();
			System.out.println("<!----top wera----->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
		}
		catch(Exception e)
		{
			act.moveToElement(driver.findElement(By.xpath("//div[@class='_1psGvi SLyWEo']//div[text()='Fashion']"))).perform();
		}
		try 
		{
			if(driver.findElement(By.linkText("Men's Top Wear")).isDisplayed())
			{
				String urlSave = driver.findElement(By.linkText("Men's Top Wear")).getAttribute("href");
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

	//after class last 
	@AfterTest
	public void afterClass() {
		driver.quit();
	}


}
