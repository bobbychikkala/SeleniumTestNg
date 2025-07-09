package testCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class CricBuzz {
	@Test
	public void testSLvsBan() {
		
		WebDriver driver = new ChromeDriver();
		
	String scoreCard = "https://www.cricbuzz.com/live-cricket-scorecard/106673/rsa-vs-aus-final-icc-world-test-championship-final-2025";
		driver.get(scoreCard);
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		String innings ="innings_2";
		int rows = driver.findElements(By.xpath("//*[@id='"+innings+"']/div[1]/div/div[1]/a")).size();
		int col = driver.findElements(By.xpath("//*[@id='"+innings+"']/div[1]/div[3]/div")).size();
		for(int r=2;r<rows+2;r++) {
			
			for(int c=1;c<col;c++) {
				String data=driver.findElement(By.xpath("//*[@id='"+innings+"']/div[1]/div["+r+"]/div["+c+"]")).getText();
				System.out.print(data);
				if(c<2) {
					for(int k=0;k<25-data.length();k++)System.out.print(" ");
				}
				if(c==2) {
					for(int k=0;k<45-data.length();k++)System.out.print(" ");
				}
				if(c>2) {
					for(int k=0;k<6-data.length();k++)System.out.print(" ");
				}
				
			}
			System.out.println();
		}
		driver.quit();
	}

}
