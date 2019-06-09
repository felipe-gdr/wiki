package wiki.concurrency;

public class BrokenTaskRunner implements Runnable {
    private final Runnable runnable;
    private final long sleep;

    private volatile boolean cancelled;

    public BrokenTaskRunner(Runnable runnable) {
        this.runnable = runnable;
        this.sleep = 0L;
    }

    public BrokenTaskRunner(Runnable runnable, long sleep) {
        this.runnable = runnable;
        this.sleep = sleep;
    }

    @Override
    public void run() {
        while (!this.cancelled) {
            this.runnable.run();

            try {
                Thread.sleep(this.sleep);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        System.out.println("Exiting " + this.getClass().getSimpleName());
    }

    public void cancel() {
        this.cancelled = true;
    }
}
