package oso.server.remote;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;


/**
 * @description:
 * @author: weichen
 * @date: 2019-06-27
 */
public class HelloWorldClient {

    private GreeterGrpc.GreeterBlockingStub helloWorldServiceBlockingStub;

    public HelloWorldClient() {
        ManagedChannel managedChannel = ManagedChannelBuilder
                .forAddress("localhost", 10000).usePlaintext().build();

        helloWorldServiceBlockingStub =
                GreeterGrpc.newBlockingStub(managedChannel);
    }

    public String sayHello() {
        Hello.HelloRequest helloRequest = Hello.HelloRequest.newBuilder().setName("grpc").build();
        Hello.HelloReply reply =
                helloWorldServiceBlockingStub.sayHello(helloRequest);

        return reply.getMessage();
    }
}
