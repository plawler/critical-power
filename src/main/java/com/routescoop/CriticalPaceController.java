package com.routescoop;

import io.micronaut.http.annotation.Body;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;

import javax.validation.Valid;

@Controller("/critical-pace")
public class CriticalPaceController {

    @Post
    public CpResult post(@Valid @Body SolveForEffort paces) {
        return new PaceSolver(paces).solve();
    }

}
