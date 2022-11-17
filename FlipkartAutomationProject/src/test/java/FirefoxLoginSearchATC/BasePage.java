package FirefoxLoginSearchATC;


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
//A-shot importer for screenshot
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage {
	public static WebDriver driver;
	// Screenshot method 
	public static void takesnapshot(WebDriver webdriver, String filepath) throws Exception
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File Destn = new File(filepath);
		FileUtils.copyFileToDirectory(SrcFile, Destn);
	}
	
	public static void getDriver() throws Exception
	{
		WebDriverManager.firefoxdriver().setup();
		//System.setProperty("webdriver.gecko.driver", "C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\driver\\geckodriver.exe");
		driver = new FirefoxDriver();
		
	}
	//Close popup
	public static void popupclose() throws Exception 
	{
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
	}
	//Get dedicated URL
	public static void getURL() throws Exception
	{
		driver.navigate().to("https://www.flipkart.com");	
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
	}
	//Take A-Shot screenshot 
	public static void takesnapshotyshot (WebDriver driver) throws Exception{
		//Begin A-shot mv_n repository for sreen_sht
		Screenshot scrnshot = new AShot().shootingStrategy(ShootingStrategies.viewportPasting(1000)).takeScreenshot(driver);
		ImageIO.write(scrnshot.getImage(), "jpg", new File("C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test1.png"));		
		//End
	}
	
	public static void timewait (WebDriver driver) throws Exception{
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}
}