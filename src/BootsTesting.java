import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BootsTesting {

	WebDriver driver = new ChromeDriver();
	String URL = "https://www.boots.com/"; 
	Random rand = new Random(); 
	String UserPssword = "emanemair@E12345";
    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));

	@BeforeTest 
	public void MySetup() throws InterruptedException {
		
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 
	}
	
	@Test(priority = 1 , enabled = false)
	public void VerifyHomepageLoad() {
		
        // Verify Nav Bar is visible
        WebElement navBar = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("topLevelMenu")));
        Assert.assertTrue(navBar.isDisplayed(), "NavBar should be visible");

        // Verify Page Banner(s) are present
        List<WebElement> pageBanners = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".oct-grid__row.oct-grid__section.oct-grid__section--full-width.oct-grid__section--background-color")
            )
        );
        Assert.assertFalse(pageBanners.isEmpty(), "Page banner(s) should be present");

        // Verify Product List is present
        List<WebElement> productList = wait.until(
            ExpectedConditions.presenceOfAllElementsLocatedBy(
                By.cssSelector(".oct-teaser.oct-teaser--theme-productTile.oct-teaser--theme-productTile--border-callout.oct-teaser--border")
            )
        );
        Assert.assertFalse(productList.isEmpty(), "Product list should be present");
	} 
	
	@Test(priority= 2 , enabled = true) 
	public void Regisstration() throws InterruptedException {
		WebElement RegistrationLink = driver.findElement(By.id("signInQuickLink")); 
		RegistrationLink.click(); 
		driver.findElement(By.xpath("//input[@value='Register']")).click(); 
		
		Thread.sleep(2000); 
		//title 
		WebElement TitleSelect = driver.findElement(By.xpath("//select[@data-display-name='Title']"));
		TitleSelect.click(); 
		Select TitleSelectList = new Select(TitleSelect); 
		Thread.sleep(2000); 

		TitleSelectList.selectByVisibleText("Miss");
		
		//first name 
		WebElement FirstNameInput = driver.findElement(By.xpath("//input[@data-display-name='First name']")); 
		FirstNameInput.sendKeys("Eman"); 
		
		//Last Name 
		
		WebElement LastNameInput = driver.findElement(By.xpath("//input[@data-display-name='Last name']"));
		LastNameInput.sendKeys("Emair"); 
		
		//Email 
		WebElement EmailInput =driver.findElement(By.xpath("//input[@data-display-name='Email address']")); 
		EmailInput.sendKeys("emanimair2@gmail.com"); 
		//Password 
		WebElement passwordField = driver.findElement(By.id("gigya-password-142081691598547540"));
		passwordField.sendKeys(UserPssword);
		
		WebElement ConfirmPass = driver.findElement(By.id("gigya-password-68140718006804320")); 
		ConfirmPass.sendKeys(UserPssword);
		
		
		// Scroll directly to the element
		JavascriptExecutor js = (JavascriptExecutor) driver;
		WebElement conditionTerms = driver.findElement(By.id("gigya-checkbox-43083977052586530"));

		// Scroll directly to the element
		js.executeScript("arguments[0].scrollIntoView({behavior: 'smooth', block: 'center'});", conditionTerms);
	
		
		
		Thread.sleep(2000);
		
		WebElement ConditionLabel = driver.findElement(By.cssSelector("label[for='gigya-checkbox-43083977052586530']"));
		ConditionLabel.click();
		
	



		
	}
	
	@Test(priority = 2 , enabled = false) 
	public void verifyProductListingPageLoad() throws InterruptedException {
		Thread.sleep(2000);

		List <WebElement> ProductCategories = driver.findElements(By.cssSelector(".oct-teaser.oct-teaser--theme-productTile.oct-teaser--theme-productTile--border-callout.oct-teaser--border")); 
		int RandomProductIndex =rand.nextInt(ProductCategories.size()); 
		
		Thread.sleep(2000);
		if(RandomProductIndex > 2 ) {
			List<WebElement> swiperList = driver.findElements(By.cssSelector(".swiper-scrollbar-drag.oct-carousel-teaser-swiper-scrollbar-drag")); 
			int slideWidth = swiperList.get(0).getSize().getWidth();
			if (RandomProductIndex <= 6 ) {
				
				
				// Create the actions object
				Thread.sleep(2000);
				Actions actions = new Actions(driver);

				// Swipe right by ~129px (positive X)
				actions.clickAndHold(swiperList.get(0))
				     	.moveByOffset(slideWidth, 0)
				       .release()
				       .perform();
			}if (RandomProductIndex > 6 ) {
				
				
				// Create the actions object
				Thread.sleep(2000);
				Actions actions = new Actions(driver);

				// Swipe right by ~129px (positive X)
				actions.clickAndHold(swiperList.get(0))
				.moveByOffset(slideWidth+slideWidth/2,0)
				       .release()
				       .perform();
			}
			
			}	
		Thread.sleep(2000); 
		ProductCategories.get(RandomProductIndex).findElement(By.tagName("a")).click();
	}
	
	
	@Test (priority = 3 , enabled = false)
	public void VerifiyProductListIsLoaded() throws InterruptedException {
		
		Thread.sleep(2000); 
		
		String ProductShowenText =driver.findElement(By.cssSelector(".oct-listers__pagination.oct-listers__pagination-top.oct-listers__pagination-top__no-chanel")).getText(); 
		Boolean ExpectedResult = true; 
		Boolean ActualResult = ProductShowenText.contains("Showing");  
		
		Assert.assertEquals(ActualResult, ExpectedResult); 
	
	}
	
	@Test (priority = 4 , enabled = false)
	public void ViewingProductDetailes() throws InterruptedException {
		
		
		List<WebElement> ProductList = driver.findElement(By.id("hits")).findElements(By.cssSelector(".oct-grid__cell.oct-grid__cell--width-3.oct-grid__cell--small-6.oct-grid__cell--medium-small-4.oct-grid__cell--medium-4.oct-grid__cell--medium-large-4.oct-listers-hits__item.hit.oct-listers__item--with-inGrid\r\n"
				+ ""));
		int RandomProductIndex = rand.nextInt(ProductList.size()); 
		ProductList.get(RandomProductIndex).findElement(By.tagName("a")).click();
		Thread.sleep(2000); 

		WebElement ProductDetailes = driver.findElement(By.id("estore_pdp_trcol_1")); 
        List<Boolean> boolList = new ArrayList<>();
        WebElement ProductName =  ProductDetailes.findElement(By.tagName("h1")); 
        WebElement ProductPrice = ProductDetailes.findElement(By.id("PDP_productPrice")); 
        WebElement ProductReviews = ProductDetailes.findElement(By.cssSelector(".bv_main_container_row_flex.bv_ratings_summary.bv_main_rating_button")); 
        boolList.add(ProductName.isDisplayed()); 
        boolList.add(ProductPrice.isDisplayed()) ; 
        boolList.add(ProductReviews.isDisplayed()); 
        int DispalyingCount = 0 ; 
        for (int i = 0 ; i < boolList.size() ; i++ ) {
        	if(boolList.get(i)!= true ) 
        		break; 
        	else 
        		DispalyingCount = DispalyingCount + 1 ; 
        }
        
       Boolean ExpectedResult = true; 
       Boolean ActualResult = false; 
       if (DispalyingCount == 3) {
    	   ActualResult = true; 
       }
		
       Assert.assertEquals(ActualResult, ExpectedResult); 

	}
	
	
	

	
}