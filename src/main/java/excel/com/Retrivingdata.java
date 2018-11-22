package excel.com;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.testng.annotations.Test;

public class Retrivingdata {

	
	//This method allows us to read data from an excel file TestData(stored on the excel package)
	  public ArrayList<String> PassData(int colNo) throws IOException 
	  {
		
		  
		  FileInputStream fis=new FileInputStream("C:\\Users\\Reverside\\Desktop\\TestData.xlsx");
		  //C:\\Users\\Reverside\\
		  
		  XSSFWorkbook wb=new XSSFWorkbook(fis);
		 

		  XSSFSheet sheet1=wb.getSheetAt(0);
		  
		  Iterator<Row> rowIt=sheet1.iterator();

		  rowIt.next();
		  ArrayList<String> list=new ArrayList<String>();
		  while(rowIt.hasNext())
		  {
		   list.add(rowIt.next().getCell(colNo).getStringCellValue());
		    
		  }
		  System.out.println(list);
		  
		  return list;
		 
		  
	  }
}