package dev.dex;

import java.util.*;

public class StockItem implements Comparable<StockItem> {
    private final String name;
    private double price;
    private int quantityStock;
    private int reserved;

    public StockItem(String name, double price) {
        this(name,price,0);
    }

    public StockItem(String name, double price, int quantityStock) {
        this.name = name;
        this.price = price;
        this.quantityStock = quantityStock;
    }

    public void adjustStock(int quantity) {
        int newQuantity = quantityStock + quantity;
        if (newQuantity >= 0) {
            quantityStock = newQuantity;
        }
    }

    public int adjustReserved(int reserved) {
        int newReserved = this.reserved + reserved;
        if (quantityStock < newReserved) {
            System.out.println("dev.dex.StockItem -> setReserved() error: newReserved is bigger than quantityStock");
            return -1;
        }
        if (newReserved < 0) {
            System.out.println("dev.dex.StockItem -> setReserved() error: reserved cannot be zero");
            return -1;
        }
        this.reserved = newReserved;
        return newReserved;
    }

    public int availableStock() {
        return quantityStock - reserved;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public int quantityStock() {
        return quantityStock;
    }

    public void setPrice(double price) {
        if (price < 0) return;
        this.price = price;
    }

    public int getReserved() {
        return reserved;
    }


    @Override
    public boolean equals(Object o) {
        System.out.println("Entering dev.dex.StockItem.equals");
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        StockItem stockItem = (StockItem) o;
        return Objects.equals(name, stockItem.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name) + 31;
    }

    @Override
    public int compareTo(StockItem o) {
        System.out.println("dev.dex.StockItem -> compareTo()");
        if (o == null) throw new NullPointerException("o is null");
        if (this == o) return 0;
        return name.compareTo(o.name);
    }

    @Override
    public String toString() {
        return name + ": price " + price;
    }
}
