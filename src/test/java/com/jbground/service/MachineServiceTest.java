package com.jbground.service;

import com.google.protobuf.Empty;
import com.jbground.grpc.carrier.Carrier;
import com.jbground.grpc.carrier.CarrierServiceGrpc;
import com.jbground.grpc.machine.Machine;
import com.jbground.grpc.machine.MachineServiceGrpc;
import io.grpc.*;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.Iterator;

public class MachineServiceTest {

    @BeforeAll
    static void beforeAll() {
        int port = 8080;
        Server server = ServerBuilder.forPort(port)
                .addService(new CarrierServiceImpl())
                .addService(new MachineServiceImpl())
                .build();

        try {
            server.start();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
    }

    @Test
    void getMachines(){
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080).usePlaintext().build();
        MachineServiceGrpc.MachineServiceBlockingStub stub = MachineServiceGrpc.newBlockingStub(channel);

        // Client: Server-side Streaming RPC
        System.out.println("(3) Server-side Streaming RPC");
        try {
            Iterator<Machine> resultList = stub.getMachines(Empty.newBuilder().build());
            while (resultList.hasNext()) {
                Machine machine = resultList.next();
                System.out.println(machine.toString());
            }
        } catch (StatusRuntimeException ignored) { }



        // Release
        channel.shutdown();
        Runtime.getRuntime().exit(0);

    }
}
