package com.wip.test;

import static org.testng.Assert.assertTrue;

import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class RedBus
{
WebDriver driver;

By popupTitle= By.xpath("//div[@class='poptitle']");
By redbusIndia_btn= By.xpath("//div[@id='go']");
By header_lbl=By.xpath("//h1");
By source=By.id("src");
By destination= By.id("dest");


boolean flag=false;
String expected=null;
String actual=null;

@BeforeTest
public void setup()
{

	try
	{
		System.setProperty("webdriver.gecko.driver", "C:/Softwares/geckodriver.exe");	
		String Url="https://www.redbus.com/";
		driver= new FirefoxDriver();
		driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
		driver.manage().window().maximize();
		driver.get(Url);
	}
	catch (Exception e)
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
}

@AfterTest
public void teardown()
{
//driver.quit();	
	
driver.close();
}

@Test
public void  check_LogoandText()
{
	
String gettitle= driver.getTitle();
System.out.println(gettitle);
}

@Test
public void land_to_redbus_India() throws InterruptedException
{
	try 
	{
		flag= driver.findElement(popupTitle).isDisplayed();
		Assert.assertTrue(flag, "Popup Title is not displaying");
		
		expected= driver.findElement(popupTitle).getText();
		Assert.assertEquals("You reached redbus.com", expected,"Both the strings are not matching");
		
		driver.findElement(redbusIndia_btn).click();
		
		String ti= driver.findElement(header_lbl).getText();
		
		System.out.println(ti);
		
		String t= driver.getTitle();
		System.out.println(t);
		expected="Online Bus Tickets Booking, Book Volvo AC Bus Tickets, Confirmed Bus Reservation -redBus";
		Assert.assertEquals(t, expected,"Both Titles are not equal");
	} 
	catch (Exception e) 
	{
		// TODO Auto-generated catch block
		e.printStackTrace();
	}

}

@Test
public void detailsofSource()
{
	
	driver.findElement(redbusIndia_btn).click();
	
}


}
