package utilities;

import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ExcelReaderAndWriter {
      
    public Map<String, String> readRequestBodyDetailsForUserModule() throws IOException {
        String path = ".\\src\\test\\resources\\requestBodyDetails.xlsx";
        File excelFile = new File(path);
        FileInputStream fileInputStream = new FileInputStream(excelFile);
        XSSFWorkbook workbook = new XSSFWorkbook(fileInputStream);
        XSSFSheet dataSheet = workbook.getSheet("Sheet1");
        Iterator<Row> rowIterator = dataSheet.rowIterator();
        Map<String, String> requestDetails = new HashMap<String, String>();

        while (rowIterator.hasNext()) {
            Row currentRow = rowIterator.next();
            if (currentRow.getRowNum() > 0) {

                requestDetails.put(currentRow.getCell(0).getStringCellValue(), currentRow.getCell(1).getStringCellValue());

            }


        }
        workbook.close();
        return requestDetails;

    }

   
}


