package com.routescoop;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;

@Controller("/critical-power")
public class CriticalPowerController {

    @Post
    CpResult post(@Valid @Body SolveForEffort power) {
        return new PowerSolver(power).solve();
    }

}
