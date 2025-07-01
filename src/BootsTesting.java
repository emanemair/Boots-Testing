import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.StaleElementReferenceException;
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

	String DiscountCode = "SAVE20"; 
	
	double PPrice ; 
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
	
	@Test(priority = 1 , enabled = true)
	public void Test001_VerifyHomepageLoad() {
		
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
	


	@Test(priority = 2  , enabled = true)
	public void Test002_CheckAiAssistantFunctionality() throws InterruptedException {
		
		String ChatText = "What is the average delivery time?"; 
	
		WebElement AI_AssistantButton = driver.findElement(By.className("ATChatOpen")); 
		AI_AssistantButton.click(); 
		WebElement ChatInput = driver.findElement(By.id("chatBoxTextEntry")); 
		ChatInput.sendKeys(ChatText); 
		driver.findElement(By.xpath("//*[@id=\"page\"]/div[8]/div[2]/div/div[2]/div/button")).click(); 
		Thread.sleep(5000); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));

		WebElement messageElement = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.cssSelector(".message.messageReceived.feedbackEnabled")
		    )) ; 
		String AI_Feedback = messageElement.getText(); 
		boolean ActualResult = AI_Feedback.contains("delivery"); 
		boolean ExpectedAI_result = true; 
		driver.findElement(By.className("modalHeaderActionGroup")).findElement(By.tagName("button")).click(); 
		Assert.assertEquals(ActualResult, ExpectedAI_result);
		
	}
	
	@Test(priority= 2 , enabled = false) 
	public void Test002_Registration() throws InterruptedException {
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
		EmailInput.sendKeys("faisale912@gmail.com"); 
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
		
		//captcha and create account will be a manual autoamtion 
		
		driver.findElement(By.xpath("//input[@value='Create my account']")).click(); 
		
        WebElement SelectCountryDropDown = driver.findElement(By.className("gig-tfa-phone-register-select"));

		Select SelectCountryList = new Select(SelectCountryDropDown); 
		
		SelectCountryList.selectByContainsVisibleText("Jordan"); 
		
		driver.findElement(By.className("gig-tfa-phone-number")).sendKeys("0799646707"); 
		driver.findElement(By.id("gigyaRegisterDiv_showTfaUI_0_wrapper-get-voice-code")).click();
		
		
	}
	

	@Test(priority=  3,enabled = true)
	public void Test003_ChooseProductCate() throws InterruptedException {

			driver.findElement(By.className("dismiss")).click(); 
			Thread.sleep(1000);
			driver.findElement(By.className("dismiss")).click(); 

		
		    List<WebElement> ProductCategories = driver.findElements(By.cssSelector(
		        ".swiper-slide.oct-carousel-teaser-swiper-slide.swiper-slide-next"
		    ));
		    
		    
		    int RandomProductIndex = 0 ; 
//oct-teaser oct-teaser--theme-productTile oct-teaser--theme-productTile--border-callout oct-teaser--border
		  //  int RandomProductIndex = rand.nextInt(2,ProductCategories.size()); 
		
		    //rand.nextInt(1, ProductCategories.size()-1);
		    System.out.println(RandomProductIndex);

			
		    // Scroll and click with retry if stale element exception occurs
		    boolean success = false;
		    int attempts = 0;
		    String ProductDesc= "beauty"; 

		    while (!success && attempts < 3) {
		        try {
		            // Re-locate the list each time to avoid stale elements
		            ProductCategories = driver.findElements(By.cssSelector(
		                ".oct-teaser.oct-teaser--theme-productTile.oct-teaser--theme-productTile--border-callout.oct-teaser--border"
		            ));

		            WebElement selectedProduct = ProductCategories.get(RandomProductIndex);
		            // Scroll into view
		            ((JavascriptExecutor) driver).executeScript(
		                "arguments[0].scrollIntoView({block: 'center'});", selectedProduct
		            );
		            Thread.sleep(1000);

		            selectedProduct.findElement(By.tagName("a")).click();
		            success = true;

		        } catch (StaleElementReferenceException e) {
		            attempts++;
		            Thread.sleep(500); // short wait before retry
		        }
		    }

		    if (!success) {
		        throw new RuntimeException("Failed to click product category after multiple attempts due to stale element.");
		    }
		    boolean Test003_Expected_Result= true; 
		    WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
		    WebElement navItem = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(
		            By.className("oct-breadcrumb__item"))).get(2);
		    String Nav2 = navItem.getText().toLowerCase();
		    boolean Actual_Result = Nav2.contains(ProductDesc.toLowerCase()); 
		    Assert.assertEquals(Actual_Result, Test003_Expected_Result);
		}
	
	
	@Test (priority = 4 , enabled = true)
	public void Test004_VerifiyProductListIsLoaded() throws InterruptedException {
		
		Thread.sleep(2000); 
		
		String ProductShowenText =driver.findElement(By.cssSelector(".oct-listers__pagination.oct-listers__pagination-top.oct-listers__pagination-top__no-chanel")).getText(); 
		Boolean Test004_ExpectedResult = true; 
		Boolean ActualResult = ProductShowenText.contains("Showing");  
		
		Assert.assertEquals(ActualResult, Test004_ExpectedResult); 
	
	}
	
	@Test(priority = 5 , enabled = true)
	public void Test005_FilterFunctionality() throws InterruptedException  {
		
		
		driver.findElement(By.cssSelector(".oct-toggle__slider.oct-toggle__slider--unselected")).click();

		boolean hasFilter = driver.findElements(
		        By.cssSelector(".oct-button.oct-button--default.oct-button--default-default.oct-button--"
		        		+ "default-default-responsive.applied-filters__clear-all")).size() > 0;


		if (hasFilter) {
		    WebElement clearAllFiltersBtn = driver.findElement(
		            By.cssSelector(".oct-button.oct-button--default.oct-button--default-default.oct-button--default-default-responsive.applied-filters__clear-all"));
		    clearAllFiltersBtn.click();
		}

		WebElement filterSection = driver.findElement(By.cssSelector(".oct-listers-facets"));
		WebElement brandSection = filterSection.findElement(By.cssSelector(".oct-listers-facets__item.oct-listers-facets__item--brand"));
		
		
	


		String brandName ="Clinique"; 

		    WebElement searchBrandInput = driver.findElement(By.xpath("//input[@title='Search Brand']"));
		    searchBrandInput.sendKeys(brandName);
		    Thread.sleep(2000);

		    List<WebElement> brandsAvailable = brandSection
		            .findElement(By.cssSelector(".oct-accordion__pannel.oct-accordion__pannel--open"))
		            .findElements(By.cssSelector(".checkbox-list-facet__child.facet__child"));

		    brandsAvailable.get(0)
		            .findElement(By.className("oct-checkbox__input")).click();



		String InStockFilter = "inStock=true".toLowerCase();
		String BrandFilter = ("brand="+brandName).toLowerCase(); 
		boolean Test005_ExpectResult_test = true;
		Thread.sleep(2000); 
		String CurrentUrl = driver.getCurrentUrl().toLowerCase();
		boolean ActualResult_test4 = CurrentUrl.contains(InStockFilter) && CurrentUrl.contains(BrandFilter);
		System.out.println(CurrentUrl); 
		System.out.println(BrandFilter + "   " + InStockFilter ) ; 
		
		Assert.assertEquals(ActualResult_test4, Test005_ExpectResult_test);

		


		} 	
	
	@Test(priority =  6, enabled = true)
	public void Test006_ChooseRandomProduct() throws InterruptedException {
		Thread.sleep(2000);
/*
		List<WebElement> ProductLinks = driver.findElements(
				By.cssSelector(".oct-link.oct-link--theme-text.oct-color--boots-blue.oct-teaser__title-link"));

		int RandomProductIndex = rand.nextInt(ProductLinks.size());*/
		/*
		String ProductName = ProductLinks.get(RandomProductIndex).getText();
		ProductLinks.get(RandomProductIndex).click(); */
		String ProductName = driver.findElement(By.linkText("Clinique All About Eyes™ Eye Cream 15ml")).getText(); 
		 driver.findElement(By.linkText("Clinique All About Eyes™ Eye Cream 15ml")).click();
		Thread.sleep(1000);	
		String SelectedPName = driver.findElement(By.id("estore_pdp_trcol")).findElement(By.tagName("h1")).getText();
		boolean Tets006_ExpectedResultTest= true; 
		boolean ActualResultTest = ProductName.trim().equalsIgnoreCase(SelectedPName.trim());
		Assert.assertEquals(ActualResultTest, Tets006_ExpectedResultTest);
	}
	

	
	@Test (priority = 7 , enabled = true)
	public void Test007_ViewingProductDetailes() throws InterruptedException {

		Thread.sleep(2000); 
		WebElement ProductDetailes = driver.findElement(By.id("estore_pdp_trcol")); 
        List<Boolean> boolList = new ArrayList<>();
        WebElement ProductName =  ProductDetailes.findElement(By.tagName("h1")); 
        WebElement ProductPrice = ProductDetailes.findElement(By.id("PDP_productPrice")); 
        WebElement ProductReviews = ProductDetailes.findElement
        		(By.cssSelector(".bv_main_container_row_flex.bv_ratings_summary.bv_main_rating_button")); 
        
        PPrice = Double.parseDouble(ProductPrice.getText().trim().replaceAll("[^\\d.]", "")); 
        WebElement ProductCode = ProductDetailes.findElement(By.cssSelector(".productid.productid_redesign")); 
        boolList.add(ProductName.isDisplayed()); 
        boolList.add(ProductPrice.isDisplayed()) ; 
        boolList.add(ProductReviews.isDisplayed()); 
        boolList.add(ProductCode.isDisplayed()); 
        int DispalyingCount = 0 ; 
        for (int i = 0 ; i < boolList.size() ; i++ ) {
        	if(boolList.get(i)!= true ) 
        		break; 
        	else 
        		DispalyingCount = DispalyingCount + 1 ; 
        }
        
       Boolean Test007_ExpectedResult = true; 
       Boolean ActualResult = false; 
       if (DispalyingCount == 4) {
    	   ActualResult = true; 
       }
		
       Assert.assertEquals(ActualResult, Test007_ExpectedResult); 

	}
	
	@Test(priority = 8 , enabled = true)
	public void Test008_AddItemToTheCart () throws InterruptedException {
		Thread.sleep(1000);

		String ProductCNameToBeAdded = driver.findElement(By.id("estore_pdp_trcol"))
		                                     .findElement(By.tagName("h1"))
		                                     .getText(); 
		boolean ChooseColorSectionIsExist = driver.findElements(By.className("sizeComboButton_custom_dropdown")).size()>0; 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		if (ChooseColorSectionIsExist) {
		    WebElement colorSection = wait.until(ExpectedConditions.elementToBeClickable(By.className("sizeComboButton_custom_dropdown")));
		    colorSection.click();
		    
		    WebElement dropdownList = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("sizeComboButton_dropdown")));
		    List<WebElement> sizeOptions = dropdownList.findElements(By.tagName("li"));
		    
		    if (!sizeOptions.isEmpty()) {
		        wait.until(ExpectedConditions.elementToBeClickable(sizeOptions.get(1))).click();
		    } else {
		        System.out.println("No size options found in dropdown.");
		    }
		}

		WebElement addToCartBtn = null;
		if (driver.findElements(By.className("ATATBButton")).size() > 0) {
		    addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.className("ATATBButton")));
		} else if (driver.findElements(By.id("add2CartBtn")).size() > 0) {
		    addToCartBtn = wait.until(ExpectedConditions.elementToBeClickable(By.id("add2CartBtn")));
		}

		if (addToCartBtn != null && addToCartBtn.isDisplayed()) {
		    addToCartBtn.click();
		}
		Thread.sleep(2000);
		if (driver.findElements(By.xpath("//div[@data-testid='basket-notification-element']")).size() > 0 ) 
			driver.findElement(By.xpath("//button[@aria-label='close basket notification']")).click(); 
	
		WebElement BasketButton = driver.findElement(By.xpath("//button[@aria-label='Basket']"));
		BasketButton.click();

		
		Thread.sleep(3000);
		
		// Retrieve product names from basket

		// Wait for the presence of all elements
		List<WebElement> ProductName = wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(
		    By.cssSelector(".oct-link.oct-link--theme-text.oct-color--boots-blue.oct-product-details__name.oct-product-details__name__withImage")
		));
		
		List<String> ProductNames = new ArrayList<>();
		for (WebElement PElement : ProductName) {
		    ProductNames.add(PElement.getText().trim()); // trimming is useful for exact matches
		}


		System.out.println("Product Name to be Added: " + ProductCNameToBeAdded);

		// Validation
		boolean Test008_ExpectedBasketResult = true;
		boolean ActualBasketResult = ProductNames.contains(ProductCNameToBeAdded);

		Assert.assertEquals(ActualBasketResult, Test008_ExpectedBasketResult);
	}
	

	
	@Test(priority = 11, enabled = true)
	public void Test011_CheckSearchProductFunctionality() throws InterruptedException{
		
		
		Thread.sleep(2000); 
		
		driver.findElement(By.cssSelector(".oct-iconButton.oct-basket-header__close-btn")).click();
		Boolean Test011_ExpectedResul = true; 
		String ProductName = "eye cream"; 
		driver.findElement(By.id("AlgoliaSearchInput")).sendKeys(ProductName);
		driver.findElement(By.id("algolia-search-button")).click();
		Thread.sleep(2000);
		Boolean ActualResult = driver.findElement(By.cssSelector(".oct-text.oct-text--standard.oct-text--size_m.oct-aem-text.oct-aem-text--h1--variant-1\r\n"
				+ "")).getText().contains("results");
		Assert.assertEquals(ActualResult, Test011_ExpectedResul);
		
	}
	
	
	@Test(priority = 10 ,enabled = true)
	public void Test010_DicsountCode() throws InterruptedException {
	
		driver.findElement(By.id("offerCode")).sendKeys(DiscountCode);
		driver.findElement(By.xpath("//button[@aria-label='Apply Offer Code']")).click(); 
		Thread.sleep(2000);
		
		
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		WebElement alertElement = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("form p")));
		String MessageAlert = alertElement.getText();
		
		boolean Test010_ExpectedResult = true; 
		
		boolean ActualResult =  MessageAlert.contains("successfully applied"); 

		Assert.assertEquals(ActualResult, Test010_ExpectedResult); 
	 
	}
	
		

}
