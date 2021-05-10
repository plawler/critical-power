package com.routescoop;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class CriticalPowerSolverTest {

    List<Effort> efforts = Arrays.asList(
        new Effort(180, 400),
        new Effort(360, 350),
        new Effort(720, 300)
    );

    int[] durations = new int[]{15, 30, 60, 120, 180, 240, 300, 480, 600, 900, 1200, 2400, 3600, 7200, 9800};

    @Test
    public void powerSolverTest() {
        CpSolver solver = new PowerSolver(new SolveForEffort(efforts, durations));
        CpResult result = solver.solve();
        assertEquals(264, result.cp);
        assertEquals(27000, result.xprime);
    }

    @Test
    public void testMyCriticalPower() {
        List<Effort> efforts = Arrays.asList(
            new Effort(180, 370),
            new Effort(360, 327),
            new Effort(720, 298)
        );
        CpSolver solver = new PowerSolver(new SolveForEffort(efforts, durations));
        CpResult result = solver.solve();
        assertEquals(273, result.cp);
        assertEquals(18180, result.xprime);
    }
}
