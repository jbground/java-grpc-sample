package com.jbground.service;

import com.google.protobuf.Empty;
import com.jbground.grpc.carrier.Carrier;
import com.jbground.grpc.carrier.CarrierServiceGrpc;
import io.grpc.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

class CarrierServiceTest {

    @BeforeAll
    static void beforeAll(){
        int port = 8080;
        Server server = ServerBuilder.forPort(port).addService(new CarrierServiceImpl()).build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

//        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
//            System.err.println("Server: Shutting down gRPC server");
//            server.shutdown();
//            System.err.println("Server: Server shutdown");
//        }));
    }

    @Test
    void getCarriers(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        CarrierServiceGrpc.CarrierServiceBlockingStub stub = CarrierServiceGrpc.newBlockingStub(channel);

        // Client: Server-side Streaming RPC
        System.out.println("(3) Server-side Streaming RPC");
        try {
            Iterator<Carrier> resultList = stub.getCarriers(Empty.newBuilder().build());
            while (resultList.hasNext()) {
                Carrier carrier = resultList.next();
                System.out.println(carrier.toString());
            }
        } catch (StatusRuntimeException ignored) { }



        // Release
        channel.shutdown();
        Runtime.getRuntime().exit(0);

    }

}