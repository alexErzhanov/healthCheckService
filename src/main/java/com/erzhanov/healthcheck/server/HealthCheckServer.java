package com.erzhanov.healthcheck.server;

import java.io.IOException;

import io.grpc.Server;
import io.grpc.ServerBuilder;

public class HealthCheckServer {

    public static void main(String[] args) throws IOException, InterruptedException {
        Server server = ServerBuilder
                .forPort(8080)
                .addService(new HelthCheckServiceImpl()).build();

        server.start();
        server.awaitTermination();
    }
}
