package com.routescoop;

import io.micronaut.core.annotation.Introspected;

import java.util.List;

@Introspected
public class CpResult {

    public final double cp;
    public final double xprime;
    public final List<Effort> predictedEfforts;

    public CpResult(double cp, double xprime, List<Effort> efforts) {
        this.cp = cp;
        this.xprime = xprime;
        this.predictedEfforts = efforts;
    }

}
