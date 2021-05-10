package com.routescoop;

import io.micronaut.core.annotation.Introspected;

import javax.validation.constraints.NotEmpty;

@Introspected
public class Effort {

    @NotEmpty
    private final int duration;

    @NotEmpty
    private final int output;

    public Effort(int duration, int output) {
        this.duration = duration;
        this.output = output;
    }

    public int getDuration() {
        return duration;
    }

    public int getOutput() {
        return output;
    }

}
