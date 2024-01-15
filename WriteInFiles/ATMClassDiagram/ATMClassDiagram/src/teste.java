import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;

class User {
    private String name;
    private double balance;

    public User(String name, double balance) {
        this.name = name;
        this.balance = balance;
    }

    public String getName() {
        return name;
    }

    public double getBalance() {
        return balance;
    }

    public boolean deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            return true;
        }
        return false;
    }

    public boolean withdraw(double amount) {
        if (amount > 0 && balance >= amount) {
            balance -= amount;
            return true;
        }
        return false;
    }
}

class TransactionLogger {
    private static final String FILE_PATH = "src/Resource/bd.txt";

    public static void logTransaction(String transactionDetails) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_PATH, true))) {
            writer.write(getCurrentTimeStamp() + " - " + transactionDetails + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static String getCurrentTimeStamp() {
        LocalDateTime currentTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return currentTime.format(formatter);
    }
}

class ATM {
    private Map<String, User> users;

    public ATM() {
        users = new HashMap<>();
    }

    public void addUser(User user) {
        users.put(user.getName(), user);
    }

    public boolean transfer(String senderName, String receiverName, double amount) {
        User sender = users.get(senderName);
        User receiver = users.get(receiverName);

        if (sender != null && receiver != null) {
            if (sender.withdraw(amount)) {
                if (receiver.deposit(amount)) {
                    TransactionLogger.logTransaction("Transfer from " + senderName + " to " + receiverName + ": Success");
                    return true;
                } else {
                    sender.deposit(amount); // Revert sender's withdrawal
                    TransactionLogger.logTransaction("Transfer from " + senderName + " to " + receiverName + ": Error - Receiver deposit failed");
                }
            } else {
                TransactionLogger.logTransaction("Transfer from " + senderName + " to " + receiverName + ": Error - Insufficient balance");
            }
        } else {
            TransactionLogger.logTransaction("Transfer from " + senderName + " to " + receiverName + ": Error - User not found");
        }
        return false;
    }

    public boolean deposit(String userName, double amount) {
        User user = users.get(userName);

        if (user != null) {
            if (user.deposit(amount)) {
                TransactionLogger.logTransaction("Deposit for " + userName + ": Success");
                return true;
            } else {
                TransactionLogger.logTransaction("Deposit for " + userName + ": Error - Deposit failed");
            }
        } else {
            TransactionLogger.logTransaction("Deposit for " + userName + ": Error - User not found");
        }
        return false;
    }

    public boolean withdraw(String userName, double amount) {
        User user = users.get(userName);

        if (user != null) {
            if (user.withdraw(amount)) {
                TransactionLogger.logTransaction("Withdrawal for " + userName + ": Success");
                return true;
            } else {
                TransactionLogger.logTransaction("Withdrawal for " + userName + ": Error - Insufficient balance");
            }
        } else {
            TransactionLogger.logTransaction("Withdrawal for " + userName + ": Error - User not found");
        }
        return false;
    }
}

public class teste {
    public static void main(String[] args) {
        User user1 = new User("Alice", 500);
        User user2 = new User("Bob", 1000);

        ATM atm = new ATM();
        atm.addUser(user1);
        atm.addUser(user2);

        atm.transfer("Alice", "Bob", 200);
        atm.deposit("Bob", 100);
        atm.withdraw("Alice", 300);
    }
}
