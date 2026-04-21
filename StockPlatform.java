import java.util.*;
class Stock {
    String name;
    double price;

    Stock(String name, double price) {
        this.name = name;
        this.price = price;
    }
}
class Transaction {
    String type;
    String stockName;
    int quantity;
    double price;

    Transaction(String type, String stockName, int quantity, double price) {
        this.type = type;
        this.stockName = stockName;
        this.quantity = quantity;
        this.price = price;
    }

    void display() {
        System.out.println(type + " | " + stockName + " | Qty: " + quantity + " | Price: " + price);
    }
}

class User {
    double balance;
    HashMap<String, Integer> portfolio;
    ArrayList<Transaction> history;

    User(double balance) {
        this.balance = balance;
        portfolio = new HashMap<>();
        history = new ArrayList<>();
    }

    void buyStock(Stock stock, int qty) {
        double cost = stock.price * qty;

        if (balance >= cost) {
            balance -= cost;
            portfolio.put(stock.name, portfolio.getOrDefault(stock.name, 0) + qty);
            history.add(new Transaction("BUY", stock.name, qty, stock.price));
            System.out.println("Stock bought successfully!");
        } else {
            System.out.println("Insufficient balance!");
        }
    }
    void sellStock(Stock stock, int qty) {
        if (portfolio.getOrDefault(stock.name, 0) >= qty) {
            balance += stock.price * qty;
            portfolio.put(stock.name, portfolio.get(stock.name) - qty);
            history.add(new Transaction("SELL", stock.name, qty, stock.price));
            System.out.println("Stock sold successfully!");
        } else {
            System.out.println("Not enough stock to sell!");
        }
    }
    void showPortfolio(HashMap<String, Stock> market) {
        System.out.println("\n--- Portfolio ---");
        double totalValue = 0;

        for (String stockName : portfolio.keySet()) {
            int qty = portfolio.get(stockName);
            double price = market.get(stockName).price;
            double value = qty * price;

            totalValue += value;
            System.out.println(stockName + " | Qty: " + qty + " | Value: " + value);
        }

        System.out.println("Total Portfolio Value: " + totalValue);
        System.out.println("Balance: " + balance);
        System.out.println("Net Worth: " + (totalValue + balance));
    }
    void showHistory() {
        System.out.println("\n--- Transaction History ---");
        for (Transaction t : history) {
            t.display();
        }
    }
}

// Main class
public class StockPlatform {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Market data
        HashMap<String, Stock> market = new HashMap<>();
        market.put("TCS", new Stock("TCS", 3500));
        market.put("INFY", new Stock("INFY", 1500));
        market.put("RELIANCE", new Stock("RELIANCE", 2800));

        User user = new User(10000); // starting balance

        int choice;

        do {
            System.out.println("\n===== STOCK TRADING MENU =====");
            System.out.println("1. View Market");
            System.out.println("2. Buy Stock");
            System.out.println("3. Sell Stock");
            System.out.println("4. View Portfolio");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");

            System.out.print("Enter choice: ");
            choice = sc.nextInt();

            switch (choice) {

                case 1:
                    System.out.println("\n--- Market Data ---");
                    for (Stock s : market.values()) {
                        System.out.println(s.name + " : " + s.price);
                    }
                    break;

                case 2:
                    System.out.print("Enter stock name: ");
                    String buyName = sc.next().toUpperCase();

                    if (market.containsKey(buyName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        user.buyStock(market.get(buyName), qty);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 3:
                    System.out.print("Enter stock name: ");
                    String sellName = sc.next().toUpperCase();

                    if (market.containsKey(sellName)) {
                        System.out.print("Enter quantity: ");
                        int qty = sc.nextInt();
                        user.sellStock(market.get(sellName), qty);
                    } else {
                        System.out.println("Stock not found!");
                    }
                    break;

                case 4:
                    user.showPortfolio(market);
                    break;

                case 5:
                    user.showHistory();
                    break;

                case 6:
                    System.out.println("Exiting...");
                    break;

                default:
                    System.out.println("Invalid choice!");
            }

        } while (choice != 6);

        sc.close();
    }
}