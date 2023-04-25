package com.jbground;

import com.jbground.service.UserServiceImpl;
import io.grpc.*;
import io.grpc.stub.StreamObserver;

import java.io.IOException;
import java.util.Iterator;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class GrpcApplication {

    public static void main(String[] args) {
        int port = 8080;
        Server server = ServerBuilder.forPort(port).addService(new UserServiceImpl()).build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            System.err.println("Server: Shutting down gRPC server");
            server.shutdown();
            System.err.println("Server: Server shutdown");
        }));
    }
}
