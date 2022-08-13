export default function ItemRow({id, name, numberInStock, capacity}) {
    return (
        <>
            <tr>
                <td>{ id }</td>
                <td>{ name }</td>
                <td>{ numberInStock }</td>
                <td>{ capacity }</td>
                <input type="number"></input>
            </tr>
        </>
    );
}