package testCases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class TestClass {
	
	//@BeforeTest
	@BeforeMethod
	public static void launchingBrowser() {
		System.out.println("Launching Browser");
	}
	
	//@AfterTest
	@AfterMethod
	public static void closeingBrowser() {
		System.out.println("Closing Browser");
	}

	@Test(priority=1)
	private static void firstTestMethod() {
		System.out.println("first test executing");
	}
	@Test(priority=2)
	private static void secondTestMethod() {
		System.out.println("second test executing");
	}
}
