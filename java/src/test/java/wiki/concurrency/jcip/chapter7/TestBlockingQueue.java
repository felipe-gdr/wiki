package wiki.concurrency.jcip.chapter7;

import org.junit.Test;
import wiki.concurrency.jcip.chapter7.TaskRunner;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.function.Consumer;
import java.util.function.Supplier;

import static org.junit.Assert.assertEquals;

public class TestBlockingQueue {
    class QueueProducer<T> extends TaskRunner {
        QueueProducer(final BlockingQueue<T> queue, final Supplier<T> supplier) {
            super(() -> {
                        try {
                            queue.put(supplier.get());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    },
                    100);
        }
    }

    class QueueConsumer<T> extends TaskRunner {
        QueueConsumer(final BlockingQueue<T> queue, final Consumer<T> consumerFunction) {
            super(() -> {
                        try {
                            consumerFunction.accept(queue.take());
                        } catch (InterruptedException e) {
                            Thread.currentThread().interrupt();
                        }
                    },
                    0);
        }
    }

    @Test
    public void producerConsumer() {
        BlockingQueue<Integer> queue = new ArrayBlockingQueue<>(100);

        QueueProducer<Integer> producer = new QueueProducer<>(
                queue,
                () -> (int) (Math.random() * 1000)
        );

        QueueConsumer<Integer> consumer = new QueueConsumer<>(
                queue,
                System.out::println
        );

        producer.start();
        consumer.start();

        new Thread(() -> {
            try {
                Thread.sleep(500);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

            consumer.cancel();
            producer.cancel();
        }).start();

        try {
            producer.join();
            consumer.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        assertEquals(
                "Producer thread terminated",
                Thread.State.TERMINATED, producer.getState()
        );

        assertEquals(
                "Consumer thread also terminated",
                Thread.State.TERMINATED, consumer.getState()
        );
    }
}
