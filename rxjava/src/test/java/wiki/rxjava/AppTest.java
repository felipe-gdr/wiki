package wiki.rxjava;

import io.reactivex.Maybe;
import io.reactivex.Observable;
import io.reactivex.Single;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AppTest {

    @Test
    public void test1() {
        Observable.create(emitter -> {
            while (!emitter.isDisposed()) {
                long time = System.currentTimeMillis();
                emitter.onNext(time);
                if (time % 2 != 0) {
                    emitter.onError(new IllegalStateException("Odd millisecond!"));
                    break;
                }
            }
        }).subscribe(System.out::println, Throwable::printStackTrace);
    }

    @Test
    public void test2() {
        Observable.concat(Observable.just("Hello"),
                Observable.just("reactive"),
                Observable.just("world"))
                .subscribe(System.out::println);
    }

    @Test
    public void test3() {
        Maybe<Integer> m0 = Maybe.just(0);
        Maybe<Integer> m1 = Maybe.empty();
        Maybe<Integer> m2 = Maybe.just(2);

        List<Maybe<Integer>> maybes =Arrays.asList(m0, m1, m2);

        final Single<List<Integer>> sequence = sequence(maybes);

        sequence.subscribe(System.out::println);

    }

    private <T> Single<List<T>> sequence(List<Maybe<T>> input) {
        return Observable.fromIterable(input)
                .flatMap(Maybe::toObservable)
                .reduce(new ArrayList<>(), (a, b) -> {
                    a.add(b);
                    return a;
                });
    }

    @Test
    public void defer() {
        final Single<Integer> just = Single.defer(() -> Single.just(1));


        just.subscribe(System.out::println);

    }

}
