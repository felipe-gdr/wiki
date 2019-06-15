package wiki.concurrency.jcip.chapter10;

public class Account {
    private int id;
    private double balance;

    public Account(int id, double initialBalance) {
        this.id = id;
        this.balance = initialBalance;
    }

    public void credit(double amount) {
        this.balance += amount;
    }

    public void debit(double amount) {
        this.balance += amount;
    }

    public double getBalance() {
        return balance;
    }

    public int getId() {
        return id;
    }
}
