import Model.InventoryItem;
import dao.FileInventoryDAO;
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

        FileInventoryDAO inventoryReaderDAO = null;

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
        String inventoryFileName = "C:\\Users\\Student\\source\\repos\\individual\\projects\\TopBlocAssessment\\code-challenge\\server\\resources\\Inventory.xlsx";
        try {
            inventoryReaderDAO = new FileInventoryDAO(new File(inventoryFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<InventoryItem> lowStockInventory = new ArrayList<>();
        lowStockInventory = inventoryReaderDAO.retrieveLowStockInventory();
        System.out.println(lowStockInventory);

        //TODO: Return JSON containing the candies for which the stock is less than 25% of it's capacity
        get("/low-stock", (request, response) -> {
            return null;
        });

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            return null;
        });

    }

}
