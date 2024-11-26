package com.example.myproject;

import com.example.myproject.hello.v1.HelloReply;
import com.example.myproject.hello.v1.HelloRequest;
import com.example.myproject.service.hello.v1.HelloServiceGrpc;
import io.grpc.stub.StreamObserver;

public class HelloService extends HelloServiceGrpc.HelloServiceImplBase {

    @Override
    public void hello(HelloRequest req, StreamObserver<HelloReply> responseObserver) {
        HelloReply reply = HelloReply.newBuilder()
                .setMessage("Hello, " + req.getNameInput() + '!')
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
