package Model;

public class InventoryItem {

    private String name;
    private int numberInStock;
    private int capacity;

    // The IDs are numbers in the data, but if I'm not doing any math operations on them, I prefer to store them as strings.
    private String id;

    //Initializing replacement cost to -1 so that I know I need to set an initial value when iterating through the distributors prices.
    private double lowestReplacementCost = -1;

    public boolean isLowOnStock() {
        return (numberInStock/(double)capacity) < 0.25;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumberInStock() {
        return numberInStock;
    }

    public void setNumberInStock(int numberInStock) {
        this.numberInStock = numberInStock;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public double getLowestReplacementCost() {
        return lowestReplacementCost;
    }

    public void setLowestReplacementCost(double lowestReplacementCost) {
        this.lowestReplacementCost = lowestReplacementCost;
    }
}
