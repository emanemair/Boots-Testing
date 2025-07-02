import java.time.Duration;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.BeforeTest;

public class TestData {
	
	WebDriver driver = new ChromeDriver();
	String URL = "https://www.boots.com/";
	
	Random rand = new Random(); 
	String UserPssword = "emanemair@E12345";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
  
    String DiscountCode = "SAVE20"; 
    

	
	String ChatText = "What is the average delivery time?"; 
	boolean ExpectedAI_result = true; 
    boolean Test003_Expected_Result= true; 
	Boolean Test004_ExpectedResult = true; 
	boolean Test005_ExpectResult_test = true;
	boolean Tets006_ExpectedResultTest= true; 
    Boolean Test007_ExpectedResult = true; 
	boolean Test008_ExpectedBasketResult = true;
	Boolean Test011_ExpectedResul = true; 
	boolean Test010_ExpectedResult = true; 


    String ProductLinkText = "Clinique All About Eyes™ Eye Cream 15ml"; 
    String SearchProductName = "eye cream"; 
	
	public void HandlePopUpsIfPresent() {
		
		 try {
		        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
		        WebElement popup = wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("container")));

		        // Example: Close button inside popup
		        WebElement closeButton = popup.findElement(By.id("close")); // adjust selector
		        closeButton.click();
		        System.out.println("Popup closed successfully.");

		    } catch (Exception e) {
		        System.out.println("Unexpected error while handling popup: " + e.getMessage());
		    }
	
	   
	    
	}
    

}
