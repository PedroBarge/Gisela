public class BankAccount {
    private volatile double balance;

    public BankAccount() {
        balance = 0.0;
    }

    public void deposit(double amount) {
        synchronized (this) {
            balance += amount;
            System.out.println("Deposit: " + amount);
            System.out.println("Balance after deposit: " + balance);
        }
    }

    public void withdraw(double amount) {
        synchronized (this) {
            if (balance >= amount) {
                balance -= amount;
                System.out.println("Withdrawal: " + amount);
                System.out.println("Balance after withdrawal: " + balance);
            } else {
                System.out.println("Try to Withdraw: " + amount);
                System.out.println("Insufficient funds. Withdrawal cancelled.");
            }
        }
    }
}
