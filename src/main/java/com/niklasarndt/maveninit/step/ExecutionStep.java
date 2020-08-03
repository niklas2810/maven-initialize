package com.niklasarndt.maveninit.step;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Niklas on 2020/08/03.
 */
public abstract class ExecutionStep {

    private final List<ExecutionStep> children = new ArrayList<>();
    private String name;
    private int index;
    private ExecutionStep parent;

    public ExecutionStep(String name) {
        this(name, null);
    }

    public ExecutionStep(String name, List<ExecutionStep> children) {
        this.name = name;

        if (children == null) return;
        this.children.addAll(children);
        for (int i = 0; i < this.children.size(); i++) {
            this.children.get(i).setParent(this, i);
        }
    }

    public final void setParent(ExecutionStep parent, int index) {
        if (this.parent != null) {
            throw new IllegalStateException("The parent step has already been set.");
        }
        if (parent == null) {
            throw new IllegalStateException("The parent parameter must not be null");
        }
        this.parent = parent;
        setIndex(index);
    }

    public abstract boolean isExecutable(File runningDirectory);

    public abstract boolean execute(File runningDirectory);

    public String getName() {
        return name;
    }

    public List<ExecutionStep> getChildren() {
        return children;
    }

    public int getIndex() {
        return index;
    }

    public final void setIndex(int index) {
        if (this.index > 0) {
            throw new IllegalStateException("The index has already been set.");
        }
        if (index < 1) {
            throw new IllegalStateException(index + " is an invalid value for an execution step.");
        }
        this.index = index;
    }

    public String getPrintableIndex() {
        return getParent() == null ? getIndex() + "." : getParent().getIndex()
                + "." + getIndex() + ".";
    }

    public ExecutionStep getParent() {
        return parent;
    }
}
