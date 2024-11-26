package com.example.myproject;

import com.linecorp.armeria.server.Server;
import com.linecorp.armeria.server.ServerBuilder;
import com.linecorp.armeria.server.docs.DocService;
import com.linecorp.armeria.server.grpc.GrpcService;
import com.linecorp.armeria.server.grpc.GrpcServiceBuilder;

import java.util.concurrent.CompletableFuture;

/**
 * This application compares two numbers, using the Ints.compare
 * method from Guava.
 */
public class SimpleServer {
  public static void main(String... args) throws Exception {
    System.out.println("Hallo");

    ServerBuilder sb = Server.builder();
    GrpcServiceBuilder grpcBuilder = GrpcService.builder();
    grpcBuilder.enableHttpJsonTranscoding(true);
    sb.service(grpcBuilder.addService(new HelloService()).build());

    sb.serviceUnder("/docs", new DocService());

    sb.http(25000);
    Server server = sb.build();
    CompletableFuture<Void> future = server.start();

    future.join();
  }
}
