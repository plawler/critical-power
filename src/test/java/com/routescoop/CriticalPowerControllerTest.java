package com.routescoop;

import com.amazonaws.serverless.proxy.internal.testutils.AwsProxyRequestBuilder;
import com.amazonaws.serverless.proxy.model.AwsProxyRequest;
import com.amazonaws.serverless.proxy.model.AwsProxyResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.micronaut.http.HttpHeaders;
import io.micronaut.http.HttpMethod;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.MediaType;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriticalPowerControllerTest extends BaseAwsLambdaTest {

    @Test
    void testCalculateCriticalPower() throws JsonProcessingException {
        List<Effort> efforts = Arrays.asList(
            new Effort(180, 400),
            new Effort(360, 350),
            new Effort(720, 300)
        );

        SolveForEffort pace = new SolveForEffort(efforts, new int[]{1200});
        String json = objectMapper.writeValueAsString(pace);
        AwsProxyRequest request = new AwsProxyRequestBuilder("/critical-power", HttpMethod.POST.toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .body(json)
            .build();

        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());

        CpResult result = objectMapper.readValue(response.getBody(), CpResult.class);
        assertEquals(264, result.cp);
    }

}
