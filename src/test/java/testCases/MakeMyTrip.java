package testCases;
import org.testng.annotations.Test;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.DataProvider;


import utilities.Screenshot;

public class MakeMyTrip {
	static WebDriver driver = new ChromeDriver();
	@BeforeTest
	public void launchingBrowser(){
		driver.get("https://www.makemytrip.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		driver.findElement(By.xpath("//*[@data-cy='closeModal']")).click();	
	}

	@AfterTest
	public void closingBrowser() { 
		driver.quit();
	}


	@Test(dataProvider ="cities")
	public void testLeastPrice(String from,String to) throws IOException {
		driver.findElement(By.id("fromCity")).sendKeys(from);
		driver.findElement(By.xpath("//*[@class='makeFlex column flexOne']")).click();
		driver.findElement(By.id("toCity")).sendKeys(to);
		driver.findElement(By.xpath("//*[@class='makeFlex column flexOne']")).click();

		int leastPrice = Integer.MAX_VALUE;		//27456566677
		String leastPriceofDate="";
		String priceXpath ="//*[@class=' todayPrice']";
		List<WebElement> prices = driver.findElements(By.xpath(priceXpath));
		List<WebElement> dates = driver.findElements(By.xpath(priceXpath+"/../.."));
		
		HashMap<String,Integer> priceWithDates = new HashMap<String,Integer>();
		System.out.println();
		
		for (int i=1 ; i<prices.size();i++){
			
			int price =Integer.parseInt(prices.get(i).getText().replace(",", ""));//3000
			String eachDate = dates.get(i).getDomAttribute("aria-label");
			priceWithDates.put(eachDate, price); // this is extra --storing date and price in dictionary
			if (price<leastPrice) {
				leastPrice =price ;
				leastPriceofDate = eachDate;
				
				
			}
			
		}
		Screenshot.takeSreenshot(driver,"testMMT");
		System.out.println("From "+from +" to "+to + " Flights");
		System.out.println("Cheapest flight Price is :"+leastPrice);
		System.out.println("Dated on " +leastPriceofDate);
		//dates.get(k).click();
	}

	
	@DataProvider(name ="cities")
	public String[][] getData(){
		return new String [][] {
			{"Hyderabad","Bengaluru"}, 
			{"Bengaluru","Hyderabad"}, 
			{"Bengaluru","Chennai"},	
			{"Chennai","Delhi"},
			{"Mumbai","Delhi"}, 
			{"Kolkata","Delhi"}
				 
		};
	}
}
