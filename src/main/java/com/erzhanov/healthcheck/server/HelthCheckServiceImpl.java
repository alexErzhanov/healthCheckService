package com.erzhanov.healthcheck.server;

import static com.erzhanov.healthcheck.HealthCheckServiceGrpc.HealthCheckServiceImplBase;

import com.erzhanov.healthcheck.HealthCheckRequest;
import com.erzhanov.healthcheck.HealthCheckResponse;

import io.grpc.stub.StreamObserver;

public class HelthCheckServiceImpl extends HealthCheckServiceImplBase {

    @Override
    public void ping(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        HealthCheckResponse response = HealthCheckResponse.newBuilder()
                .setCode(request.getUrl() + " 200 OK")
                .setTime("200ms")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
