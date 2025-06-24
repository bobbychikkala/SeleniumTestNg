package testCases;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class FlipkartSearch {

	private static WebDriver driver;

	@DataProvider(name="data")
	public String[][]getData(){
		return new String [][] {
			{"Samsung phone"}
		};
	}

	@BeforeTest
	public void launchingBrowser() {
		driver = new ChromeDriver();
		driver.get("https://www.flipkart.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	}
	
	@AfterTest
	public void closingBrowser() {
		//driver.quit();
	}
	
	@Test(dataProvider="data")
	public void testSerachItem(String itemToBeSearch) throws InterruptedException  {

		int highestDisc =0;
		String targetProduct="";
		String productOfferPrice="";
		String productPrice= "";

		WebElement searchInputBox =driver.findElement(By.xpath("//*[@name='q']"));
		
		searchInputBox.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
		
		searchInputBox.sendKeys(itemToBeSearch);
	
		searchInputBox.sendKeys(Keys.ENTER);
		List <WebElement>itemsIDs=waitForVisibilityOfElement(driver,By.xpath("//div[@data-id]"),10);
		
		
		for(int i=0; i<itemsIDs.size();i++) {
			String itemID =itemsIDs.get(i).getAttribute("data-id");
			System.out.println();
			
			String itemName =driver.findElement(By.xpath("//div[@data-id='"+itemID+"']//div[@class='KzDlHZ']")).getText();
			String offerPrice = driver.findElement(By.xpath("//*[@data-id='"+itemID+"']//div[@class='hl05eU']/div[1]")).getText();
			String actualPrice=offerPrice;
			String discountper="0";
			try{
				 actualPrice = 
						driver.findElement(By.xpath("//*[@data-id='"+itemID+"']//div[@class='hl05eU']/div[2]")).getText();
			
				 discountper =
						driver.findElement(By.xpath("//*[@data-id='"+itemID+"']//div[@class='hl05eU']/div[3]")).getText();
			}
			catch (NoSuchElementException e){
				System.out.println("No offer available for the price  " );
			}
			
			
			Integer discValue = Integer.parseInt(discountper.replaceAll("[^0-9]", ""));
			//System.out.println(discValue);
			if(discValue>highestDisc) {
				highestDisc=discValue;
				targetProduct = i+1+". "+itemName;
				productOfferPrice=offerPrice;
				productPrice=actualPrice;
			}
			System.out.println(i+1+". "+itemName+ "\n   Offer Price :"+offerPrice +"\n   Actual Price :"+actualPrice +"\n   Dicount :"+discountper);
		}
		System.out.println("Highest Discount Product");
		System.out.println(targetProduct+"\n   " +productOfferPrice+"\n   "+productPrice);
	}
	
	public static List<WebElement> waitForVisibilityOfElement(WebDriver driver, By locator, int timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(timeoutInSeconds));
        return wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(locator));
    }
	
	
}
