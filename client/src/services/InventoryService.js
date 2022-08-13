import axios from "axios";

const http = axios.create({
    baseURL: "http://localhost:4567"
});

function retrieveLowStockItems() {
    return http.get("/low-stock");
}

function retrieveTotalCost(data) {
    return http.post("/restock-cost", data);
}

const inventoryService = {
    retrieveLowStockItems,
    retrieveTotalCost
}

export default inventoryService;

