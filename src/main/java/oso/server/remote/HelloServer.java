package oso.server.remote;

import com.google.common.collect.Lists;
import io.grpc.BindableService;
import io.grpc.Server;
import io.grpc.ServerBuilder;
import io.grpc.protobuf.services.ProtoReflectionService;

import java.io.IOException;
import java.util.List;

/**
 * @description:
 * @author: weichen
 * @date: 2019-06-26
 */
public class HelloServer {

    private List<GrpcService> services;

    private Server server;

    public void start(int port) throws IOException, InterruptedException {
        GreeterServiceImpl greeterService = new GreeterServiceImpl();
        services = Lists.newArrayList();
        services.add(greeterService);

        ServerBuilder<?> serverBuilder = ServerBuilder.forPort(port);
        for (GrpcService grpcService : services) {
            if (!(grpcService instanceof BindableService)) {
                throw new RuntimeException("GrpcService should only used for grpc BindableService; found wrong usage of GrpcService for service: " + grpcService.getClass().getSimpleName());
            }
            serverBuilder.addService((BindableService) grpcService);
        }

        this.server = ((ServerBuilder<?>) serverBuilder)
                .addService(ProtoReflectionService.newInstance())
                .build()
                .start();

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            // Use stderr here since the logger may have been reset by its JVM shutdown hook.
            System.err.println("*** shutting down gRPC server since JVM is shutting down");
            stop();
            System.err.println("*** server shut down");
        }));

        blockUntilShutdown();
    }

    public void stop() {
        if (this.server != null) {
            this.server.shutdown();
        }
    }

    /**
     * Wait for main method. the gprc services uses daemon threads
     * @throws InterruptedException
     */
    public void blockUntilShutdown() throws InterruptedException {
        if (this.server != null) {
            this.server.awaitTermination();
        }
    }
}
