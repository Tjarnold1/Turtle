export default function ItemRow({ name, id, numberInStock, capacity}) {
    return (
        <>
            <tr>
                <td>{{ id }}</td>
                <td>{{ name }}</td>
                <td>{{ numberInStock }}</td>
                <td>{{ capacity }}</td>
                <input type="number"></input>
            </tr>`
        </>
    );
}