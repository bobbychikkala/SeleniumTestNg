package testCases;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;

public class AmazonTest {
@Test
	public void testSearch() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
	
		
		try {
			driver.findElement(By.xpath("//*[@alt='Continue shopping']")).click();
			}
		catch(Exception e) {
			
		}
		driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys("iPhone");
		driver.findElement(By.id("nav-search-submit-button")).click();
		List<WebElement> mobiles = driver.findElements(By.xpath("//div[@data-cy='title-recipe']/a/h2/span[contains(text(),'Apple')]"));
		mobiles.size();
		}
		//driver.quit();
	}

