package sms.Utils;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.*;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class FileImportUtil {

    /**
     * @return -3: unknown error; -2: the file is not a excel file;
     * -1: read file error; 0: insert into table error;
     * otherwise return the total numbers of data rows
     */
    public static int fileImport(String filePath) {
        int ret = -3; // the sheet book is
        List<List<Object>> data = new ArrayList<>();
        List<String> cols_name = new ArrayList<>();
        String fileName = filePath.substring(filePath.lastIndexOf("\\") + 1);
        String filePrefix = fileName.substring(0, fileName.lastIndexOf("."));
        String fileSuffix = fileName.substring(fileName.lastIndexOf("."));
        try (InputStream inputStream = new FileInputStream(filePath)) {
            Workbook workbook;
            if (fileSuffix.equals(".xls")) {
                workbook = new HSSFWorkbook(inputStream);
            } else if (fileSuffix.equals(".xlsx"))
                workbook = new XSSFWorkbook(inputStream);
            else {
                return -2;
            }
            Sheet sheetAt = workbook.getSheetAt(0);
            if (sheetAt.getPhysicalNumberOfRows() < 2) {
                return -1;
            }
            List<Object> tmpList = null;

            for (Row row : sheetAt) {

                if (row.getRowNum() == 0) {
                    for (Cell cell : row)
                        cols_name.add(cell.getStringCellValue());
                    continue;
                }
                tmpList = new ArrayList<>();
                Object cellValue = null;
                for (Cell cell : row) {
                    switch (cell.getCellType()) {
                        case STRING:
                            cellValue = cell.getStringCellValue().trim();
                            break;
                        case NUMERIC: // because all num in this proj is integer
                            cellValue = ((int) cell.getNumericCellValue());
                            break;
                        case BOOLEAN:
                            cellValue = cell.getBooleanCellValue();
                            break;
                        case ERROR:
                            cellValue = "";
                            break;
                        case BLANK:
                            cellValue = "";
                            break;
                        default:
                            cellValue = "";
                    }
                    tmpList.add(cellValue);
                }
                data.add(tmpList);
            }
            ret = data.size();
        } catch (IOException e) {
            ret = -1;
            e.printStackTrace();
        }
//    System.out.println(cols_name);
//    System.out.println(data);
        JdbcUtil db = new JdbcUtil();
        db.getConnection();
        try {
            db.dbInsertBatch(filePrefix, data, cols_name);
        } catch (SQLException e) {
            ret = 0;
            e.printStackTrace();
        }
        db.releaseConn();
        return ret;
    }

}
