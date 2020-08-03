package com.niklasarndt.maveninit.step.impl;

import com.niklasarndt.maveninit.step.ExecutionStep;
import java.io.File;
import java.util.Objects;

/**
 * Created by Niklas on 2020/08/03.
 */
public class RemoveGitFolderStep extends ExecutionStep {

    public RemoveGitFolderStep() {
        super("Remove .git folder of original repo");
    }

    @Override
    public boolean isExecutable(File runningDirectory) {
        File[] candidates = runningDirectory
                .listFiles(dir -> dir.getName().equals(".git") && dir.isDirectory());
        return candidates != null && candidates.length > 0;
    }

    @Override
    public boolean execute(File runningDirectory) {
        File gitDirectory = Objects.requireNonNull(runningDirectory
                .listFiles(dir -> dir.getName().equals(".git") && dir.isDirectory()))[0];
        return gitDirectory.delete();
    }
}
