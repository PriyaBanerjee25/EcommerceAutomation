package FirefoxLoginSearchATC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.List;

import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WriteDataExcel extends BasePage {
	public XSSFCell Cell ;
	@Test(priority = 0)
	public void writeData() throws Exception {
		WebDriverManager.firefoxdriver().setup();
		driver = new FirefoxDriver();
		Thread.sleep(5000);
		//		driver.navigate().to("https://www.flipkart.com");	
		//		driver.manage().window().maximize();
		//		driver.manage().deleteAllCookies();

		try {
			FileInputStream fStream = new FileInputStream(new File(excelLocation)); //Enter the path to your excel here
			// Create workbook instance referencing the file created above
			XSSFWorkbook workbook = new XSSFWorkbook(fStream);
			// Get your first or desired sheet from the workbook
			XSSFSheet sheet = workbook.getSheetAt(0); // getting first sheet
			//int rowCount = sheet.getLastRowNum() - sheet.getFirstRowNum();
			int rowCount = sheet.getLastRowNum();
			System.out.println(rowCount);
			XSSFRow row = sheet.getRow(1);
			XSSFRow newrow = sheet.createRow(rowCount+1);
			driver.get("https://www.flipkart.com");	
			Thread.sleep(5000);
			driver.findElement(By.xpath("//button[@class='_2KpZ6l _2doB4z']")).click();
			Thread.sleep(5000);

			for(int i=1; i<=rowCount;i++)
			{

				activity = sheet.getRow(i).getCell(1).getStringCellValue();

				location = sheet.getRow(i).getCell(0).getStringCellValue();
				System.out.println(location +" , "+ activity);
				Actions act = new Actions(driver);
				driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).sendKeys(location);
				List<WebElement> suggestionlist = driver.findElements(By.xpath("//div[contains(@class, 'lrtEPN')]"));
				System.out.println("Print stack from Search Box");
				for(WebElement e:suggestionlist)
				{
					System.out.println(e.getText());
					WebDriverWait wait1 = new WebDriverWait(driver, 60);
					//WebElement Macbookpro= wait1.until(ExpectedConditions.elementToBeClickable(By.partialLinkText(activity)));
					//act.moveToElement(Macbookpro).perform();
					WebElement Macbookpro= wait1.until(ExpectedConditions.visibilityOfElementLocated(By.partialLinkText(activity)));
					Thread.sleep(5000);
					Cell = sheet.getRow(i).createCell(2);
					if(Macbookpro.isDisplayed())
					{
						Cell.setCellValue("PASS");
					}
					else
					{
						Cell.setCellValue("FAIL");
					}

				}
				Thread.sleep(5000);
				driver.findElement(By.xpath("//input[@placeholder='Search for products, brands and more']")).clear();
				Thread.sleep(5000);
				FileOutputStream otputstream= new FileOutputStream(excelLocation);
				workbook.write(otputstream);
			}
			

			fStream.close();
		} 
		catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}



	@AfterTest
	public void afterClass() {
		driver.quit();
	}
}
