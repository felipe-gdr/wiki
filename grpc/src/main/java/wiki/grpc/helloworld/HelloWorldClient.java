package wiki.grpc.helloworld;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.examples.routeguide.RouteGuideGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Scanner;
import java.util.concurrent.TimeUnit;

public class HelloWorldClient {
    private final ManagedChannel channel;
    private final HelloWorldGrpc.HelloWorldBlockingStub blockingStub;
    private final HelloWorldGrpc.HelloWorldStub asyncStub;

    public HelloWorldClient() {
        this.channel = ManagedChannelBuilder
                .forAddress("localhost", 8980)
                .usePlaintext()
                .build();

        this.blockingStub = HelloWorldGrpc.newBlockingStub(channel);
        this.asyncStub = HelloWorldGrpc.newStub(channel);
    }

    public static void main(String[] args) throws InterruptedException {
        final HelloWorldClient client = new HelloWorldClient();

        System.out.println("Type a message to send to the server\n");

        while(true) {
            final Scanner scanner = new Scanner(System.in);
            final String input = scanner.nextLine();

            if("".equals(input) || input == null) {
                client.shutdown();
                System.exit(0);
            }

            SimpleMessage message = SimpleMessage
                    .newBuilder()
                    .setText(input)
                    .build();

            client.asyncStub.echo(message, new ClientObserver());
            SimpleMessage echo = client.blockingStub.echo(message);
            System.out.println("sync: " + echo.getText());
        }

    }

    private void shutdown() throws InterruptedException {
        channel.shutdown().awaitTermination(5, TimeUnit.SECONDS);
    }

    private static class ClientObserver implements StreamObserver<SimpleMessage> {
        @Override
        public void onNext(SimpleMessage simpleMessage) {
            System.out.println("async: " + simpleMessage.getText());
        }

        @Override
        public void onError(Throwable throwable) {
            throw new RuntimeException("Error", throwable);
        }

        @Override
        public void onCompleted() {
            System.out.println("async: done");
        }
    }

}
