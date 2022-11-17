package FirefoxLoginSearchATC;


import org.testng.annotations.*;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;



public class SelectItemMenub extends BasePage{

	@BeforeTest
	public void URLHandle() throws Exception
	{
		getDriver();
		getURL();
		popupclose();
	}
	

	@Test(priority=0)
	public void searchboxhandle() throws InterruptedException {
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys("Macbook");
		Thread.sleep(5000);
		List<WebElement> suggestionlist = driver.findElements(By.xpath("//div[contains(@class, 'lrtEPN')]"));
		System.out.println("Print stack from Search Box");
		for(WebElement e:suggestionlist)
		{
			System.out.println(e.getText());
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			WebElement Macbookpro= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("macbook air m1")));
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

	@Test(priority = 1)
	public void completeproductlistdetails() throws Exception
	{
		Actions act = new Actions(driver);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement sortingitem = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = '_10UF8M _3LsR0e']")));
		sortingitem.click();
		Thread.sleep(5000);
		List<WebElement> Getlaptoplist = driver.findElements(By.xpath("//div[contains(@class, '_1AtVbE')]"));
		for (WebElement e:Getlaptoplist)
		{
			System.out.println(e.getText());
			WebElement Macbookpro= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("APPLE 2020 Macbook Air M1 - (8 GB/256 GB SSD/Mac OS Big Sur) MGN93HN/A")));
			act.moveToElement(Macbookpro).perform();
		}
		try 
		{
			if(driver.findElement(By.linkText("APPLE 2020 Macbook Air M1 - (8 GB/256 GB SSD/Mac OS Big Sur) MGN93HN/A")).isDisplayed())
			{
				String urlSave = driver.findElement(By.linkText("APPLE 2020 Macbook Air M1 - (8 GB/256 GB SSD/Mac OS Big Sur) MGN93HN/A")).getAttribute("href");
				driver.get(urlSave);
				System.out.println("<!----select mac----->>");
				takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			}
		}
		catch(Exception e) 
		{
			e.printStackTrace();
		}  
		
	}
	@AfterTest
	public void close()
	{
		driver.quit();
	}


}
