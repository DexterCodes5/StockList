package dev.dex;

import java.util.*;

public class StockList {
    private final Map<String, StockItem> list;

    public StockList() {
        this.list = new LinkedHashMap<>();
    }

    public int addStock(StockItem item) {
        if (item == null) {
            System.out.println("dev.dex.StockList -> addStock() error: item is null");
            return 0;
        }
        // check if already have quantities of this item
        StockItem inStock = list.getOrDefault(item.getName(), item);
//        dev.dex.StockItem inStock = list.get(item.getName());
        if (inStock != item) {
            // item exists
            item.adjustStock(inStock.quantityStock());
        }
        list.put(item.getName(), item);
        return item.quantityStock();
    }

    public int sellStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if (inStock == null || inStock.quantityStock() < quantity || quantity <= 0) {
            System.out.println("dev.dex.StockItem -> reserveStock() error: item doesn't exist or quantity is invalid");
           return 0;
        }
        inStock.adjustReserved(-quantity);
        inStock.adjustStock(-quantity);
        return quantity;
    }

    public int reserveStock(String item, int quantity) {
        StockItem inStock = list.getOrDefault(item, null);
        if (inStock == null || inStock.availableStock() < quantity || quantity <= 0) {
            System.out.println("dev.dex.StockItem -> reserveStock() error: item doesn't exist or quantity is invalid");
            return 0;
        }
        inStock.adjustReserved(quantity);
//        inStock.adjustStock(-quantity);

        return quantity;
    }

    public StockItem get(String key) {
        return list.get(key);
    }

    public Map<String, StockItem> Items() {
        return Collections.unmodifiableMap(list);
    }

    public Map<String, Double> Prices() {
        Map<String, Double> prices = new LinkedHashMap();
        for (Map.Entry<String, StockItem> elem: list.entrySet()) {
            prices.put(elem.getKey(), elem.getValue().getPrice());
        }
        return Collections.unmodifiableMap(prices);
    }

    @Override
    public String toString() {
        String s = "Stock List:\n";
        double total = 0;
        for (Map.Entry<String, StockItem> e: list.entrySet()) {
            StockItem item = e.getValue();
            double cost = item.getPrice() * item.quantityStock();
            s += item + ". reserved " + item.getReserved()
                    + " quantity " + item.quantityStock()  + " price " + String.format("%.2f$", cost) + "\n";
            total += cost;
        }
        return s + "Total stock value " + String.format("%.2f$", total);
    }
}
