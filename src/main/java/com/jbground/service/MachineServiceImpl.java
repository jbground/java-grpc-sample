package com.jbground.service;

import com.google.protobuf.Empty;
import com.jbground.grpc.machine.Machine;
import com.jbground.grpc.machine.MachineServiceGrpc;
import com.jbground.repository.MachineRepository;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class MachineServiceImpl extends MachineServiceGrpc.MachineServiceImplBase {

    MachineRepository machineRepository = new MachineRepository();

    @Override
    public void getMachines(Empty request, StreamObserver<Machine> responseObserver) {
        List list = machineRepository.findMachines();
        for (Object o : list) {
            com.jbground.model.Machine m = (com.jbground.model.Machine) o;
            Machine model = Machine.newBuilder()
                    .setName(m.getName())
                    .setShopname(m.getShopName())
                    .setAreaname(m.getAreaName())
                    .setControllername(m.getControllerName())
                    .setCarriertype(m.getCarrierType())
                    .setMachinekind(m.getMachineKind())
                    .build();
                    responseObserver.onNext(model);
        }

        responseObserver.onCompleted();
    }

    @Override
    public void getMachinesByControllerName(Machine request, StreamObserver<Machine> responseObserver) {
        super.getMachinesByControllerName(request, responseObserver);
    }

    @Override
    public void getMachineByName(Machine request, StreamObserver<Machine> responseObserver) {
        super.getMachineByName(request, responseObserver);
    }
}
