import ItemRow from "../components/ItemRow"

export default function Challenge() {
  const lowStockItems = [
    {
        "name": "Good & Plenty",
        "numberInStock": 4,
        "capacity": 20,
        "id": "786123",
        "lowestReplacementCost": 0.18
    },
    {
        "name": "Twix",
        "numberInStock": 17,
        "capacity": 70,
        "id": "627791",
        "lowestReplacementCost": 0.54
    },
    {
        "name": "Starburst",
        "numberInStock": 8,
        "capacity": 45,
        "id": "506709",
        "lowestReplacementCost": 0.07
    },
    {
        "name": "Butterfinger",
        "numberInStock": 10,
        "capacity": 60,
        "id": "601091",
        "lowestReplacementCost": 0.93
    },
    {
        "name": "Sour Patch Kids",
        "numberInStock": 14,
        "capacity": 60,
        "id": "520745",
        "lowestReplacementCost": 0.85
    }
]
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
      <button>Get Low-Stock Items</button>
      <button>Determine Re-Order Cost</button>
    </>
  );
}
