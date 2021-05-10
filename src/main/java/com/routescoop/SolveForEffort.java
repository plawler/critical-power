package com.routescoop;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;
import java.util.List;

@Introspected
public class SolveForEffort {

    @NotEmpty
    public final List<Effort> efforts;

    @NotEmpty
    public final int[] predictForDurations;

    public SolveForEffort(List<Effort> efforts, int[] predictForDurations) {
        this.efforts = efforts;
        this.predictForDurations = predictForDurations;
    }

}
