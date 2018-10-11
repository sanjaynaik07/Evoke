package com.RLH.StepDefinitions;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;

import com.cucumber.listener.Reporter;

import cucumber.api.DataTable;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class BookingStepDefinitions {
	
	WebDriver driver;
	
	@Given("^Navigate to the application$")
	public void Navigate_to_the_application()
	{
		try
		{
		System.setProperty("webdriver.chrome.driver","C:\\Users\\sdhanavath\\Downloads\\chromedriver.exe");
		 driver = new ChromeDriver();
		 driver.manage().window().maximize();
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.get("https://prod.redlion.com");
		 System.out.println("Navigated to application successfully");
		 Reporter.addStepLog("Navigated to application successfully");
		}catch(Exception e)
		{
			System.out.println("Navigation to Application failed");
			e.getMessage();
		}
		 
	}
	
	@Then("^Validate the application title$")
	public void Validate_the_title()
	{
		try
		{
			
		String expected_title = "Affordable Hotels | Red Lion Hotels";
		String title = driver.getTitle();
		String s=title;
		
		System.out.println(title);
		
		if(s.contains("Affordable Hotels | Red Lion Hotels")) {
			System.out.println("Print Fasak");
		}
	
		}catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	@Then("^select the location$")
	public void select_the_location(DataTable location) throws InterruptedException
	{
		try
		{
			
		
		// driver.findElement(By.xpath("//input[@id='globalSearchInput']")).sendKeys("Red Lion Hotel Port");
		
		for (java.util.Map<String, String> loc : location.asMaps(String.class, String.class)) 
		{
			driver.findElement(By.xpath("//input[@id='globalSearchInput']")).sendKeys(loc.get("Location"));
			
		}
		Thread.sleep(5000);
		
		Actions act = new Actions (driver);
		act.moveToElement(driver.findElement(By.xpath("//input[@id='globalSearchInput']"))).click().perform();
		
		
		List<WebElement> lst = driver.findElements(By.xpath("//div[@class='typeahead__dropdownlist_results']/ul/li"));
		System.out.println("size is " + lst.size());

		for(int i=0; i<lst.size(); i++)
		{
			System.out.println(lst.get(i).getText());
			String str = lst.get(i).getText();
			if(str.equals("Boston, NY"))
			{
				lst.get(i).click();
				break;
			}
		}
		System.out.println("location selected successfully");
		
		}catch(Exception e)
		{
			System.out.println("Location is not selected");
			e.getMessage();
		}
	}
	
	@Then("^select the check-in and check-out dates$")
	public void select_the_checkin_and_checkout_dates() throws InterruptedException
	{
		try
		{
        driver.findElement(By.xpath("//input[@id='searchStartDate']")).click();
		
		String Expctd_month = "January";
		System.out.println("expected month is " + Expctd_month);
		
		String Current_month = driver.findElement(By.xpath("//div[@id='searchCalendar']/header/span[@class='PAHmonth']")).getText();
		System.out.println("current month is " + Current_month);
		if(Expctd_month.equals(Current_month))
		{
			System.out.println("month is selected");
		}
		
		else
		{
			for(int i=1; i<=12; i++)
			{
				driver.findElement(By.xpath("//div[@id='searchCalendar']/header/span[@class='PAHnext']")).click();
				Thread.sleep(3000);
				Current_month = driver.findElement(By.xpath("//div[@id='searchCalendar']/header/span[@class='PAHmonth']")).getText();
				if(Expctd_month.equals(Current_month))
				{
					System.out.println("month is selected");
					break;
				}	
			}
		}
		
		List <WebElement> dates = driver.findElements(By.xpath("//div[@id='searchCalendar']/section[2]//span"));
		
		for(WebElement WB : dates)
		{
			String date = WB.getAttribute("data-value");
			String expected_date = "20190119";
			
			if(date.equals(expected_date))
			{
				WB.click();
				break;		
			}				
		}
		
		for(WebElement WB : dates)
		{
			String date = WB.getAttribute("data-value");
			String expected_date = "20190119";
			if(date.equals(expected_date))
			{
				WB.click();
				break;	
			}				
		}
		System.out.println("check-in and check-out dates are selected");
		}catch(Exception e)
		{
			System.out.println("check-in and check-out dates are not selected");
		}
		
	}
	
	@Then("^click on Check Availability link$")
	public void click_on_Check_Availability_link()
 	{
		try
		{
        driver.findElement(By.xpath("//button[text()='Check Availability']")).click();
        System.out.println("check availability link is clicked");
		}catch(Exception e)
		{
			System.out.println("check availability link is not clicked");
			e.getMessage();
		}
	}
	

	@Then("^select the room type$")
	public void select_the_room_type()
 	{
		try
		{
			driver.findElement(By.xpath("//div[@class='filter-option']/div/div[text()='Select a Room Type']")).click();
			driver.findElement(By.xpath("//div[@class='dropdown-menu show']/div/ul/li[2]")).click();
			System.out.println("room type is selected");
		}catch(Exception e)
		{
			System.out.println("Room type is not selected");
			e.getMessage();
		}
	}
	
	@Then("^click on quick book link$")
	public void click_on_quick_book_link()
	{
		try{
			
        driver.findElement(By.xpath("//button[text()='Quick Book']")).click();
        System.out.println("quickbook link is clicked");
        
		}catch(Exception e)
		{
			System.out.println("quickbook link is not clicked");
			e.getMessage();
		}   
	}
	
	@Then("^validate the quick book URL$")
	public void validate_the_quickbook_URL()
	{
		try
		{
		String actual_url = driver.getCurrentUrl();
        String expected_url = "https://prod.redlion.com/checkout";
        if(actual_url.contains(expected_url))
        {
        	Reporter.addStepLog("Quick book URL Passed");
        }
		}catch(Exception e)
		{
			e.getMessage();
		}
	}
	
	@Then("^enter customer details$")
	public void enter_customer_details(DataTable customerdeails) 
	{

		try
		{
           for (java.util.Map<String, String> det : customerdeails.asMaps(String.class, String.class)) 
	          {
		        driver.findElement(By.xpath(" //input[@id='checkout-form-first-name']")).sendKeys(det.get("first name"));
		        driver.findElement(By.xpath("//input[@id='checkout-form-last-name']")).sendKeys(det.get("last name"));
		        driver.findElement(By.xpath("//input[@id='checkout-form-email-address']")).sendKeys(det.get("email"));
		        driver.findElement(By.xpath("//input[@id='checkout-form-phone-number']")).sendKeys(det.get("contact"));
		        driver.findElement(By.xpath("//input[@id='checkout-form-zip-code']")).sendKeys(det.get("postal code"));
		
	           }
           System.out.println("customer details are entered successfully");
	    }catch(Exception e)
		{
	    	System.out.println("customer details are not entered successfully");
	    	e.getMessage();
		}
	}
	
	@Then("^enter payment details$")
	public void enter_payment_details(DataTable paymentdetails) 
	{

		try
		{
           for (java.util.Map<String, String> paymnt : paymentdetails.asMaps(String.class, String.class)) 
	          {
		        driver.findElement(By.xpath(" //input[@id='creditNumber']")).sendKeys(paymnt.get("credit card numbe"));
		        driver.findElement(By.xpath("//input[@id='fullYear']")).sendKeys(paymnt.get("Expiration Date"));
		        driver.findElement(By.xpath("//input[@id='cvc']")).sendKeys(paymnt.get("cvv"));
		        driver.findElement(By.xpath("//input[@id='postalCode']")).sendKeys(paymnt.get("postal code"));
	           }
           System.out.println("payment details are entered successfully");
	    }catch(Exception e)
		{
	    	System.out.println("payment details are not entered successfully");
	    	e.getMessage();
		}
	}
	
	@Then("^click on accept payment checkbox$")
	public void click_on_accept_payment_checkbox()
	{
		try
		{
			
        driver.findElement(By.xpath("//input[@id='checkout-payment-agreement']")).click();
        System.out.println("check box is selected successfully");
        
		}catch(Exception e)
		{
			System.out.println("check box is not selected");
			e.getMessage();
		}   
	}
	
	@Then("^click on reserve room link$")
	public void click_on_resrve_room_link()
	{
		try
		{
			
        driver.findElement(By.xpath("//button[@id='btnReserveRoom']")).click();
        System.out.println("reserve room link is clicked successfully");
        
		}catch(Exception e)
		{
			System.out.println("reserve room link is clicked successfully");
			e.getMessage();
		}   
	}

}
