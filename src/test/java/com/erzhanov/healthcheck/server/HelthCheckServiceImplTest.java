package com.erzhanov.healthcheck.server;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import org.junit.jupiter.api.Test;

import com.erzhanov.healthcheck.AbstractGrpcTest;
import com.erzhanov.healthcheck.HealthCheckRequest;
import com.erzhanov.healthcheck.HealthCheckResponse;
import com.erzhanov.healthcheck.HealthCheckServiceGrpc;

public class HelthCheckServiceImplTest extends AbstractGrpcTest {

    @Test
    void shouldReturnResponseWithBlockingCall() {
        //given
        HealthCheckResponse expected = HealthCheckResponse.newBuilder()
                .setCode("200")
                .build();

        //when
        HealthCheckServiceGrpc.HealthCheckServiceBlockingStub stub =
                HealthCheckServiceGrpc.newBlockingStub(getChannel());
        HealthCheckResponse response = stub.ping(HealthCheckRequest.newBuilder().setUrl("google.com").build());

        //then
        assertThat(response.getCode()).isEqualTo(expected.getCode());
    }
}