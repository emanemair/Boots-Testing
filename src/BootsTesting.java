import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
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
	
	@Test(priority= 2 , enabled = false) 
	public void Registration() throws InterruptedException {
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
		EmailInput.sendKeys("emaireman51@gmail.com"); 
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
		
		

		
	}
	

	@Test(priority=  2 ,enabled = false)
	public void ChooseRandomProductCate() throws InterruptedException {
		
		  Thread.sleep(2000);

		    List<WebElement> ProductCategories = driver.findElements(By.cssSelector(
		        ".oct-teaser.oct-teaser--theme-productTile.oct-teaser--theme-productTile--border-callout.oct-teaser--border"
		    ));

		    int RandomProductIndex = rand.nextInt(ProductCategories.size());

		    // Scroll and click with retry if stale element exception occurs
		    boolean success = false;
		    int attempts = 0;

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
		}
	/*
	@Test(priority = 2 , enabled = false) 
	public void ChooseRandomProductCategory() throws InterruptedException {
		Thread.sleep(2000);

		List <WebElement> ProductCategories = driver.findElements(By.cssSelector(".oct-teaser.oct-teaser--theme-productTile.oct-teaser--theme-productTile--border-callout.oct-teaser--border")); 
		int RandomProductIndex =rand.nextInt(ProductCategories.size()-1 ); 
		
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
	
	*/
	
	@Test (priority = 3 , enabled = false)
	public void VerifiyProductListIsLoaded() throws InterruptedException {
		
		Thread.sleep(2000); 
		
		String ProductShowenText =driver.findElement(By.cssSelector(".oct-listers__pagination.oct-listers__pagination-top.oct-listers__pagination-top__no-chanel")).getText(); 
		Boolean ExpectedResult = true; 
		Boolean ActualResult = ProductShowenText.contains("Showing");  
		
		Assert.assertEquals(ActualResult, ExpectedResult); 
	
	}
	@Test(priority = 4 , enabled = false)
	public void ChooseRandomProduct() {
	
		
		List<WebElement> ProductList = driver.findElement(By.id("hits")).findElements(By.cssSelector(".oct-grid__cell.oct-grid__cell--width-3.oct-grid__cell--small-6.oct-grid__cell--medium-small-4.oct-grid__cell--medium-4.oct-grid__cell--medium-large-4.oct-listers-hits__item.hit.oct-listers__item--with-inGrid\r\n"
				+ ""));
		int RandomProductIndex = rand.nextInt(ProductList.size()); 
		ProductList.get(RandomProductIndex).findElement(By.tagName("a")).click();
	}
	
	
	@Test(priority = 5 , enabled = false )
	public void Filter() {
		
		
		WebElement FilterSection = driver.findElement(By.cssSelector
				(".oct-listers-facets-wrapper.oct-listers__loaded"));	
		List<WebElement> FilterOptions = FilterSection.findElements(
			    By.xpath("//button[contains(@class, 'oct-accordion__toggle') and contains(@class, 'oct-accordion__toggle--open')]")
			);
		
		
		int RandomFitleringIndex  = rand.nextInt(FilterOptions.size()); 
		WebElement RandomFilterOption = FilterOptions.get(RandomFitleringIndex); 
		String NameOfFilterOption = RandomFilterOption.getDomAttribute("aria-label"); 
		System.out.println(NameOfFilterOption); 
		List<WebElement>FilterPannelList = RandomFilterOption.findElements(By.tagName("li")); 
			/*	("//div[contains(@class,'oct-accordion__pannel') and contains"
						+ "(@class,'oct-accordion__pannel--open')]"));*/
		

		
		if (NameOfFilterOption == "brand" ||NameOfFilterOption == "Promotions" ) {
			int RandomFilterPannel = rand.nextInt(FilterPannelList.size()); 

			WebElement SelctedFilter = FilterPannelList.get(RandomFilterPannel).
					findElement(By.tagName("input")) ;
			SelctedFilter.click(); 
			
		}

		
		
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
	
	@Test(priority = 5 , enabled = true)
	public void CheckSearchProductFunctionality() throws InterruptedException{
		
		Boolean ExpectedResultOfSearchingProduct = true; 
		String ProductName = "eye cream"; 
		driver.findElement(By.id("AlgoliaSearchInput")).sendKeys(ProductName);
		driver.findElement(By.id("algolia-search-button")).click();
		Thread.sleep(2000);
		Boolean ActualResult = driver.findElement(By.cssSelector(".oct-text.oct-text--standard.oct-text--size_m.oct-aem-text.oct-aem-text--h1--variant-1\r\n"
				+ "")).getText().contains("results");
		Assert.assertEquals(ActualResult, ExpectedResultOfSearchingProduct);
	}
	
	@Test(priority = 6 , enabled = true)
	public void FilterFunctionality() throws InterruptedException  {
		
		// All Filter Options 
		List<WebElement> AllFiltersOptions = driver.findElements(By.xpath
				("//div[contains(@class,'oct-accordion__item') and"
						+ " contains (@class,'oct-accordion__item--noTransition')]"));
		ArrayList<Integer> RandomFilterIndices = new ArrayList<>();
		int FilterCount = 0 ; 
		int RandomFilterIndex = rand.nextInt(AllFiltersOptions.size()); 
		while(!RandomFilterIndices.contains(RandomFilterIndex) && FilterCount < 3  ) {
			RandomFilterIndices.add(RandomFilterIndex);
			RandomFilterIndex = rand.nextInt(AllFiltersOptions.size()); 
			FilterCount++; 
		}
		for (int i = 0 ; i< RandomFilterIndices.size(); i++) {
			
			WebElement ButtonElement = AllFiltersOptions.get(RandomFilterIndices.get(i)).
					findElement(By.tagName("button"));
			String ToggleType = ButtonElement.getDomAttribute("class");
			System.out.println(ToggleType); 
		
			if (ToggleType.contains("open")) {
				
				List<WebElement> PannelsOptions = AllFiltersOptions.get(RandomFilterIndices.get(i)).
				findElement(By.tagName("div")).findElement(By.xpath("//div[contains(@class,'oct-accordion__pannel') "
						+ "and contains (@class,'oct-accordion__pannel--open')]")).findElement(By.tagName("ul")).
								findElements(By.tagName("li"));
				for (int j = 0 ; j <PannelsOptions.size(); j++ )
					System.out.println(PannelsOptions.get(j).
							findElement(By.xpath("//span[@data-testid='checkbox-label']")));
						
			}else {
				ButtonElement.click();
			}
			
		}
		
			
		
	}
	
	@Test(priority = 7  , enabled = false)
	public void CheckAiAssistantFunctionality() throws InterruptedException {
		
		String ChatText = "I'm looking for a vegan and environmentally friendly skincare products "; 
	
		WebElement AI_AssistantButton = driver.findElement(By.className("ATChatOpen")); 
		AI_AssistantButton.click(); 
		WebElement ChatInput = driver.findElement(By.id("chatBoxTextEntry")); 
		ChatInput.sendKeys(ChatText); 
		driver.findElement(By.xpath("//*[@id=\"page\"]/div[8]/div[2]/div/div[2]/div/button")).click(); 
		Thread.sleep(5000); 
		WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));

		WebElement messageElement = wait.until(
		    ExpectedConditions.visibilityOfElementLocated(
		        By.cssSelector(".message.messageReceived.feedbackEnabled")
		    )) ; 
		String AI_Feedback = messageElement.getText(); 
		boolean ActualResult = AI_Feedback.contains("vegan") && AI_Feedback.contains("environmentally friendly"); 
		boolean ExpectedAI_result = true; 
		Assert.assertEquals(ActualResult, ExpectedAI_result); 
		
	}

	
}




/*

/*
WebElement RandomSelectedFilters = AllFiltersOptions.get(RandomFilterIndex); 
List<WebElement> OpenFilterOptions = driver.findElements(
	    By.xpath("//button[contains(@class, 'oct-accordion__toggle') and contains(@class, 'oct-accordion__toggle--open')]")
	);

//oct-accordion__item--noTransition

//0 = category , 1 = price range , 2 = brand , 3 = rating , 4 = promotions 

//Select 2 filter Options 

for(int i = 2 ; i <FilterOptions.size()   ; i ++ ) {
	
	if (i == 1 || i== 3 )
		continue; 
	
	WebElement FilterPannel =  FilterOptions.get(i).findElement(By.cssSelector
			("oct-accordion__pannel oct-accordion__pannel--open")); 
	if ( i == 2  || i == 4  ) {
		
		List<WebElement> PannelChoises = FilterPannel.findElements(By.tagName("li")); 
		int RandomChoiceIndex = rand.nextInt(PannelChoises.size()); 
		PannelChoises.get(RandomChoiceIndex).findElement(By.tagName("input")).click(); 
		
		 
	}
	
}
List<WebElement> ProductCategories = driver.findElements(By.cssSelector(".hierarchy-facet__child.facet__child.level-2")); 
int CategoriesNumber = ProductCategories.size(); 
int RandomCate = rand.nextInt(CategoriesNumber); 
WebElement SelectedProductCate = ProductCategories.get(RandomCate);
SelectedProductCate.findElement(By.tagName("button")).click();
String MainProductCateKeyWords = SelectedProductCate.findElement(By.tagName("button")).getDomAttribute("aria-label"); 
Thread.sleep(2000);

List<WebElement> ProductCate2 = ProductCategories.get(RandomCate).findElements(By.cssSelector(".hierarchy-facet__child.facet__child.level-3")); 
int SubCateNumber = ProductCate2.size(); 
int RandomSubCate = rand.nextInt(SubCateNumber); 
WebElement SelectedProductCate2 = ProductCate2.get(RandomSubCate);
SelectedProductCate2.findElement(By.tagName("button")).click(); 
String ProductCate_2_KeyWords = SelectedProductCate2.findElement(By.tagName("button")).getDomAttribute("aria-label");
System.out.println(MainProductCateKeyWords); 
System.out.println(ProductCate_2_KeyWords); 

String[] MainKeyWords = {MainProductCateKeyWords,ProductCate_2_KeyWords }; 


String currentURL  =driver.getCurrentUrl(); 


String currentUrl = driver.getCurrentUrl();

// Extract 'criteria.category' value
String categoryParam = null;
String[] params = currentUrl.split("&");
for (String param : params) {
    if (param.startsWith("criteria.category=")) {
        categoryParam = param.split("=")[1];
        break;
    }
}

if (categoryParam != null) {
    // Decode URL-encoded string
    String decodedCategory = URLDecoder.decode(categoryParam, StandardCharsets.UTF_8);

    // Split by "---" to separate main category and subcategories
    String[] parts = decodedCategory.split("---");

    if (parts.length > 0) {
        String mainCategory = parts[0].trim();
        System.out.println("Main Category: " + mainCategory);

        if (parts.length > 1) {
            String subCategoriesRaw = parts[1];
            String[] subCategories = subCategoriesRaw.split("~"); // or regex if needed

            System.out.println("Subcategories:");
            for (String sub : subCategories) {
                System.out.println("- " + sub.trim());
            }
        }
    }
}



 for (int i = 0; i < MainKeyWords.length; i++) {
        // Remove '&', trim spaces, and normalize multiple spaces to single space
        MainKeyWords[i] = MainKeyWords[i]
                .replace("&", "")            // Remove "&"
                .trim()                      // Remove leading/trailing spaces
                .replaceAll("\\s+", " ").toLowerCase();    // Replace multiple spaces with one
    }
 
 List<String> allKeywords = new ArrayList<>();

    for (String kw : MainKeyWords) {
        // Clean: remove &, trim, normalize spaces
        String cleaned = kw.replace("&", "")
        					.replace("'", "")
                           .trim()
                           .replaceAll("\\s+", " ")
                           .toLowerCase();

        // Split into words if there's more than one word
        String[] words = cleaned.split(" ");

        // Add all words to the list
        for (String word : words) {
            if (!word.isEmpty()) {
                allKeywords.add(word);
            }
        }
    }

    // Print result to check
    System.out.println("Cleaned individual keywords:");
    for (String keyword : allKeywords) {
        System.out.println(keyword);
    }
    
    Thread.sleep(3000); 
    boolean allFound = true;
    for (String keyword : allKeywords) {
        if (!currentURL.toLowerCase().contains(keyword)) {
            allFound = false;
            break;
        }
    }
 

System.out.println(driver.getCurrentUrl().toLowerCase());  

Boolean ExpectedFilteringResult = true; 
Boolean ActualFilteringResult = allFound; 
Assert.assertEquals(ActualFilteringResult, ExpectedFilteringResult);
*/	