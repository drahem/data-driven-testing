package ddt;

//reading value of a particular cell  
import java.io.FileInputStream;  
import java.io.FileNotFoundException;  
import java.io.IOException;  
import org.apache.poi.ss.usermodel.Cell;  
import org.apache.poi.ss.usermodel.*;  
import org.apache.poi.ss.usermodel.Sheet;  
import org.apache.poi.ss.usermodel.Workbook;  
import org.apache.poi.xssf.usermodel.XSSFWorkbook;  

public class readdata {


public static void main(String[] args){
	
//readdata rc=new readdata();   //object of the class  
//reading the value of 2nd row and 2nd column  
//String vOutput=rc.ReadCellData(1, 0);   
String [] cell = new String [7]; 

for(int j=1;j<7;j++) {
for(int i=1;i<6;i++) {
	cell[i] = ReadCellData(j, i);
	System.out.println(cell[i]);
	}
}  
}
//method defined for reading a cell  
public static String ReadCellData(int vRow, int vColumn)  
{  
String value=null;          //variable for storing the cell value  
Workbook wb=null;           //initialize Workbook null  
try  
{  
//reading data from a file in the form of bytes  
FileInputStream fis=new FileInputStream("C:\\Users\\drahem\\eclipse-workspace\\cura ddt\\data.xlsx");  
//constructs an XSSFWorkbook object, by buffering the whole stream into the memory  
wb=new XSSFWorkbook(fis);  
}  
catch(FileNotFoundException e)  
{  
e.printStackTrace();  
}  
catch(IOException e1)  
{  
e1.printStackTrace();  
}  
Sheet sheet=wb.getSheetAt(0);   //getting the XSSFSheet object at given index  
Row row=sheet.getRow(vRow); //returns the logical row  
Cell cell=row.getCell(vColumn); //getting the cell representing the given column  

try {
value=cell.getStringCellValue();    //getting cell value  
}
catch(Exception e){
	int x = (int) cell.getNumericCellValue();
	value = Integer.toString(x);
}
System.out.println(value);
return value;               //returns the cell value  
}  



}