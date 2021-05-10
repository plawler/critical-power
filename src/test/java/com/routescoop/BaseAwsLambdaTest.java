package com.routescoop;

import com.amazonaws.serverless.exceptions.ContainerInitializationException;
import com.amazonaws.serverless.proxy.internal.testutils.MockLambdaContext;
import com.amazonaws.services.lambda.runtime.Context;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.micronaut.function.aws.proxy.MicronautLambdaHandler;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseAwsLambdaTest {

    static MicronautLambdaHandler handler;
    static Context lambdaContext = new MockLambdaContext();
    static ObjectMapper objectMapper;

    @BeforeAll
    public static void setupSpec() {
        try {
            handler = new MicronautLambdaHandler();
            objectMapper = handler.getApplicationContext().getBean(ObjectMapper.class);

        } catch (ContainerInitializationException e) {
            e.printStackTrace();
        }
    }

    @AfterAll
    public static void cleanupSpec() {
        handler.getApplicationContext().close();
    }

}
