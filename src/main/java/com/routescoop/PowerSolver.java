package com.routescoop;

import java.util.ArrayList;
import java.util.List;

public class PowerSolver implements CpSolver {

    private final LinearRegression regression;
    private final SolveForEffort power;

    public PowerSolver(SolveForEffort power) {
        this.power = power;
        List<Observation> observations = new ArrayList<>();
        for (Effort effort : power.efforts) {
            double seconds = effort.getDuration();
            double watts = effort.getOutput();
            observations.add(new Observation(seconds, watts * seconds)); // convert to kjs, watts * seconds
        }
        this.regression = new LinearRegression(observations);
    }

    @Override
    public CpResult solve() {
        return solveFor(power.predictForDurations);
    }

    private CpResult solveFor(int[] durations) {
        List<Effort> efforts = new ArrayList<>();
        for (int duration: durations) {
            efforts.add(new Effort(duration, (int) regression.predict(duration) / duration));
        }
        return new CpResult((int) regression.slope(), (int) regression.intercept(), efforts);
    }

}
