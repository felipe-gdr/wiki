package wiki.concurrency.jcip.chapter10;

import org.junit.Test;

import java.util.Random;

public class TestTransferMoney {
    private static final long TEST_TIMEOUT = 10000;

    // This test will fail
    @Test(timeout = TEST_TIMEOUT)
    public void executeTransferWithDeadlock() {
        final int NUM_THREADS = 20;
        final int NUM_ACCOUNTS = 5;
        final int NUM_ITERATIONS = 1000000;

        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100000);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                System.out.println(String.format("Starting thread %s", Thread.currentThread().getName()));
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = rnd.nextInt(NUM_ACCOUNTS);
                    int toAccount = rnd.nextInt(NUM_ACCOUNTS);

                    double amount = rnd.nextInt(1000);

                    TransferManager.transferMoneyWithDeadlock(accounts[toAccount], accounts[fromAccount], amount);
                }
                System.out.println(String.format("Thread %s finished all transactions", Thread.currentThread().getName()));
            }
        }

        final Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new TransferThread();

            thread.start();

            threads[i] = thread;
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    // This test will pass
    @Test(timeout = TEST_TIMEOUT)
    public void executeTransferSafeFromDeadlock() {
        final int NUM_THREADS = 20;
        final int NUM_ACCOUNTS = 5;
        final int NUM_ITERATIONS = 1000000;


        final Random rnd = new Random();
        final Account[] accounts = new Account[NUM_ACCOUNTS];

        for (int i = 0; i < accounts.length; i++) {
            accounts[i] = new Account(i, 100000);
        }

        class TransferThread extends Thread {
            @Override
            public void run() {
                System.out.println(String.format("Starting thread %s", Thread.currentThread().getName()));
                for (int i = 0; i < NUM_ITERATIONS; i++) {
                    int fromAccount = rnd.nextInt(NUM_ACCOUNTS);
                    int toAccount = rnd.nextInt(NUM_ACCOUNTS);

                    double amount = rnd.nextInt(1000);

                    TransferManager.transferMoneyWithNoDeadlock(accounts[toAccount], accounts[fromAccount], amount);
                }
                System.out.println(String.format("Thread %s finished all transactions", Thread.currentThread().getName()));
            }
        }

        final Thread[] threads = new Thread[NUM_THREADS];

        for (int i = 0; i < NUM_THREADS; i++) {
            Thread thread = new TransferThread();

            thread.start();

            threads[i] = thread;
        }

        for (int i = 0; i < NUM_THREADS; i++) {
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
