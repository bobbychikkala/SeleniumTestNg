package testCases;

import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class TestParamXml {
	@Parameters({"url","browser"})
	@Test
	public  void testXMfile(String u,String b) {
		System.out.println(u+"---"+b);
	}

}
