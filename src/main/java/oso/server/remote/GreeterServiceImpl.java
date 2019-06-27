package oso.server.remote;

import io.grpc.stub.StreamObserver;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-26
 */
public class GreeterServiceImpl extends GreeterGrpc.GreeterImplBase implements GrpcService {

    @Override
    public void sayHello(Hello.HelloRequest req, StreamObserver<Hello.HelloReply> responseObserver) {
        Hello.HelloReply reply = Hello.HelloReply.newBuilder().setMessage("Hello " + req.getName()).build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
