import ItemRow from "../components/ItemRow"
import inventoryService from "../services/InventoryService"
import { useState } from 'react';

export default function Challenge() {

  async function getLowStockItems() {
    const {data, status} = await inventoryService.retrieveLowStockItems();
    if(status === 200) {
      setLowStockItems(data);
    }
  }

  const [ lowStockItems, setLowStockItems ] = useState([]);
  return (
    <>
      <table>
        <thead>
          <tr>
            <td>SKU</td>
            <td>Item Name</td>
            <td>Amount in Stock</td>
            <td>Capacity</td>
            <td>Order Amount</td>
          </tr>
        </thead>
        <tbody>
          {lowStockItems.map((item) => 
          <ItemRow 
            name={item.name} 
            id={item.id} 
            numberInStock={item.numberInStock} 
            capacity={item.capacity} 
            key={item.name} 
          /> )}
          {/* 
          TODO: Create an <ItemRow /> component that's rendered for every inventory item. The component
          will need an input element in the Order Amount column that will take in the order amount and 
          update the application state appropriately.
          */}
        </tbody>
      </table>
      {/* TODO: Display total cost returned from the server */}
      <div>Total Cost: </div>
      {/* 
      TODO: Add event handlers to these buttons that use the Java API to perform their relative actions.
      */}
      <button onClick={() => getLowStockItems()}>Get Low-Stock Items</button>
      <button >Determine Re-Order Cost</button>
    </>
  );
}
