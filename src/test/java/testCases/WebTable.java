package testCases;

import java.time.Duration;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
public class WebTable {
	static WebDriver driver = new ChromeDriver();
	static String innings;
	
	@DataProvider(name="inningNo")
	public  String[][]dataSet(){
		String url1="https://www.cricbuzz.com/live-cricket-scorecard/106673/rsa-vs-aus-final-icc-world-test-championship-final-2025";
		String url2="https://www.cricbuzz.com/live-cricket-scorecard/118586/sl-vs-ban-1st-test-bangladesh-tour-of-sri-lanka-2025";
		return   new String[][]{
			{"innings_1",url1},
			{"innings_2",url1},
			{"innings_3",url1},
			{"innings_4",url1},
			{"innings_1",url2},
			{"innings_2",url2},
			{"innings_3",url2},
			};
	}

	@Test(dataProvider ="inningNo",priority=1)
	public  void testCricketScore(String inning,String url) {
		driver.get(url);
		innings=inning;
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String commonXpath ="//div[@id='"+innings+"']/div[1]";
		int batsmen = driver.findElements(By.xpath(commonXpath+"/div/div[1]/a")).size();
		int column = driver.findElements(By.xpath(commonXpath+"/div[3]/div")).size();
		

		for (int r=2 ; r<=batsmen+2;r++) {		
			for(int c=1;c<column;c++) {
				
				String data =driver.findElement(By.xpath(commonXpath+"/div["+r+"]/div["+c+"]")).getText();
				System.out.print(data);
				
				if(c<2) for(int e=0;e<30-data.length();e++)System.out.print(" ");
				
				else if(c==2) for(int d=0;d<45-data.length();d++)System.out.print(" ");
		
				else  for(int e=0;e<6-data.length();e++)System.out.print(" ");
				
			}
			System.out.println();
		}
		Assert.assertTrue(true);
		//driver.quit();
		System.out.println("===========================================================================================================");
	
	}
	
	
	@AfterTest
	public void closeBrowser() {
		driver.quit();
	}
	
	
}
