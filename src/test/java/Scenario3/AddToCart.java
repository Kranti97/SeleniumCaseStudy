package Scenario3;

import java.util.concurrent.TimeUnit;

//import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Selenium.CaseStudy1.UtilityClass;

public class AddToCart {
WebDriver driver;
Actions act;
	
	@BeforeTest
	public void beforeTest()
	{
		driver = UtilityClass.openBrowser("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
		driver.findElement(By.linkText("SignIn")).click();
		driver.findElement(By.name("userName")).sendKeys("Lalitha");
  	    driver.findElement(By.name("password")).sendKeys("password123");
  	    driver.findElement(By.name("Login")).click();
	}
	
  @Test
  public void productcart() {
	  act = new Actions(driver);
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  act.moveToElement(driver.findElement(By.linkText("All Categories"))).perform();
	  act.moveToElement(driver.findElement(By.linkText("Electronics"))).click().perform();
	  act.moveToElement(driver.findElement(By.linkText("Head Phone"))).click().build().perform();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.linkText("Add to cart")).click();
	  driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
	  driver.findElement(By.partialLinkText("Cart")).click();
	  driver.findElement(By.linkText("Checkout")).click();
  }
}
