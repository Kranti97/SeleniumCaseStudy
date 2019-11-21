package Scenario1;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

import org.openqa.selenium.OutputType;
import Selenium.CaseStudy1.UtilityClass;
import org.openqa.selenium.TakesScreenshot;
import java.util.concurrent.TimeUnit;
import org.testng.ITestResult;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.AfterMethod;

public class Registration {
   ExtentReports extent;
   ExtentTest logger;
   WebDriver driver;
   Actions act;
	
	@BeforeTest
	public void beforeTest()
	{
		driver = UtilityClass.openBrowser("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		extent = new ExtentReports(System.getProperty("user.dir") + "/test-output/Reports.html", true);
		extent.addSystemInfo("Host Name", "TestMe");
		extent.addSystemInfo("Environment", "Selenium Testing");
		extent.addSystemInfo("User Name", "Kranti Asabe");
	}
	
	public static String getScreenshot(WebDriver driver, String screenshotName) throws Exception {
		String dateName = new SimpleDateFormat("yyyyMMddhhmmss").format(new Date());
		TakesScreenshot ts = (TakesScreenshot) driver;
		File source = ts.getScreenshotAs(OutputType.FILE);
		String destination = System.getProperty("user.dir") + "/Screenshot/" + screenshotName + dateName
				+ ".png";
		File finalDestination = new File(destination);
		FileUtils.copyFile(source, finalDestination);
		return destination;
	}
	
      @Test(priority = 1)
      public void SignUp() {
    	 logger = extent.startTest("SignUp");
	  driver.findElement(By.linkText("SignUp")).click();
	  driver.findElement(By.name("userName")).sendKeys("ChinmayGutal");
	  driver.findElement(By.name("firstName")).sendKeys("Chinmay");
	  driver.findElement(By.name("lastName")).sendKeys("Gutal");
	  driver.findElement(By.name("password")).sendKeys("Kranti2519");
	  driver.findElement(By.name("confirmPassword")).sendKeys("Kranti2519");
	  driver.findElement(By.xpath("//input[@value='Male' and @type='radio']")).click();
	  driver.findElement(By.name("emailAddress")).sendKeys("krantiasabe80@gmail.com");
	  driver.findElement(By.name("mobileNumber")).sendKeys("9284980596");
	  driver.findElement(By.name("dob")).sendKeys("06/25/1997");
	  driver.findElement(By.name("address")).sendKeys("Pune");
	  Select sel = new Select(driver.findElement(By.name("securityQuestion")));
		sel.selectByIndex(2);
	  driver.findElement(By.name("answer")).sendKeys("Pink");
	  driver.findElement(By.xpath("//input[@name='Submit']")).click();
	  Assert.assertEquals("Login",driver.getTitle());
	  logger.log(LogStatus.PASS, "SignUp has Passed !!");
  }
  
      @Test(priority = 2)
      public void Signin() {
    	  logger = extent.startTest("Signin");
    	  driver.findElement(By.name("userName")).sendKeys("Lalitha");
    	  driver.findElement(By.name("password")).sendKeys("password123");
    	  driver.findElement(By.name("Login")).click();
    	  Assert.assertEquals("Home",driver.getTitle());
    	  logger.log(LogStatus.PASS, "Login has Passed !!");
    	
    	  
      }
      
      @Test(priority = 3)
	  public void productcart() {
    	  logger = extent.startTest("productcart");
		  act = new Actions(driver);
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  act.moveToElement(driver.findElement(By.linkText("All Categories"))).perform();
		  act.moveToElement(driver.findElement(By.linkText("Electronics"))).click().perform();
		  act.moveToElement(driver.findElement(By.linkText("Head Phone"))).click().build().perform();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.findElement(By.linkText("Add to cart")).click();
		  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		  driver.findElement(By.partialLinkText("Cart")).click();
		  Assert.assertEquals("View Cart",driver.getTitle());
		  logger.log(LogStatus.PASS, "Product added to cart");
		  
      }
      
      @Test(priority = 4)
  	public void payment()
  	{
    	  logger = extent.startTest("payment");
    	  driver.findElement(By.linkText("Checkout")).click();
  		driver.findElement(By.xpath("//input[@value='Proceed to Pay']")).click();
  		driver.findElement(By.xpath("//label[text()='Andhra Bank']")).click();
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		driver.findElement(By.xpath("//a[@id='btn' and @href='#']")).click();
  		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
  		driver.findElement(By.name("username")).sendKeys("123456");
  		driver.findElement(By.name("password")).sendKeys("Pass@456");
  		driver.findElement(By.xpath("//input[@value='LOGIN']")).click();
  		driver.findElement(By.name("transpwd")).sendKeys("Trans@456");
  		driver.findElement(By.xpath("//input[@value='PayNow']")).click();
  		Assert.assertEquals("Order Details",driver.getTitle());
  		driver.findElement(By.linkText("SignOut")).click();
  		logger.log(LogStatus.PASS, "Payment successfull");
  	}
  	
      @AfterMethod
  	public void getResult(ITestResult result) throws Exception {
  		if (result.getStatus() == ITestResult.SUCCESS) {
  			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getName());
  			logger.log(LogStatus.PASS, "Test Case Passed is " + result.getThrowable());
  			String screenshotPath = Registration.getScreenshot(driver, result.getName());
  			logger.log(LogStatus.PASS, logger.addScreenCapture(screenshotPath));
  		} 
  		extent.endTest(logger);
  		
  	}
          
  @AfterTest
	
	public void afterTest()
	{
		driver.close();
		extent.close();
	}
}
