import java.util.List;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class BootsTesting {

	WebDriver driver = new ChromeDriver();
	String URL = "https://www.boots.com/"; 
	Random rand = new Random(); 
	@BeforeTest 
	public void MySetup() throws InterruptedException {
		
		driver.get(URL);
		driver.manage().window().maximize();
		Thread.sleep(2000);
		driver.findElement(By.id("onetrust-accept-btn-handler")).click(); 
	}
	

	
	@Test(priority = 1 , enabled = true)
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
	
	
	@Test (priority = 2 , enabled = true )
	public void VerifiyProductListIsLoaded() {
		
		String ProductShowenText =driver.findElement(By.cssSelector(".oct-listers__pagination.oct-listers__pagination-top.oct-listers__pagination-top__no-chanel")).getText(); 
		Boolean ExpectedResult = true; 
		Boolean ActualResult = ProductShowenText.contains("Showing");  
		
		Assert.assertEquals(ActualResult, ExpectedResult); 
	
	}
	
	@Test (priority = 3 , enabled = true) 
	public void ViewingProductDetailes() {
		
		List<WebElement> ProductList = driver.findElement(By.id("hits")).findElements(By.cssSelector(".oct-grid__cell.oct-grid__cell--width-3.oct-grid__cell--small-6.oct-grid__cell--medium-small-4.oct-grid__cell--medium-4.oct-grid__cell--medium-large-4.oct-listers-hits__item.hit.oct-listers__item--with-inGrid\r\n"
				+ ""));
		int RandomProductIndex = rand.nextInt(ProductList.size()); 
		ProductList.get(RandomProductIndex).findElement(By.tagName("a")).click(); 

	}
	

	
}