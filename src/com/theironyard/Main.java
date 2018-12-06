package com.theironyard;

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

        temp = new StockItem("cup", .45, 7);
        stockList.addStock(temp);

        temp = new StockItem("cup", .50, 200);
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
        System.out.println((myCart));

        sellItem(myCart, "juice", 4);
        sellItem(myCart, "cup", 12);
        sellItem(myCart, "bread", 1);
        System.out.println(myCart);

        System.out.println(stockList);


    }

    public static int sellItem(Cart cart, String item, int quantity) {
        //retrieve the item from stock list
        StockItem stockItem = stockList.get(item);
        if(stockItem == null) {
            System.out.println("We don't sell " + item);
            return 0;
        }
        if(stockList.sellStock(item, quantity) != 0) {
            cart.addToCart(stockItem, quantity);
            return quantity;
        }
        return 0;
    }
}
