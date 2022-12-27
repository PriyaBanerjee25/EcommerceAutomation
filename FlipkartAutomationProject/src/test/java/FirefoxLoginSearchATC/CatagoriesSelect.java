package FirefoxLoginSearchATC;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class CatagoriesSelect extends BasePage{
	
	@BeforeTest
	public void URLHandle() throws Exception
	{
		getDriver();
		getURL();
		popupclose();
	}
	
	@Test(priority =0)
	public void SubmenuItem() throws Exception {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement menu= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Electronics")));
		Actions action = new Actions(driver);
		action.moveToElement(menu).click(menu).perform();
		printText("/html/body/div/div/div[2]/div");
		//WebElement sub_menu = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//svg[@class='_7DCQC6']")));
		takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
		timewait(driver);
		System.out.println("Succss");
	}
	
	@Test(priority =1)
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
	
	@Test(priority = 2)
	public void searchHPComputers() throws InterruptedException
	{

	    Thread.sleep(2000);
	    WebDriverWait wait1 = new WebDriverWait(driver, 40);
	    wait1.until(ExpectedConditions.presenceOfElementLocated(By.xpath("//li[text()='Newest First']")));
	    Thread.sleep(1000);
	    List<WebElement> element =  driver.findElements(By.xpath("//div[@class='_1vC4OE _2rQ-NK']"));
	    List<WebElement> elementLink = driver.findElements(By.xpath("//div[@class='_3wU53n']"));

	    int largestPrice =0, elementIndex =0, i=0;

	    for(WebElement web : element)
	    {
	        String amount =  web.getText();
	        int length = amount.length();
	        String price = amount.substring(1, length);
	        price = price.replaceAll(",", "");
	        int priceInt = Integer.parseInt(price);
	        System.out.println("Amount : "+ priceInt);
	        Thread.sleep(1000);

	        if(priceInt > largestPrice) {

	            largestPrice = priceInt;

	            elementIndex = i;
	        }

	        i++;
	    }


	    JavascriptExecutor jse = (JavascriptExecutor)driver;
	    jse.executeScript("arguments[0].scrollIntoView(true);", elementLink.get(elementIndex-1));

	    WebDriverWait wait = new WebDriverWait(driver, 20);
	    wait.until(ExpectedConditions.elementToBeClickable(elementLink.get(elementIndex)));

	    elementLink.get(elementIndex).click();
	}
	
	@AfterTest
	public void close()
	{
		driver.quit();
	}

}
