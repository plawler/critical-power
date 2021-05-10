package com.routescoop;

import java.util.ArrayList;
import java.util.List;

public class PaceSolver implements CpSolver {

    private final LinearRegression regression;
    private final SolveForEffort paces;

    public PaceSolver(SolveForEffort pace) {
        this.paces = pace;
        List<Observation> observations = new ArrayList<>();
        for (Effort effort : pace.efforts) {
            double seconds = effort.getDuration();
            double meters = effort.getOutput();
            observations.add(new Observation(seconds, meters));
        }
        this.regression = new LinearRegression(observations);
    }

    @Override
    public CpResult solve() {
        return solveFor(paces.predictForDurations);
    }

    private CpResult solveFor(int[] durations) {
        List<Effort> efforts = new ArrayList<>();
        for (int duration: durations) {
            efforts.add(new Effort(duration, (int) regression.predict(duration)));
        }
        return new CpResult(regression.slope(), regression.intercept(), efforts);
    }
}
