package oso.server.remote;

import io.grpc.stub.StreamObserver;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-26
 */
public interface GrpcService {
    public void sayHello(Hello.HelloRequest req, StreamObserver<Hello.HelloReply> responseObserver);
}
