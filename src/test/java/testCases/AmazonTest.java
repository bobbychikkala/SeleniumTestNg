/**
 * 
 */
package testCases;

import org.testng.annotations.Test;
import org.testng.annotations.Test;
import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.Test;
public class AmazonTest {
	@Test
	public  void searchItem() {
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		try {
			driver.findElement(By.xpath("//*[@alt=\"Continue shopping\"]")).click();
		}
		catch (Exception e) {}
		finally {
			String mobileName = "iPhone";
			driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']")).sendKeys(mobileName);
			driver.findElement(By.id("nav-search-submit-button")).click();
			String dynamicTitleXpath = "//*[@data-cy='title-recipe']/a/h2/span[starts-with(text(),'" + mobileName
					+ "')]";
			List<WebElement> titles = driver.findElements(By.xpath(dynamicTitleXpath));
			List<WebElement> prices = driver.findElements(By.xpath(dynamicTitleXpath
					+ "/parent::h2/parent::a/parent::div/parent::div/div[3]//span[@class=\"a-price\"]"));
			System.out.println(titles.size() + " " + mobileName + " products found");

			for (int i = 0; i < titles.size(); i++) {
				System.out.println();
				System.out.print(i + 1 + " . " + titles.get(i).getText().substring(0,45));
				System.out.println("  Price:" + prices.get(i).getText());	
			}
		}
		driver.quit();
	}
}
