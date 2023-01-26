package dev.dex;

import java.util.*;

public class Main {
    private static StockList stockList = new StockList();
    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        stockList.addStock(new StockItem("cake", 1.1, 7));
        stockList.addStock(new StockItem("car", 12.5, 2));
        stockList.addStock(new StockItem("chair", 62, 10));
        stockList.addStock(new StockItem("cup", .5, 200));
        stockList.addStock(new StockItem("cup", .45, 7));
        stockList.addStock(new StockItem("door", 72.95, 4));
        stockList.addStock(new StockItem("juice", 2.5, 36));
        stockList.addStock(new StockItem("phone", 96.99, 35));
        stockList.addStock(new StockItem("towel", 2.4, 80));
        stockList.addStock(new StockItem("vase", 8.76, 40));

        System.out.println(stockList);

        for (String s: stockList.Items().keySet()) {
            System.out.println(s);
        }

        Basket dimsBasket = new Basket("Dim");
        reserveItem(dimsBasket, "car", 1);
        System.out.println(dimsBasket);

        reserveItem(dimsBasket, "car", 1);
        System.out.println(dimsBasket);

        reserveItem(dimsBasket, "car", 1);
        reserveItem(dimsBasket, "spanner", 1);
        System.out.println(dimsBasket);

        reserveItem(dimsBasket, "juice", 4);
        reserveItem(dimsBasket, "cup", 12);
        reserveItem(dimsBasket, "bread", 1);
        System.out.println(dimsBasket);
        System.out.println(stockList);

//        checkout(dimsBasket);
        System.out.println(stockList);
        System.out.println(dimsBasket);
        unreserveItem(dimsBasket, "ss", 2);
        System.out.println(dimsBasket);

    }

    private static int reserveItem(Basket basket, String item, int quantity) {
        // retrieve the item from dev.dex.StockList
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockList.reserveStock(item, quantity) == 0) {
            System.out.println(stockItem.getName() + " is out of stock");
            return 0;
        }

        basket.addToBasket(stockItem, quantity);
        return quantity;
    }

    private static int unreserveItem(Basket basket, String item, int quantity) {
        StockItem stockItem = stockList.get(item);
        if (stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if (stockItem.adjustReserved(-quantity) == -1) {
            System.out.println();
            return 0;
        }
        basket.removeFromBasket(stockItem, quantity);
        return 1;
    }

    private static int checkout(Basket basket) {
        double total = 0;
        Map<StockItem, Integer> items = basket.Items();
        for (Map.Entry<StockItem, Integer> entry: items.entrySet()) {
            total += entry.getKey().getPrice() * entry.getValue();
            stockList.sellStock(entry.getKey().getName(), entry.getValue());
        }
        basket.empty();
        System.out.println("Checkout \n Total " + total);
        return 0;
    }
}
