package wiki.reactor.playgrounds;

import org.assertj.core.api.Assertions;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import reactor.core.publisher.Flux;
import reactor.test.StepVerifier;
import wiki.reactor.playgrounds.model.User;

import java.net.UnknownServiceException;

import static org.assertj.core.api.Assertions.assertThat;

public class Part06Request {
//    ReactiveRepository<User> repository = new ReactiveUserRepository();

//========================================================================================

    // TODO Create a StepVerifier that initially requests all values and expect 4 values to be received
    StepVerifier requestAllExpectFour(Flux<User> flux) {
        return StepVerifier
                .create(flux)
                .expectSubscription()
                .expectNextCount(4)
                .expectComplete();
    }

//========================================================================================

    // TODO Create a StepVerifier that initially requests 1 value and expects User.SKYLER then requests another value and expects User.JESSE.
    StepVerifier requestOneExpectSkylerThenRequestOneExpectJesse(Flux<User> flux) {
        return StepVerifier.withVirtualTime(() -> flux)
                .expectSubscription()
                .thenRequest(2)
                .assertNext(user -> assertThat(user.getUsername()).isEqualTo("swhite"))
                .thenRequest(1)
                .assertNext(user -> assertThat(user.getUsername()).isEqualTo("jpinkman"))
                .thenCancel();
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints automatically logs for all Reactive Streams signals
    Flux<User> fluxWithLog() {
        return null;
    }

//========================================================================================

    // TODO Return a Flux with all users stored in the repository that prints "Starring:" on subscribe, "firstname lastname" for all values and "The end!" on complete
    Flux<User> fluxWithDoOnPrintln() {
        return null;
    }

    public static void main(String[] args) {
        final Part06Request part06Request = new Part06Request();

        final StepVerifier stepVerifier = part06Request.requestOneExpectSkylerThenRequestOneExpectJesse(
                Flux.just(User.SKYLER, User.JESSE).log()
        );

        stepVerifier.verify();
    }
}
