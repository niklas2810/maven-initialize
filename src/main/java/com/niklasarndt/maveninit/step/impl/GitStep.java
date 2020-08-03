package com.niklasarndt.maveninit.step.impl;

import com.niklasarndt.maveninit.Configuration;
import com.niklasarndt.maveninit.step.ExecutionStep;
import java.io.File;

/**
 * Created by Niklas on 2020/08/03.
 */
public class GitStep extends ExecutionStep {

    public GitStep() {
        super("Git setup");
        super.addChild(new RemoveGitFolderStep());
    }

    @Override
    public boolean isExecutable(File runningDirectory) {
        return Configuration.USE_GIT;
    }

    @Override
    public boolean execute(File runningDirectory) {
        return true;
    }
}
