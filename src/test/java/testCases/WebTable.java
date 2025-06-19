package testCases;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
public class WebTable {
	static WebDriver driver = new ChromeDriver();

	@Test
	public static void testChampionFinal() {
		String scoreCard = "https://www.cricbuzz.com/live-cricket-scorecard/106673/rsa-vs-aus-final-icc-world-test-championship-final-2025";
		driver.get(scoreCard);
	}
	@Test
	public static void banVsSl() {
		String scoreCard = "https://www.cricbuzz.com/live-cricket-scorecard/118586/sl-vs-ban-1st-test-bangladesh-tour-of-sri-lanka-2025";
		driver.get(scoreCard);
	}
	@AfterMethod
	public void testDynamicTable() {
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		int batsmen = driver.findElements(By.xpath("//div[@id='innings_1']/div[1]/div/div[1]/a")).size();
		int column = driver.findElements(By.xpath("(//div[@id='innings_1']/div[1]/div[3]/div)")).size();

		for (int r=0 ; r<batsmen+1;r++) {		
			for(int c=1;c<column;c++) {
				int k=2+r;
				String data =driver.findElement(By.xpath("(//div[@id='innings_1']/div[1]/div["+k+"]/div)["+c+"]")).getText();
				System.out.print(data);
				if((c<2) && data.length()<25){
					for(int e=0;e<25-data.length();e++)System.out.print(" ");
				}
				if((c==2) && data.length()<45){
					for(int d=0;d<45-data.length();d++)System.out.print(" ");
				}
				if((c>2) && data.length()<5){
					for(int e=0;e<5-data.length();e++)System.out.print(" ");
				}
			}
			System.out.println();
		}
		Assert.assertTrue(true);
		//driver.quit();
		System.out.println("===========================================================================================================");
	}
}
