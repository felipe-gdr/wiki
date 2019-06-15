package wiki.concurrency.jcip.chapter10;

public final class TransferManager {
    /**
     * Transfer money in a way that is very prone to deadlock
     *
     */
    public static void transferMoneyWithDeadlock(Account to, Account from, double amount) {
        // Nested lock acquisition done this way can easily result on a deadlock situation
        synchronized (to) {
            synchronized (from) {
                executeTransfer(to, from, amount);
            }
        }
    }

    private static final Object tie = new Object();

    /**
     * Transfer money in a way that is deadlock free
     *
     */
    public static void transferMoneyWithNoDeadlock(Account to, Account from, double amount) {
        // Uses induce lock acquisition to force nested locks that are always on the same order
        // Uses account id to define the order. But it could use the object as well (ie. with System.identityHashCode())
        if (to.getId() > from.getId()) {
            synchronized (to) {
                synchronized (from) {
                    executeTransfer(to, from, amount);
                }
            }
        } else if (to.getId() < from.getId()) {
            synchronized (from) {
                synchronized (to) {
                    executeTransfer(to, from, amount);
                }
            }
        } else {
            synchronized (tie) {
                synchronized (from) {
                    synchronized (to) {
                        executeTransfer(to, from, amount);
                    }
                }
            }
        }

    }

    private static void executeTransfer(Account to, Account from, double amount) {
        if (from.getBalance() < amount) {
            throw new IllegalArgumentException("Insufficient funds");
        } else {
            to.credit(amount);
            from.debit(amount);
        }
    }
}
