package utilities;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

public class ExcelUtility {

    private String filePath;
    private Workbook workbook;
    private Sheet sheet;

    // Constructor to initialize file path
    public ExcelUtility(String filePath) {
        this.filePath = filePath;
    }

    /**
     * Gets the total row count in a given sheet.
     */
    public int getRowCount(String sheetName) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) return 0;
            return sheet.getLastRowNum();
        } finally {
            if (workbook != null) workbook.close();
        }
    }

    /**
     * Gets the total column count in a given row.
     */
    public int getCellCount(String sheetName, int rowNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) return 0;
            Row row = sheet.getRow(rowNum);
            if (row == null) return 0;
            return row.getLastCellNum();
        } finally {
            if (workbook != null) workbook.close();
        }
    }

    /**
     * Reads cell data from a specific row and column index.
     */
    public String getCellData(String sheetName, int rowNum, int colNum) throws IOException {
        try (FileInputStream fis = new FileInputStream(new File(filePath))) {
            workbook = new XSSFWorkbook(fis);
            sheet = workbook.getSheet(sheetName);
            if (sheet == null) return "";
            Row row = sheet.getRow(rowNum);
            if (row == null) return "";
            Cell cell = row.getCell(colNum);
            if (cell == null) return "";

            // Format data into a plain string regardless of type
            DataFormatter formatter = new DataFormatter();
            return formatter.formatCellValue(cell);
        } finally {
            if (workbook != null) workbook.close();
        }
    }

    /**
     * Writes cell data to a specific row and column index.
     */
    public void setCellData(String sheetName, int rowNum, int colNum, String data) throws IOException {
        File file = new File(filePath);
        
        // If file exists, open it. Otherwise, create a fresh workbook.
        if (file.exists()) {
            try (FileInputStream fis = new FileInputStream(file)) {
                workbook = new XSSFWorkbook(fis);
            }
        } else {
            workbook = new XSSFWorkbook();
        }

        sheet = workbook.getSheet(sheetName);
        if (sheet == null) {
            sheet = workbook.createSheet(sheetName);
        }

        Row row = sheet.getRow(rowNum);
        if (row == null) {
            row = sheet.createRow(rowNum);
        }

        Cell cell = row.createCell(colNum);
        cell.setCellValue(data);

        // Commit modifications to the local file system
        try (FileOutputStream fos = new FileOutputStream(file)) {
            workbook.write(fos);
        } finally {
            workbook.close();
        }
    }
}
