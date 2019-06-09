package wiki.concurrency.jcip.chapter7;

public class TaskRunner extends Thread {
    private final Runnable runnable;
    private final long sleep;

    public TaskRunner(Runnable runnable) {
        this.runnable = runnable;
        this.sleep = 0L;
    }

    public TaskRunner(Runnable runnable, long sleep) {
        this.runnable = runnable;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        while (true) {
//            while (!Thread.currentThread().isInterrupted()) {
            this.runnable.run();

            try {
                /*
                 * "Some methods, such as wait, sleep, and join, take such requests seriously, throwing an exception
                 * when they receive an interrupt request or encounter an already set interrupt status upon entry"
                 *
                 * Java Concurrency in Practice, Chapter 7
                 */
                Thread.sleep(this.sleep);
            } catch (InterruptedException e) {
                System.out.println("Exiting " + this.getClass().getSimpleName());
                Thread.currentThread().interrupt();
                return;
            }
        }
    }

    public void cancel() {
        interrupt();
    }
}
