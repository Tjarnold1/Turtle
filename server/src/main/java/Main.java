import Model.InventoryItem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        //This is required to allow GET and POST requests with the header 'content-type'
        options("/*",
                (request, response) -> {
                        response.header("Access-Control-Allow-Headers",
                                "content-type");

                        response.header("Access-Control-Allow-Methods",
                                "GET, POST");

                    return "OK";
                });

        //This is required to allow the React app to communicate with this API
        before((request, response) -> response.header("Access-Control-Allow-Origin", "http://localhost:3000"));

        //Initializing my inventory list that I want to return, and denoting the files I want to read from.
        FileInputStream  inventoryFile;
        try {
            inventoryFile = new FileInputStream(new File("../../../resources/Inventory.xlsx"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<InventoryItem> lowStockInventory = new ArrayList<>();
        lowStockInventory = readInventoryFile(inventoryFile);

        //TODO: Return JSON containing the candies for which the stock is less than 25% of it's capacity
        get("/low-stock", (request, response) -> {
            return null;
        });

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            return null;
        });

    }

    private List<InventoryItem> readInventoryFile(FileInputStream myFile) throws IOException {

        //Create a workbook object to work on, and a return list
        XSSFWorkbook inventoryWorkbook = new XSSFWorkbook(myFile);
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
            rowItem.setId(row.getCell(3).getStringCellValue());

            //If the item is in low stock, add it to my list
            if(rowItem.isLowOnStock()) {
                lowStockItems.add(rowItem);
            }

        }

        return lowStockItems;
    }
}
