package dev.dex;

import java.util.*;

public class Basket {
    private final String name;
    private final Map<StockItem, Integer> list;

    public Basket(String name) {
        this.name = name;
        this.list = new TreeMap<>();
    }

    public int addToBasket(StockItem item, int quantity) {
        // item is 100% in the dev.dex.StockList
        if (item == null || quantity <= 0) return 0;
        int inBasket = list.getOrDefault(item, 0);
        list.put(item, inBasket + quantity);
        return inBasket;
    }

    public int removeFromBasket(StockItem item, int quantity) {
        // item is 100% in the dev.dex.StockList
        if (item == null || quantity <= 0) return 0;
        int inBasket = list.get(item);
        int diff = inBasket - quantity;
        if (diff < 0) {
            System.out.println("dev.dex.Basket -> removeFromBasket() error: can't unreserve more than existing items");
            return 0;
        }
        if (diff == 0) {
            list.remove(item);
            return diff;
        }
        list.put(item, diff);

        return diff;
    }

    public void empty() {
        list.clear();
    }

    public Map<StockItem, Integer> Items() {
        return Collections.unmodifiableMap(list);
    }

    @Override
    public String toString() {
        String s = "\nShopping basket " + name + " contains " + list.size() + " item" + (list.size() > 1 ? "s" : "") + "\n";
        double total = 0;
        for (Map.Entry<StockItem, Integer> elem: list.entrySet()) {
            s += elem.getKey() + ". " + elem.getValue() + "\n";
            total += elem.getKey().getPrice() * elem.getValue();
        }
        return s + "Total cost: " + total + "\n";
    }

}
