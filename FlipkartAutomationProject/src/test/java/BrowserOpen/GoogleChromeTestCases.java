package BrowserOpen;

import org.testng.annotations.*;
import java.util.concurrent.TimeUnit;

//import org.openqa.selenium.PageLoadStrategy;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import io.github.bonigarcia.wdm.WebDriverManager;

public class GoogleChromeTestCases {
   
  public static WebDriver driver;
	
  //before class to launch all web browser
//  @Parameters("browser") 
  @BeforeTest
  public void LaunchappChrome() {
	  	  WebDriverManager.chromedriver().setup();
		  driver = new ChromeDriver();
		  driver.navigate().to("https://www.flipkart.com");	
		  driver.manage().window().maximize();
		  driver.manage().deleteAllCookies();
		  driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		  driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	  }
	  
  @Test
  public void f() {
  }
  
  @AfterTest
  public void afterClass() {
	  
	  driver.quit();
  }
    
}
