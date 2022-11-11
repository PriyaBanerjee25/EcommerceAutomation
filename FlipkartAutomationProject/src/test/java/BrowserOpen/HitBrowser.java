package BrowserOpen;

import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;
import javax.imageio.ImageIO;
import java.io.*;
import org.openqa.selenium.By;
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
//A-shot importer for screenshot
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import io.github.bonigarcia.wdm.WebDriverManager;

public class HitBrowser {

	public static WebDriver driver;
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
	public void Launchapp() throws Exception {
		WebDriverManager.firefoxdriver().setup();
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
		WebElement emailaddress= driver.findElement(By.xpath("//input[@class='_2IX_2- VJZDxU']"));
		emailaddress.sendKeys("9953121985");
		WebElement password= driver.findElement(By.xpath("//input[@class='_2IX_2- _3mctLh VJZDxU']"));
		password.sendKeys("Mousomi@6&&");
		WebElement lgnBtn = driver.findElement(By.xpath("//button[@class='_2KpZ6l _2HKlqd _3AWRsL']"));
		lgnBtn.click();
		//Begin A-shot mv_n repository for sreen_sht
		Screenshot scrnshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(scrnshot.getImage(), "jpg", new File("C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test1.png"));		
		//End
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}

	@Test(priority =2)
	public void ItemList() throws InterruptedException {
		Thread.sleep(2000);
		java.util.List<WebElement> Products = driver.findElements(By.xpath("/html/body/div/div/div[2]/div"));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!--------->>");
			driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		}
	}

	@Test(priority =1)
	public void SelectItem() throws InterruptedException {
		Thread.sleep(2000);
		WebDriverWait wait1 = new WebDriverWait(driver, 60);
		WebElement menu= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText("Electronics")));
		//WebElement electronic_lpt = wait1.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='_6WOcW9 _2-k99T'][contains(.,'Laptop and Desktop')]")));
		//WebElement menu = driver.findElement(By.xpath("//span[text()='Electronics' and @class='_1QZ6fC _3Lgyp8']"));
		Actions action = new Actions(driver);
		action.moveToElement(menu).click(menu).perform();
//		WebElement sub_menu = wait1.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[text()='Laptop And Desktop' and @class='_6WOcW9]")));
		WebElement sub_menu =  driver.findElement(By.xpath("//*[@text()='Laptop And Desktop' and @class='_6WOcW9]"));
		//Actions action = new Actions(driver);
		//action.moveToElement(menu).perform()//*[@text() = “Submit”]
		action.moveToElement(sub_menu).click(sub_menu).build().perform();
		//Actions action = new Actions(driver);
		//action.moveToElement(electronic).click().build().perform();
		//action.moveToElement(electronic).moveToElement(electronic_lpt).click().build().perform();
		driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);
		System.out.println("Sucss");
	}
	
	//after class last 
	@AfterTest
	public void afterClass() {
		driver.quit();
	}


}
