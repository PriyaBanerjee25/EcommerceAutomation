package ExcelReadWrite;
import FirefoxLoginSearchATC.BasePage;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
//import org.testng.Assert;
//import org.testng.annotations.AfterTest;
//import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
//import com.google.common.collect.Table.Cell;

import io.github.bonigarcia.wdm.WebDriverManager;

public class ReadExcelData extends BasePage {
	//Workbook
	@Test(priority = 0)
	public void ReadData() throws Exception
	{
		getDriver();
		Thread.sleep(5000);
		getURL();
		driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
		Thread.sleep(5000);
		try {
			System.out.println("ABC");
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
		Actions act = new Actions(driver);
		driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys(location);

		List<WebElement> suggestionlist = driver.findElements(By.xpath("//div[contains(@class, 'lrtEPN')]"));
		System.out.println("Print stack from Search Box");
		for(WebElement e:suggestionlist)
		{
			System.out.println(e.getText());
			WebDriverWait wait1 = new WebDriverWait(driver, 60);
			WebElement Macbookpro= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(activity)));
			act.moveToElement(Macbookpro).perform();

		}
		try 
		{
			if(driver.findElement(By.linkText(activity)).isDisplayed())
			{
				String urlSave = driver.findElement(By.linkText(activity)).getAttribute("href");
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

	@AfterTest
	public void afterClass() {
		driver.quit();
	}
}
