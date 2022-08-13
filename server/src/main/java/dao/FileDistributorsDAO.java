package dao;

import Model.InventoryItem;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.*;

public class FileDistributorsDAO implements DistributorsDAO {

    private FileInputStream fileToRead;

    public FileDistributorsDAO(File fileName) {
        try{
            fileToRead = new FileInputStream(fileName);}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public Map<String, Double> retrieveLowestPrices() {

        //Create a workbook object to work on, and a return list

        XSSFWorkbook inventoryWorkbook = null;
        try {
            inventoryWorkbook = new XSSFWorkbook(fileToRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        Map<String, Double> lowestPrices = new HashMap<>();

        //The distributors file has multiple sheets. So, I'll use a for loop to iterate over them
        for(int i = 0; i < inventoryWorkbook.getNumberOfSheets(); i++) {
            //Create a workbook sheet object to work on
            XSSFSheet inventorySheet = inventoryWorkbook.getSheetAt(i);

            //Iterate through each row, but skip the headers
            Iterator<Row> rowIterator = inventorySheet.iterator();
            rowIterator.next();

            while(rowIterator.hasNext()) {

                Row row = rowIterator.next();
                if(row.getCell(2).getCellType() == CellType.BLANK) {
                    continue;
                }

                if(lowestPrices.get(String.valueOf((int)row.getCell(1).getNumericCellValue())) == null) {
                    lowestPrices.put(String.valueOf((int)row.getCell(1).getNumericCellValue()), row.getCell(2).getNumericCellValue());
                } else {
                    double currentPrice = lowestPrices.get(String.valueOf((int)row.getCell(1).getNumericCellValue()));
                    double proposedPrice = row.getCell(2).getNumericCellValue();
                    double newPrice = currentPrice < proposedPrice ? currentPrice : proposedPrice;
                    lowestPrices.put(String.valueOf((int)row.getCell(1).getNumericCellValue()), newPrice);
                }

            }


        }

        return lowestPrices;
    }

}
