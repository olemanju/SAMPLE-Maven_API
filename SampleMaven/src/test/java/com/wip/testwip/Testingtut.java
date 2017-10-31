package com.wip.testwip;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoAlertPresentException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;



public class Testingtut
{
	
	WebDriver driver;
	
	By login_lbl_xpath=By.xpath("//h3[contains(text(),'LogIn')]");
	By exampleLogin_lbl_xpah=By.xpath("(//h1)[2]");
	By userid_txt_xpath=By.xpath("//input[@name='userid']");
	By fname_txt_xpath=By.xpath("//input[@name='fname']");
	By lname_txt_xpath=By.xpath("//input[@name='lname']");
	By pwsd_txt_xpath=By.xpath("//input[@name='pswrd']");
	By login_btn_xpath=By.xpath("//input[@value='Login']");
	By cancel_btn_xpath=By.xpath("//input[@value='Cancel']");
	
	By cart_drp_id=By.id("Carlist");
	

	boolean flag=false;
	
	@BeforeTest
	public void startup()
	{
					
		try {
			String URL="http://only-testing-blog.blogspot.in/2014/05/login.html";
			System.setProperty("webdriver.gecko.driver", "C:/Softwares/geckodriver.exe");	
			
			driver= new FirefoxDriver();
			driver.get(URL);
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
		driver.close();
	}
	
	@Test(enabled=false)
	public void testCheckBoxes() 
	{
		
		String Title=null;
		Title=getTitle();
		System.out.println(Title);
	}
	@Test(enabled=false)
	public void checkElementsPresent()
	{
		flag=isElementPresent(login_lbl_xpath);
		if(flag=true)
		{
			Assert.assertTrue(flag,"Login label is Present");
		}
		else
		{
		Assert.assertTrue(flag,"Login label is Missing");
		}
		
	}
	@Test(enabled=false)
	public void checkLoginFunction()
	{
		
		flag= isElementPresent(userid_txt_xpath);
		enterTextbox(userid_txt_xpath, "olemanju");
		enterTextbox(pwsd_txt_xpath, "test123");
		enterTextbox(fname_txt_xpath, "manjuntha");
		enterTextbox(lname_txt_xpath,"Wipro");
		clcikonButton(login_btn_xpath);	
		String val=handleAlertsandgetText();
		System.out.println("alert text value is : " + val);
	}
	
	@Test(enabled=false)
	public void getDropdown()
	{
		//http://only-testing-blog.blogspot.in/2014/01/textbox.html
			String url= driver.getCurrentUrl();
			
			String s=url.substring(0, 37);
			String new_url= s+"2014/01/textbox.html";
			driver.get(new_url);
		handledropdown(cart_drp_id, "Toyota");
	}
	public String getTitle()
	{
		
		try 
		{
			String company_Title= driver.getTitle();
			return company_Title;
		} catch (NoSuchElementException e) 
		{
			System.out.println(e.getLocalizedMessage());
			
		}
		return null;
		
	}
	
		public boolean isElementPresent(By by) 
		{
			
	        try {
	            driver.findElement(by);
	              return true;
	        } 
	        catch (NoSuchElementException e)
	        {
	            return false;
	        }
	    }
		
		public void enterTextbox(By by, String textval)
		{
			try {
				WebElement textbox= driver.findElement(by);
				Assert.assertTrue(textbox.isDisplayed(),"Text box is missing");
				textbox.sendKeys(Keys.CONTROL+"a");
				textbox.sendKeys(Keys.DELETE);
				try
				{
					Thread.sleep(1000);
				}
				catch(Exception e)
				{
					e.getMessage();
				}
				
				textbox.sendKeys(textval);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		public void clcikonButton(By by)
		{
		driver.findElement(by).click();
		try {
			Thread.sleep(3000);
		} catch (InterruptedException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
			
		}
		
		public void handleAlerts()
		{
			
			try 
			{
				WebDriverWait wait= new WebDriverWait(driver, 60);
				
				boolean isalertPresent=false;
				
if(wait.until(ExpectedConditions.alertIsPresent())==null)
{
				isalertPresent=false;
}
else
{
				isalertPresent=true;
}
if(isalertPresent)
{
				Alert alert= driver.switchTo().alert();
				alert.accept();
}
			} catch (NoAlertPresentException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		
		}
		
		public String handleAlertsandgetText()
		{
			String alerttext=null;
			try 
			{
				WebDriverWait wait= new WebDriverWait(driver, 60);
				
				boolean isalertPresent=false;
				
if(wait.until(ExpectedConditions.alertIsPresent())==null)
{
				isalertPresent=false;
}
else
{
				isalertPresent=true;
}
if(isalertPresent)
{
				Alert alert= driver.switchTo().alert();
				 alerttext= alert.getText();
				alert.accept();
				
}
			} catch (NoAlertPresentException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
				
			}
		
			return alerttext;
		}
		
		public void handledropdown(By bye, String valtoSelect)
		{
			
			WebElement element= driver.findElement(bye);
			
			Select select= new Select(element);
			List<WebElement> drop_options= select.getOptions();
			
			for (WebElement webdrop : drop_options)
			{
				if(webdrop.getText().equals(valtoSelect))
				{
					webdrop.click();
					try 
					{
						Thread.sleep(3000);
					} catch (InterruptedException e) 
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					break;
				}
			}
			
			
		}
		
	}


