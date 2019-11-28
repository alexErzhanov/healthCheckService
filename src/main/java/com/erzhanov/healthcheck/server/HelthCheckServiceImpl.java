package com.erzhanov.healthcheck.server;

import static com.erzhanov.healthcheck.HealthCheckServiceGrpc.HealthCheckServiceImplBase;

import org.apache.commons.lang3.time.StopWatch;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import com.erzhanov.healthcheck.HealthCheckRequest;
import com.erzhanov.healthcheck.HealthCheckResponse;

import io.grpc.stub.StreamObserver;

public class HelthCheckServiceImpl extends HealthCheckServiceImplBase {

    @Override
    public void ping(HealthCheckRequest request, StreamObserver<HealthCheckResponse> responseObserver) {
        HttpGet req = new HttpGet("http://" + request.getUrl());
        StopWatch watch = new StopWatch();
        int statusCode = 0;

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            watch.start();
            CloseableHttpResponse res = httpClient.execute(req);
            statusCode = res.getStatusLine().getStatusCode();

        } catch (Exception e) {
            e.printStackTrace();

        } finally {
            watch.stop();
        }

        HealthCheckResponse response = HealthCheckResponse.newBuilder()
                .setCode(String.valueOf(statusCode))
                .setTime(watch.toString())
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
