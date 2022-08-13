package dao;

import Model.InventoryItem;

import java.util.List;

public interface InventoryDAO {

    List<InventoryItem> retrieveLowStockInventory();

}
