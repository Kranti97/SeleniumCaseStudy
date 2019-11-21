package Scenario2;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import Selenium.CaseStudy1.UtilityClass;

public class Login {
WebDriver driver;
	
	@BeforeTest
	public void beforeTest()
	{
		driver = UtilityClass.openBrowser("chrome");
		driver.get("http://10.232.237.143:443/TestMeApp/fetchcat.htm");
	}
	
  @Test
  public void Signin() {
	  driver.findElement(By.linkText("SignIn")).click();
	  driver.findElement(By.name("userName")).sendKeys("Lalitha");
	  driver.findElement(By.name("password")).sendKeys("password123");
	  driver.findElement(By.name("Login")).click();
  }
  

  @AfterTest
	
	public void afterTest()
	{
		driver.close();
	}
}
