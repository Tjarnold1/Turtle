

export default function ItemRow({id, name, numberInStock, capacity, setRestockOrder}) {
    
    /*function changeQuantity(event) {
        setQuantity(event.target.value);
        if(restockOrder === []) {
            let obj = {};
            obj["id"] = id;
            obj["quantity"] = quantity;
            setRestockOrder(restockOrder.push(obj));
        } else {
            let index = restockOrder.findIndex((item) => {
                return item.id === id;
            })
            setRestockOrder(restockOrder.splice(index,1));
            setRestockOrder(...restockOrder, {id, quantity});
        }
    } */

    

   // const [ quantity, setQuantity ] = useState(0);
    return (
        <>
            <tr>
                <td>{ id }</td>
                <td>{ name }</td>
                <td>{ numberInStock }</td>
                <td>{ capacity }</td>
                <input type="number" onChange={(event) => setRestockOrder([{id, quantity: event.target.value}])}></input>
            </tr>
        </>
    );
}