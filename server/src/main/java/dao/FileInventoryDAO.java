package dao;

import Model.InventoryItem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileInventoryDAO implements InventoryDAO{

    private FileInputStream fileToRead;

    public FileInventoryDAO(File fileName) {
        try{
        fileToRead = new FileInputStream(fileName);}
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<InventoryItem> retrieveLowStockInventory() {

        //Create a workbook object to work on, and a return list

        XSSFWorkbook inventoryWorkbook = null;
        try {
             inventoryWorkbook = new XSSFWorkbook(fileToRead);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<InventoryItem> lowStockItems = new ArrayList<>();

        //Create a workbook sheet object to work on
        XSSFSheet inventorySheet = inventoryWorkbook.getSheetAt(0);

        //Iterate through each row, but skip the headers
        Iterator<Row> rowIterator = inventorySheet.iterator();
        rowIterator.next();
        while(rowIterator.hasNext()) {

            // Create an inventory item to add to my return list, a cell iterator, and a row item
            Row row = rowIterator.next();
            InventoryItem rowItem = new InventoryItem();

            // Because we know the structure of the sheet, we can just set the inventory item values.
            rowItem.setName(row.getCell(0).getStringCellValue());
            rowItem.setNumberInStock((int)row.getCell(1).getNumericCellValue());
            rowItem.setCapacity((int)row.getCell(2).getNumericCellValue());
            rowItem.setId(String.valueOf((int)row.getCell(3).getNumericCellValue()));

            //If the item is in low stock, add it to my list
            if(rowItem.isLowOnStock()) {
                lowStockItems.add(rowItem);
            }

        }

        return lowStockItems;
    }

}
