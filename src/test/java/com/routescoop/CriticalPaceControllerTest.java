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

public class CriticalPaceControllerTest extends BaseAwsLambdaTest {

    @Test
    void testCalculateCriticalPace() throws JsonProcessingException {
        List<Effort> efforts = Arrays.asList(
            new Effort(157, 800),
            new Effort(352, 1600),
            new Effort(755, 3200)
        );

        SolveForEffort power = new SolveForEffort(efforts, new int[]{150});
        String json = objectMapper.writeValueAsString(power);
        System.out.println(json);
        AwsProxyRequest request = new AwsProxyRequestBuilder("/critical-pace", HttpMethod.POST.toString())
            .header(HttpHeaders.CONTENT_TYPE, MediaType.APPLICATION_JSON)
            .body(json)
            .build();

        AwsProxyResponse response = handler.handleRequest(request, lambdaContext);
        assertEquals(HttpStatus.OK.getCode(), response.getStatusCode());

        CpResult result = objectMapper.readValue(response.getBody(), CpResult.class);
        assertEquals(4.006895587755673, result.cp);
        assertEquals(779, result.predictedEfforts.get(0).getOutput());
    }
}
