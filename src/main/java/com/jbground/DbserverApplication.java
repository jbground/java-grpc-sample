//package com.jbground;
//
//
//import com.jbground.dbserver.service.UserServiceImpl;
//import com.jbground.grpc.User;
//import com.jbground.grpc.UserIdx;
//import com.jbground.grpc.UserServiceGrpc;
//import io.grpc.ManagedChannel;
//import io.grpc.ManagedChannelBuilder;
//import io.grpc.Server;
//import io.grpc.ServerBuilder;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.SpringApplication;
//import org.springframework.boot.autoconfigure.SpringBootApplication;
//
//import java.io.IOException;
//
//@SpringBootApplication
//public class DbserverApplication {
//
//	public static void main(String[] args) {
//		SpringApplication.run(DbserverApplication.class, args);
//
////		int port = 8080;
////		Server server = ServerBuilder.forPort(port).addService(new UserServiceImpl()).build();
////
////		try {
////			server.start();
////		} catch (IOException e) {
////			e.printStackTrace();
////			return;
////		}
////
////		Runtime.getRuntime().addShutdownHook(new Thread(() ->{
////			System.err.println("Server: Shutting down gRPC server");
////			server.shutdown();
////			System.err.println("Server: Server shutdown");
////		}));
//
//
//	}
//
//}
