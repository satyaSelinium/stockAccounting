package Utilities;


import java.io.FileInputStream;
import java.io.FileOutputStream;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelFileUtil
{
	Workbook wb;
	public  ExcelFileUtil() throws Throwable
	{
		FileInputStream fis=new FileInputStream("C:\\Users\\satyasree.d\\workspace\\MavenStockAccounting\\TextInputs\\InputSheet.xlsx");
		wb=WorkbookFactory.create(fis);
		
	}
	
	//row count
	
	public int rowCount(String sheetName)
	{
		return wb.getSheet(sheetName).getLastRowNum();
	}

    //column count
	
	public int colCount(String sheetName,int row)
	{
		return wb.getSheet(sheetName).getRow(row).getLastCellNum();
				
	}

	public String getData(String sheetName,int row,int col)
	{
		String data="";
		if(wb.getSheet(sheetName).getRow(row).getCell(col).getCellType()==Cell.CELL_TYPE_NUMERIC)
		{
		int celldata=(int)wb.getSheet(sheetName).getRow(row).getCell(col).getNumericCellValue();
		data=String.valueOf(celldata);
		}
		else
		{
			data=wb.getSheet(sheetName).getRow(row).getCell(col).getStringCellValue();
		}
		return data;
	}
	
		public void setData(String sheetName,int row,int col,String status) throws Throwable
		{
			Sheet sh=wb.getSheet(sheetName);
			Row rownum=sh.getRow(row);
			Cell cell=rownum.createCell(col);
			cell.setCellValue(status);
			
			if(status.equalsIgnoreCase("pass"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.GREEN.index);
				font.setBold(true);
				style.setFont(font);
				rownum.getCell(col).setCellStyle(style);
			}
			else if(status.equalsIgnoreCase("Fail"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.RED.index);
				font.setBold(true);
				style.setFont(font);
				rownum.getCell(col).setCellStyle(style);
				
			}
			else if(status.equalsIgnoreCase("Not Executed"))
			{
				CellStyle style=wb.createCellStyle();
				Font font=wb.createFont();
				font.setColor(IndexedColors.BLUE.index);
				font.setBold(true);
				style.setFont(font);
				rownum.getCell(col).setCellStyle(style);
				
			}	
			
			FileOutputStream fos=new FileOutputStream("C:\\Users\\satyasree.d\\workspace\\MavenStockAccounting\\TextOutput\\output.xlsx");
			wb.write(fos);
			fos.close();
		}
		
		
		
	
	
	public static void main(String[] args) throws Throwable
	{
		ExcelFileUtil xf=new ExcelFileUtil();
		System.out.println(xf.rowCount("Sheet1"));
		System.out.println(xf.colCount("sheet1",1));
		System.out.println(xf.getData("sheet1",1,1));
		xf.setData("sheet1", 1, 2, "pass");	
		xf.setData("sheet1", 2, 2, "Fail");
		xf.setData("sheet1", 3, 2, "Not Executed");
	}
	
}



