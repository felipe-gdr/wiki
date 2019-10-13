package wiki.grpc.helloworld;

import io.grpc.stub.StreamObserver;

public class HelloWorldService extends HelloWorldGrpc.HelloWorldImplBase {
    @Override
    public void echo(SimpleMessage request, StreamObserver<SimpleMessage> responseObserver) {
        final String text = request.getText();

        final SimpleMessage response = SimpleMessage.newBuilder()
                .setText(String.format("echo: %s", text))
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
