import Model.InventoryItem;
import dao.FileDistributorsDAO;
import dao.FileInventoryDAO;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import com.google.gson.Gson;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.*;

import static spark.Spark.*;

public class Main {

    public static void main(String[] args) {

        FileInventoryDAO inventoryReaderDAO = null;
        FileDistributorsDAO distributorReaderDAO = null;

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
        String distributorFileName = "C:\\Users\\Student\\source\\repos\\individual\\projects\\TopBlocAssessment\\code-challenge\\server\\resources\\Distributors.xlsx";
        try {
            inventoryReaderDAO = new FileInventoryDAO(new File(inventoryFileName));
            distributorReaderDAO = new FileDistributorsDAO(new File(distributorFileName));
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<InventoryItem> lowStockInventory = new ArrayList<>();
        Map<String, Double> prices = new HashMap<>();
        lowStockInventory = inventoryReaderDAO.retrieveLowStockInventory();
        prices = distributorReaderDAO.retrieveLowestPrices();

        //TODO: Return JSON containing the candies for which the stock is less than 25% of it's capacity
        List<InventoryItem> finalLowStockInventory = lowStockInventory;
        Gson gson = new Gson();
        get("/low-stock", (request, response) -> {
            return finalLowStockInventory;
        }, gson::toJson);

        //TODO: Return JSON containing the total cost of restocking candy
        post("/restock-cost", (request, response) -> {
            return null;
        });

    }

}
