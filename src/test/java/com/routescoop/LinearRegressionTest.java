package com.routescoop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LinearRegressionTest {

    @Test
    public void testPaceRegression() {
        List<Observation> observations = Arrays.asList(
            new Observation(157, 800),
            new Observation(352, 1600),
            new Observation(755, 3200)
        );
        LinearRegression regression = new LinearRegression(observations);
        assertEquals(4.006895587755673, regression.slope());
        assertEquals(178.42799235894321, regression.intercept());
        assertEquals(779.4623305222941, regression.predict(150));
    }

    @Test
    public void testPowerRegression() {
        List<Observation> observations = Arrays.asList(
            new Observation(180, 400 * 180), // multiply watts * duration to convert to kjs
            new Observation(360, 350 * 360),
            new Observation(720, 300 * 720)
        );
        LinearRegression regression = new LinearRegression(observations);
        assertEquals(27000.0, regression.intercept());
        assertEquals(279.2857142857143, regression.predict(1800) / 1800); // predict 30 minute power
        assertEquals(264.2857142857143, regression.slope());
    }

}
