package FirefoxLoginSearchATC;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.Properties;
import java.util.logging.Logger;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;

public class ConfigResource  {
	private static Logger logger = Logger.getLogger(ConfigResource.class.getName());
	private String result = "";
	//    private InputStream inputStream;
	//Class files
	private FileInputStream inputStream;
	private static int c = 0;

	private String initializerOFProperty(String configKeyName) {
		try {
			Properties prop = new Properties();
			String propFileName = "\\src\\test\\resources\\config.properties";
			//            inputStream = getClass().getClassLoader().getResourceAsStream(propFileName);
			inputStream = new FileInputStream(System.getProperty("user.dir")+ propFileName);
			if (inputStream != null) {
				prop.load(inputStream);
			} else {
				throw new FileNotFoundException("property file '" + propFileName + "' not found in the classpath");
			}
			Date time = new Date(System.currentTimeMillis());
			// get the property value and print it out

			result = prop.getProperty(configKeyName);
			System.out.println(result + " from config.properties");
		} catch (Exception e) {
			System.out.println("Exception: " + e);
		} finally {
			assert inputStream != null;
			try {
				inputStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return result;
	}


	public String getUserId() throws IOException {

		return initializerOFProperty("username");


	}


	public String getPasswordValue() throws IOException {

		return initializerOFProperty("password");
	}


	public String getUrlValue() throws IOException {

		return initializerOFProperty("URL");
	}

	public String getProductName() {
		return initializerOFProperty("product");
	}

	public String getTshirtName() throws IOException {
		System.out.println(c);
		c++;
		return initializerOFProperty("tshirtname");
	}

	public String getBrowserName() {
		String s="";
		System.out.println("getBrowserName 1");
		try {
			s= initializerOFProperty("browser");
			System.out.println("getBrowserName 2");
		} catch (Exception e) {

		}
		return s;
	}
	public static void takesnapshot(WebDriver webdriver, String filepath) throws Exception
	{
		TakesScreenshot scrShot = ((TakesScreenshot)webdriver);
		File SrcFile = scrShot.getScreenshotAs(OutputType.FILE);
		File Destn = new File(filepath);
		FileUtils.copyFileToDirectory(SrcFile, Destn);
	}
}
