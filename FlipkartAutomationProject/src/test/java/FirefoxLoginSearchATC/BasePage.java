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
import org.yaml.snakeyaml.constructor.Constructor;
//sele_nium imp_ortor for screen_sht 
import org.apache.commons.io.FileUtils;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.OutputType; 
import org.openqa.selenium.TakesScreenshot;
//A-shot importer for screenshot
import ru.yandex.qatools.ashot.*;
import ru.yandex.qatools.ashot.Screenshot;
import ru.yandex.qatools.ashot.shooting.ShootingStrategies;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BasePage{
	public static WebDriver driver;
	public static String search = "//input[@name='q']";
	public static String location = null;
	public static String activity = null;
	public static String otpt = null;
	public static String excelLocation = "C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\DataReader.xlsx";
	// Screenshot method 
	public static void takesnapshot(WebDriver webdriver, String filepath) throws Exception
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File Destn = new File(filepath);
		FileUtils.copyFileToDirectory(SrcFile, Destn);
	}
	
	public static void excelReader() throws Exception{
		try {
			FileInputStream fStream = new FileInputStream(new File(excelLocation)); //Enter the path to your excel here

			// Create workbook instance referencing the file created above
			XSSFWorkbook workbook = new XSSFWorkbook(fStream);

			// Get your first or desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0); // getting first sheet
			int rowCount = sheet.getLastRowNum();

			System.out.println(rowCount);
			//			XSSFRow row = sheet.getRow(1);

			//			XSSFCell cell1 = row.getCell(0);
			//			XSSFCell cell2 = row.getCell(1);
			//			//			XSSFCell cell3 = row.getCell(2);
			//
			//			location = cell1.toString();
			//			activity = cell2.toString();
			//			order = cell3.toString();
			for(int i=1; i<=rowCount;i++)
			{
				location = sheet.getRow(i).getCell(0).getStringCellValue();
				activity = sheet.getRow(i).getCell(1).getStringCellValue();
				System.out.println(location +" , "+ activity);
				
			}
			
			fStream.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
	public void printText(String xpathval) throws Exception {
		Thread.sleep(2000);
		java.util.List<WebElement> Products = driver.findElements(By.xpath(xpathval));
		for(WebElement product:Products) {
			System.out.println(product.getText());
			System.out.println("<!--Main Menu------->>");
			takesnapshot(driver ,"C:\\Users\\priya\\git\\repository\\FlipkartAutomationProject\\ScreenShot\\test.png" );	
			timewait(driver);
		}
	}
	public static void timewait (WebDriver driver) throws Exception{
		driver.manage().timeouts().implicitlyWait(45, TimeUnit.SECONDS);
		driver.manage().timeouts().pageLoadTimeout(45, TimeUnit.SECONDS);
	}
}