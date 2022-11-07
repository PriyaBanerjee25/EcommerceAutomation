package BrowserOpen;

import org.testng.annotations.Test;
import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
//import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.*;

import io.github.bonigarcia.wdm.WebDriverManager;

public class HitBrowser {
	
	public static WebDriver driver;
	
	  //before class to launch all web browser
//	  @Parameters("browser") 
	  @BeforeTest
	  public void Launchapp() {
//		  if(browser.equalsIgnoreCase("firefox")) {
			  WebDriverManager.firefoxdriver().setup();
			  driver = new FirefoxDriver();
			  driver.navigate().to("https://www.flipkart.com");	
			  driver.manage().window().maximize();
			  driver.manage().deleteAllCookies();
			  driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
			  driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
//		  }
//		  else if(browser.equalsIgnoreCase("chrome"))
//		  {
//			  WebDriverManager.chromedriver().setup();
//			  driver = new ChromeDriver();
//			  driver.navigate().to("https://www.flipkart.com");	
//			  driver.manage().window().maximize();
//			  driver.manage().deleteAllCookies();
//			  driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
//			  driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
//		  }
//		  else
//		  {
//			  throw new IllegalArgumentException("The browser type is undefined");
//		  }
			
	  }
	  
	  
	  //Login to flipkart with desired xpath
	  @Test
	  public void f() {
		  
	  }
	  
	  //after class last 
	  @AfterTest
	  public void afterClass() {
		  
		  driver.quit();
	  }


}
