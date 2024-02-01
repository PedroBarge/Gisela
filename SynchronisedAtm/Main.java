public class Main {
    public static void main(String[] args) throws InterruptedException {
        BankAccount account = new BankAccount();

        Thread depositThread1 = new Thread(() -> account.deposit(1000));
        Thread depositThread2 = new Thread(() -> account.deposit(300));
        Thread withdrawalThread1 = new Thread(() -> account.withdraw(150));
        Thread withdrawalThread2 = new Thread(() -> account.withdraw(1200));

        depositThread1.start();
        depositThread1.join();
        withdrawalThread1.start();
        withdrawalThread1.join();

        depositThread2.start();
        depositThread1.join();
        withdrawalThread2.start();
        withdrawalThread2.join();

    }
}
