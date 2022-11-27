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
		String searchItem = "//div[@class='_4rR01T'][text()='APPLE 2020 Macbook Air M1 - (8 GB/256 GB SSD/Mac OS Big Sur) MGND3HN/A']";
		String textst = "APPLE 2022 MacBook AIR M2 - (8 GB/512 GB SSD/Mac OS Monterey) MLY23HN/A";
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement sortingitem = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//div[@class = '_10UF8M'][text()='Price -- Low to High']")));
		sortingitem.click();
		Thread.sleep(5000);
		List<WebElement> completecalContent = driver.findElements(By.xpath("//div[contains(@class, '_4rR01T')]"));
		for (int i = 0; i < completecalContent.size(); i++) {
			System.out.println("Print complete Content : " + completecalContent.get(i).getText());
			if (completecalContent.get(i).getText().equals(textst)) {
				// move to a specific element
				((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView();",
						completecalContent.get(completecalContent.size() - 1));
				// move slightly up as blue header comes in the picture
				((JavascriptExecutor) driver).executeScript("window.scrollBy(0,-100)");
				// then click on the element
				completecalContent.get(i).click();
				Thread.sleep(5000);
			}

		}
	}

	@Test(priority = 2)
	public void Addtocart() throws Exception
	{
		WebDriverWait wait = new WebDriverWait(driver,60);
		String btnAddtoCart = "//button[@class='_3v1-ww'][text()='ADD TO CART']";
		WebElement Btatc = wait.until(ExpectedConditions.elementToBeClickable(By.xpath(btnAddtoCart)));
		Btatc.click();
		Thread.sleep(5000);
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}


}
