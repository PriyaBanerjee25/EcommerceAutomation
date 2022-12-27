package BrowserOpen;

import org.testng.annotations.*;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleChromeTestCases {
   
  public static WebDriver driver;
	
  //before class to launch all web browser
//  @Parameters("browser") 
  @Test
  public void LaunchappChrome() throws Exception {
	  	  WebDriverManager.firefoxdriver().setup();
		  driver = new FirefoxDriver();
		  driver.navigate().to("https://www.flipkart.com");	
		  driver.manage().window().maximize();
		  Thread.sleep(5000);
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
		  driver.quit();
	  }
	  
  
    
}
