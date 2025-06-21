package testCases;
import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


public class MakeMyTrip {
	static WebDriver driver = new ChromeDriver();
	@BeforeTest
	public void setUp(){
		
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@data-cy='closeModal']")).click();	
	}
	@AfterTest
	public void tearDown() { 
		driver.quit();
	
	}
	@DataProvider(name ="data")
	public String[][] getData(){
		return new String [][] {
			{"Hyderabad","Bengaluru"},
			{"Bengaluru","Hyderabad"},
			{"Chennai","Delhi"},
			{"Mumbai","Delhi"}
		};
	}
	
	@Test(dataProvider ="data")
	public void testLeastPrice(String from,String to) {

		
		driver.findElement(By.id("fromCity")).sendKeys(from);
		driver.findElement(By.xpath("//*[@class='makeFlex column flexOne']")).click();
		driver.findElement(By.id("toCity")).sendKeys(to);
		driver.findElement(By.xpath("//*[@class='makeFlex column flexOne']")).click();
		
		int leastPrice = Integer.MAX_VALUE;		
		String leastPriceofDate="";
		String priceXpath ="//*[@class=' todayPrice']";
		List<WebElement> prices = driver.findElements(By.xpath(priceXpath));
		List<WebElement> dates = driver.findElements(By.xpath(priceXpath+"/../.."));
		System.out.println();
		for (int i=1 ; i<prices.size();i++){
			int price =Integer.parseInt(prices.get(i).getText().replace(",", ""));
			if (price<=leastPrice) {
				leastPrice =price ;
				leastPriceofDate = dates.get(i).getDomAttribute("aria-label");
			}
		}
System.out.println("From "+from +" to "+to + " Flights");
System.out.println("Least Price is :"+leastPrice);
System.out.println("Dated on " +leastPriceofDate);


	}

}
