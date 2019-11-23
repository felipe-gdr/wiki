package wiki.reactor.playgrounds;

import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import wiki.reactor.playgrounds.model.User;

import java.time.Duration;
import java.util.function.Supplier;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * Learn how to use StepVerifier to test Mono, Flux or any other kind of Reactive Streams Publisher.
 *
 * @author Sebastien Deleuze
 * @see <a href="https://projectreactor.io/docs/test/release/api/reactor/test/StepVerifier.html">StepVerifier Javadoc</a>
 */
public class Part03StepVerifier {

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then completes successfully.
    void expectFooBarComplete(Flux<String> flux) {
        StepVerifier
                .create(flux)
                .expectNext("foo", "bar")
                .verifyComplete();

    }

//========================================================================================

    // TODO Use StepVerifier to check that the flux parameter emits "foo" and "bar" elements then a RuntimeException error.
    void expectFooBarError(Flux<String> flux) {
        StepVerifier
                .create(flux)
                .expectNext("foo", "bar")
                .verifyError(RuntimeException.class);
    }

    //========================================================================================
    // TODO Use StepVerifier to check that the flux parameter emits a User with "swhite"username
    // and another one with "jpinkman" then completes successfully.
    void expectSkylerJesseComplete(Flux<User> flux) {
        StepVerifier
                .create(flux)
                .assertNext(user -> assertThat(user.getUsername(), is("swhite")))
                .assertNext(user -> assertThat(user.getUsername(), is("jpinkman")))
                .verifyComplete();
    }

//========================================================================================

    // TODO Expect 10 elements then complete and notice how long the test takes.
    void expect10Elements(Flux<Long> flux) {
        final Duration duration = StepVerifier
                .create(flux)
                .expectNextCount(10)
                .verifyComplete();

        System.out.println(duration);
    }

//========================================================================================

    // TODO Expect 3600 elements at intervals of 1 second, and verify quicker than 3600s
    // by manipulating virtual time thanks to StepVerifier#withVirtualTime, notice how long the test takes
    void expect3600Elements(Supplier<Flux<Long>> supplier) {
        StepVerifier.withVirtualTime(supplier)
                .expectSubscription()
                .thenAwait(Duration.ofHours(1))
                .expectNextCount(3600)
                .verifyComplete();
    }

    private void fail() {
        throw new AssertionError("workshop not implemented");
    }

    public static void main(String[] args) {
        final Part03StepVerifier part03StepVerifier = new Part03StepVerifier();

        part03StepVerifier.expect10Elements(
                Flux.interval(Duration.ofMillis(10)).log().take(10)
        );

        part03StepVerifier.expect3600Elements(
                () -> Flux.interval(Duration.ofSeconds(1)).take(3600)
        );


    }
}
