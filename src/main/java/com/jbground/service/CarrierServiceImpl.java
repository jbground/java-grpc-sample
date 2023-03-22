package com.jbground.service;

import com.google.protobuf.Empty;
import com.jbground.grpc.carrier.Carrier;
import com.jbground.grpc.carrier.CarrierServiceGrpc;
import com.jbground.repository.CarrierRepository;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class CarrierServiceImpl extends CarrierServiceGrpc.CarrierServiceImplBase {

    CarrierRepository carrierRepository = new CarrierRepository();

    @Override
    public void getCarriers(Empty request, StreamObserver<Carrier> responseObserver) {
        List list = carrierRepository.findCarriers();
        for (Object o : list) {
            com.jbground.model.Carrier carrier = (com.jbground.model.Carrier) o;
            Carrier model = Carrier.newBuilder()
                    .setName(carrier.getName())
                    .setMachineName(carrier.getMachineName())
                    .setKind(carrier.getKind())
                    .setState(carrier.getState())
                    .setType(carrier.getType())
                    .setUnitName(carrier.getUnitName())
                    .build();
            responseObserver.onNext(model);
        }

        responseObserver.onCompleted();

    }

    @Override
    public void getCarriersByMachineName(Carrier request, StreamObserver<Carrier> responseObserver) {
        String machineName = request.getMachineName();
        carrierRepository.findCarriersByMachineName();

//        responseObserver.onNext();
        responseObserver.onCompleted();
    }

    @Override
    public void getCarrierByName(Carrier request, StreamObserver<Carrier> responseObserver) {


        //        responseObserver.onNext();
        responseObserver.onCompleted();
    }
}
