package FirefoxLoginSearchATC;

import org.openqa.selenium.By;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Date;
import java.util.Properties;

public interface XpathResources {
	//POM
	ConfigResource obj=new ConfigResource();
//    String tshirtName = "'Solid Men Mandarin Collar Blue, Maroon T-Shirt'";
    String tshirtName = "'APPLE 2020 Macbook Air M1 - (8 GB/256 GB SSD/Mac OS Big Sur) MGND3HN/A'";
    String productString=obj.getProductName();
    //=====================  FLIPKART SECTION ===================================
    //String popUpUserID = "//form[@autocomplete=\"on\"]//input[@type=\"text\"]";
    String popUpUserID = "//form[@autocomplete='on']//input[@type='text']";
    String popUpPassword = "//input[@type='password']";
    String requestOtp = "//button[contains(text(),'Request OTP')]";
    String otpPos = "//input[@type='text']";
    String popUpLoginButton = "//button[@type='submit']/child::span";
    String popupRequestOTPBtn = "//button[@class='_2KpZ6l _2HKlqd _3AWRsL']";
    String popUpWindow = "//div[@class='_3Njdz7']";
    String search = "//input[@name='q']";
    String searchClick = "//button[@type='submit']";
    String oneTshirt = String.format("//a[contains(text(),%s)]", tshirtName);
    By productXpath= By.xpath(String.format("//input[@value='%s']",productString));
    String tshirtAll = "//div[@class='_1HmYoV _35HD7C']//div[contains(@data-id,'TSH')]//a";


    String userIdValue= "userid";
    String pwdValue="password";
//    String userIdValue= "9625189455";
//    String pwdValue="Priya@123";
    String tShirtName = "Striped Men Round Neck White T-Shirt";


  String msgField="//div[@data-tab='6']";
  String send="//span[@data-icon='send']";
  //===================== Test Cases =====================
    void loginFunctionality();
    void searchItemFunctionality();
    void itemSelectionFunctionality();
    void purchaseFunctionality();
    void enterPin();
}
