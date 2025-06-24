package utilities;

import java.io.IOException;

public class testExcel {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub
		String path = "./src/test/resources/excel/cricket.xlsx" ;
		ExcelReader xldata = new ExcelReader(path,"Sheet1");
		Object c=xldata.data(2, 2);
		System.out.println(c);
	}

}
