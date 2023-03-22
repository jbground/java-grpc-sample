package com.jbground.service;

import com.jbground.grpc.user.User;
import com.jbground.grpc.user.UserIdx;
import com.jbground.grpc.user.UserServiceGrpc;
import io.grpc.Status;
import io.grpc.StatusException;
import io.grpc.stub.StreamObserver;

import java.util.HashMap;
import java.util.Map;

public class UserServiceImpl extends UserServiceGrpc.UserServiceImplBase {

    private final Map<Long, User> userMap = new HashMap<>();
    private long idxCounter = 1;

    @Override
    public void setUser(User request, StreamObserver<UserIdx> responseObserver) {
        request = request.toBuilder().setIdx(idxCounter++).build();
        userMap.put(request.getIdx(), request);

//        UserIdx response = UserIdx.newBuilder().setIdx(request.getIdx()).build();
        UserIdx response = UserIdx.newBuilder().addIdx(request.getIdx()).build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void getUser(UserIdx request, StreamObserver<User> responseObserver) {

//        long userIdx = request.getIdx();
        long userIdx = request.toBuilder().getIdx(0);

        if (userMap.containsKey(userIdx)) {
            responseObserver.onNext(userMap.get(userIdx));
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(new StatusException(Status.NOT_FOUND));
        }

    }
    /*
        Stub의 종류
        BlockingStub : 동기통신, 서버로부터 응답이 올 때까지 대기, UnaryRPC와 Server Streaming RPC에서만 사용 가능
        AsyncStub : 비동기통신, 서버로부터 오는 응답을 StreamObserver 객체가 대신 받아서 처리, 모든 방식에서 사용 가능
        FutureStub : 비동기통신, 서버로부터 응답 도달에 상관 없이 일단 ListenableFuture로 래핑된 객체 반환 서버로부터 오는 응답이 오면
                    ListenableFuture객체를 통해 전달받은 메세지를 언래핑할 수 있음. UnaryRPC에서만 사용 가능
     */

    // Client Streaming RPC
    @Override
    public StreamObserver<User> setUsers(final StreamObserver<UserIdx> responseObserver) {
        return new StreamObserver<User>() {

            final UserIdx.Builder responseBuilder = UserIdx.newBuilder();

            @Override
            public void onNext(User user) {
                user = user.toBuilder().setIdx(idxCounter++).build();
                userMap.put(user.getIdx(), user);
                responseBuilder.addIdx(user.getIdx());

            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onNext(responseBuilder.build());
                responseObserver.onCompleted();
            }
        };
    }

    // Server Streaming RPC
    @Override
    public void getUsers(UserIdx request, StreamObserver<User> responseObserver) {
        for (long idx : request.getIdxList()) {
            if (userMap.containsKey(idx)) {
                responseObserver.onNext(userMap.get(idx));
            } else {
                responseObserver.onError(new StatusException(Status.NOT_FOUND));
            }
        }
        responseObserver.onCompleted();
    }

    // Bidirectional Streaming RPC
    @Override
    public StreamObserver<UserIdx> getUsersRealtime(StreamObserver<User> responseObserver) {
        return new StreamObserver<UserIdx>() {
            @Override
            public void onNext(UserIdx userIdx) {
                for (long idx : userIdx.getIdxList()) {
                    if (userMap.containsKey(idx)) {
                        responseObserver.onNext(userMap.get(idx));
                    } else {
                        responseObserver.onError(new StatusException(Status.NOT_FOUND));
                    }
                }
            }

            @Override
            public void onError(Throwable t) {
                responseObserver.onError(t);
            }

            @Override
            public void onCompleted() {
                responseObserver.onCompleted();
            }
        };
    }
}
