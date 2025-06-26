package utilities;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;


public class Screenshot {
	
	public static void takeSreenshot(WebDriver driver,String fileName) throws IOException {
		LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMYYYY_HHmmss");
        String formatted = now.format(formatter);
        File src=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        File dest = new File("./screenshots/"+fileName+formatted+".png");
        FileUtils.copyFile(src, dest);
       
	}
}
