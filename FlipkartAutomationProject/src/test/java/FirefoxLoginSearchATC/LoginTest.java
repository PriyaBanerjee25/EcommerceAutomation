package FirefoxLoginSearchATC;

import org.openqa.selenium.By;
import org.openqa.selenium.ElementNotVisibleException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;


public class LoginTest extends BasePage{

	//Login to flip with desired x-path
	@BeforeTest
	public void getUrl_Login() throws Exception {
		getDriver();
		getURL();
		//popupclose();
	}
	@Test(priority =0)
	public void flipkartLogin() throws Exception{

		try 
		{
			WebDriverWait wait1 = new WebDriverWait(driver, 100);
			WebElement emailaddress = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- VJZDxU']")));
			emailaddress.sendKeys("9625189455");
			WebElement password = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']")));
			password.sendKeys("Priya@123");
			wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"))).click();
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );
			timewait(driver);

			Thread.sleep(5000);
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
		//driver.navigate().refresh();
		Thread.sleep(2000);
		java.util.List<WebElement> Products = driver.findElements(By.xpath("/html/body/div/div/div[2]/div"));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!--Main Menu------->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			timewait(driver);
		}
	}

	//after class last 
	@AfterTest
	public void afterClass() {
		driver.quit();
	}

}
