package com.routescoop;

import org.apache.commons.math3.stat.regression.SimpleRegression;

import java.util.List;

public class LinearRegression {

    private final SimpleRegression regression;

    public LinearRegression(List<Observation> observations) {
        assert(observations.size() >= 2);
        regression = new SimpleRegression();
        observations.forEach(observation -> regression.addData(observation.x, observation.y));
    }

    public double slope() {
        return regression.getSlope();
    }

    public double intercept() {
        return regression.getIntercept();
    }

    public double rSquare() {
        return regression.getRSquare();
    }

    public double predict(double x) {
        return regression.predict(x);
    }

}
