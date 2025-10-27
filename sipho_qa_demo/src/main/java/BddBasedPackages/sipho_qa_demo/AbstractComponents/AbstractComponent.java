package BddBasedPackages.sipho_qa_demo.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.*;
import java.util.concurrent.TimeUnit;
public class AbstractComponent {
	
	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		// TODO Auto-generated constructor stub
		this.driver = driver;
	}

	public void waitForElementToBeVissible(By findBy)
	{

		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
		
	}
	
	public void waitForElementToBeClickable(By findBy)
	{
        try {
            TimeUnit.SECONDS.sleep(2);
            System.out.println("5 seconds have passed.");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        //DOTO: WebDriverWait needs further investigation as it fails to wait the required seconds
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2000));
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
		
	}
}
