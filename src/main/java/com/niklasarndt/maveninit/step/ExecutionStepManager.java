package com.niklasarndt.maveninit.step;

import com.niklasarndt.maveninit.Configuration;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 2020/08/03.
 */
public class ExecutionStepManager {

    private final List<ExecutionStep> steps = new ArrayList<>();

    public ExecutionStepManager(ExecutionStep... steps) {
        for (ExecutionStep step : steps) {
            addStep(step);
        }
    }

    private void addStep(ExecutionStep step) {
        steps.add(step);
        step.setIndex(steps.size());
    }

    public List<ExecutionStep> getSteps() {
        return steps;
    }

    public void execute() {
        steps.forEach(step -> {
            if (step.isExecutable(Configuration.DIR()))
                step.executeWithChildren(Configuration.DIR());
        });
    }

    public void printSteps() {
        steps.forEach(ExecutionStep::print);
    }
}
