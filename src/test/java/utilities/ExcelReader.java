package utilities;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFCell;
import java.io.FileInputStream;
import java.io.IOException;

public class ExcelReader {

	String file;
	Object sheetNo;
	FileInputStream fis ;
	XSSFWorkbook workBook;
	XSSFSheet sheet;
    public ExcelReader(String file ,int sheetNo) throws IOException {
    	this.file=file;
    	this.sheetNo =sheetNo;
    	fis = new FileInputStream(this.file);
    	workBook= new XSSFWorkbook(fis);
    	 sheet = workBook.getSheetAt(sheetNo);
    }
    public ExcelReader(String file ,String sheetName) throws IOException {
    	this.file=file;
    	this.sheetNo =sheetName;
    	fis = new FileInputStream(this.file);
    	workBook= new XSSFWorkbook(fis);
    	 sheet = workBook.getSheet(sheetName);
    }
    
    public Object data(int r,int c) throws IOException  {
    
    	XSSFCell cell = sheet.getRow(r).getCell(c);
    	cell.setCellValue("modi");
    	
    	workBook.close();
    	fis.close();
    	return cell;
    }

	  
  }
    
