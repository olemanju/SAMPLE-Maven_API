package com.wip.testwip;

import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxProfile;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Downloadfile
{
	WebDriver driver;
	WebElement element;
	
	By download_txt= By.xpath("//li/b[text()='Download:']");
	By smilechart_xpath=By.xpath("//li/a['smilechart.xls']");
	By smileChart_xpath2=By.xpath("//a[text()='smilechart.xls']");
	
	@SuppressWarnings("deprecation")
	@BeforeTest
	public void startup()
	{
		
		try {
			String downloadPath="C://Manju//PI";
			System.setProperty("webdriver.gecko.driver", "C:/Softwares/geckodriver.exe");	
		
			
			driver= new FirefoxDriver();
			driver.get("http://spreadsheetpage.com/index.php/file/C35/P10/");
			//driver.get("https://www.facebook.com/");
			driver.manage().window().maximize();
			driver.manage().timeouts().implicitlyWait(20, TimeUnit.SECONDS);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	@AfterTest
	public void teardown()
	{
		driver.quit();
	}
	
	@Test
	public void getDetailsOfTheLink()
	{
		try {
			boolean flag=false;
			
			flag=driver.findElement(download_txt).isDisplayed();
			
			if(flag=false)
			{
				Assert.assertTrue(flag,"Download Text is missing");
			}
			
			String title= driver.getTitle();
			
			flag=isElementIsPresent(smileChart_xpath2);
			
		} catch (Exception e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public  boolean isElementIsPresent(By elem)
	{
		
		
		try {
			boolean check=false;
			check= driver.findElement(elem).isDisplayed();
			Assert.assertTrue(check, elem + "  is not displaying on the Page. " );
				return true;
		} catch (NoSuchElementException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
			return false;
		}
	
	}

}
