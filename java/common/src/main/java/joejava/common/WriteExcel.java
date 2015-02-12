package joejava.mathoms;

import java.io.File;
import java.io.IOException;

import jxl.SheetSettings;
import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableCellFormat;
import jxl.write.WritableFont;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

public class WriteExcel {
	
	public static void main(String[] args){
		
		try{
			String filename = "out/Test.xls";;
			File file = new File(filename);
			WritableWorkbook workbook = Workbook.createWorkbook(file);
			
			WritableFont boldFont = new WritableFont(WritableFont.ARIAL,10,WritableFont.BOLD);
			WritableCellFormat boldFormat = new WritableCellFormat(boldFont);
				
			WritableFont normFont = new WritableFont(WritableFont.ARIAL,10);
			WritableCellFormat normFormat = new WritableCellFormat(normFont);
			
			WritableSheet sheet1 = workbook.createSheet("Sheet 1", 0);
			
			SheetSettings prop1 = sheet1.getSettings();
			prop1.setVerticalFreeze(1);
			
			int cell = 0;
			
			Label heading = new Label(cell,0,"Test",boldFormat);
			sheet1.addCell(heading);
			
			workbook.write();
			workbook.close();
		}catch (Exception e){
			
		}
	}
}
