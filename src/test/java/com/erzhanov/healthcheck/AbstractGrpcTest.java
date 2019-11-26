package com.erzhanov.healthcheck;

import java.io.IOException;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;

import com.erzhanov.healthcheck.server.HelthCheckServiceImpl;

import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import io.grpc.ServerBuilder;

public class AbstractGrpcTest {
    private ManagedChannel channel;

    @BeforeAll
    static void createServer() throws IOException {
        ServerBuilder.forPort(8080).addService(new HelthCheckServiceImpl()).build().start();
    }

    @BeforeEach
    void setup() {
        channel = ManagedChannelBuilder
                .forAddress("localhost", 8080)
                .usePlaintext()
                .build();
    }

    @AfterEach
    void cleanup() {
        channel.shutdownNow();
    }

    protected ManagedChannel getChannel() {
        return channel;
    }
}
