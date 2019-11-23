package wiki.concurrency.sandbox;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.stream.IntStream;

import static java.util.function.Function.identity;
import static java.util.stream.Collectors.toMap;

public class SynchronizedLists {
    static final Integer LIST_SIZE = 100;
    static final Integer THREADS_COUNT = 10;

    public static void main(String args[]) {
        final Map<Integer, Integer> map = IntStream.range(0, LIST_SIZE).boxed().collect(toMap(identity(), identity()));

        final List<CompletableFuture> futures = new ArrayList<>();

        // async
        for(int i = 0; i < 100; i++)  {
            futures.add(CompletableFuture.runAsync(() -> increment(map)));
            futures.add(CompletableFuture.runAsync(() -> decrement(map)));
        }

        // sync
//        for(int i = 0; i < 100; i++)  {
//            increment(map);
//            decrement(map);
//        }

        CompletableFuture.allOf(futures.toArray(new CompletableFuture[0]));

        map.forEach((k, v) -> {
            System.out.printf("%s:%s%n", k, v);

            assert k.equals(v);
        });
    }

    static void increment(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            map.put(e.getKey(), e.getValue() + 1);

        }
    }

    static void decrement(Map<Integer, Integer> map) {
        for (Map.Entry<Integer, Integer> e : map.entrySet()) {
            map.put(e.getKey(), e.getValue() - 1);

        }
    }
}
