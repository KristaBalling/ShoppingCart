package com.theironyard;

import java.util.Map;

public class Main {
    private static StockList stockList = new StockList();


    public static void main(String[] args) {
        StockItem temp = new StockItem("bread", 0.86, 100);
        stockList.addStock(temp);

        temp = new StockItem("cake", 1.10, 7);
        stockList.addStock(temp);

        temp = new StockItem("car", 12.50, 2);
        stockList.addStock(temp);

        temp = new StockItem("chair", 62.0, 10);
        stockList.addStock(temp);

        temp = new StockItem("cup", .50, 200);
        stockList.addStock(temp);

        temp = new StockItem("cup", .45, 7);
        stockList.addStock(temp);

        temp = new StockItem("door", 72.95, 4);
        stockList.addStock(temp);

        temp = new StockItem("juice", 2.50, 36);
        stockList.addStock(temp);

        temp = new StockItem("phone", 96.99, 35);
        stockList.addStock(temp);

        temp = new StockItem("towel", 2.40, 80);
        stockList.addStock(temp);

        temp = new StockItem("vase", 8.76, 40);
        stockList.addStock(temp);

        System.out.println(stockList);

        for(String s: stockList.Items().keySet()) {
            System.out.println(s);
        }

        Cart myCart = new Cart("Krista");
        sellItem(myCart, "car", 1);
        System.out.println((myCart));

        sellItem(myCart, "car", 1);
        System.out.println((myCart));

        if(sellItem(myCart, "car", 1) != 1) {
            System.out.println("There are no more cars in stock");
        }

        sellItem(myCart, "computer", 3);
//        System.out.println((myCart));

        sellItem(myCart, "juice", 4);
        sellItem(myCart, "cup", 12);
        sellItem(myCart, "bread", 1);
//        System.out.println(myCart);

//        System.out.println(stockList);
        Cart cart = new Cart("customer");
        sellItem(cart, "cup", 100);
        sellItem(cart, "juice", 5);
        removeItem(cart, "cup", 1);
        System.out.println(cart);

        removeItem(myCart, "car", 1);
        removeItem(myCart, "cup", 9);
        removeItem(myCart, "car", 1);
        System.out.println("cars removed: " + removeItem(myCart, "car", 1)); //should not remove any

        System.out.println(myCart);

        removeItem(myCart, "bread", 1);
        removeItem(myCart, "cup", 3);
        removeItem(myCart, "juice", 4);
        removeItem(myCart, "cup", 3);
        System.out.println(myCart);

        System.out.println("\nDisplay stocklist before and after checkout");
        System.out.println(cart);
        System.out.println(stockList);
        checkOut(cart);
        System.out.println(cart);
        System.out.println(stockList);

        //cannot add items to a unmodifiable map
        //temp = new StockItem("pen", 1.12);
        //stockList.Items().put(temp.getName(), temp);
        StockItem car = stockList.Items().get("car");
        if(car != null) {
            car.adjustStock(2000);
        }
        if(car != null) {
            stockList.get("car").adjustStock(-1000);
        }

        stockList.Items().get("car").adjustStock(2000);
        stockList.Items().get("car").adjustStock(-1000);
        System.out.println(stockList);
//        for(Map.Entry<String, Double> price: stockList.PriceList().entrySet()) {
//            System.out.println(price.getKey() + " costs " + price.getValue());
//        }

        checkOut(myCart);
        System.out.println(myCart);

    }

    public static int sellItem(Cart cart, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.reserveStock(item, quantity) != 0) {
            return cart.addToCart(stockItem, quantity);
        }
        return 0;
    }

    public static int removeItem(Cart cart, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(cart.removeFromCart(stockItem, quantity) == quantity) {
            return stockList.unreserveStock(item, quantity);
        }
        return 0;
    }

    public static void checkOut(Cart cart) {
        for(Map.Entry<StockItem, Integer> item : cart.Items().entrySet()) {
            stockList.sellStock(item.getKey().getName(), item.getValue());
        }
        cart.clearCart();
    }
}
