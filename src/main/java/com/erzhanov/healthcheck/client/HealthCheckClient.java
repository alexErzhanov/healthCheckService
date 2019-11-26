package com.erzhanov.healthcheck.client;

import com.erzhanov.healthcheck.HealthCheckRequest;
import com.erzhanov.healthcheck.HealthCheckResponse;
import com.erzhanov.healthcheck.HealthCheckServiceGrpc;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;

public class HealthCheckClient {
    public static void main(String[] args) throws InterruptedException {
        ManagedChannel channel = ManagedChannelBuilder.forAddress("localhost", 8080)
                .usePlaintext()
                .build();
        HealthCheckServiceGrpc.HealthCheckServiceBlockingStub stub = HealthCheckServiceGrpc.newBlockingStub(channel);

        HealthCheckResponse response = stub.ping(HealthCheckRequest.newBuilder()
                .setUrl("google.com")
                .build());

        System.out.println("Response received from server:\n" + response);

        channel.shutdown();
    }
}
