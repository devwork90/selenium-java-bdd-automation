package BddBasedPackages.sipho_qa_demo.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class LandingPage {
	
	WebDriver driver;
	public LandingPage(WebDriver driver) 
	{
		//initialization of all used webElements
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	public void gotTo() {
		driver.get("https://www.shopriteholdings.co.za/email-disclaimer.html");
	}

}
