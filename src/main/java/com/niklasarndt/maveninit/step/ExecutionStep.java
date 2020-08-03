package com.niklasarndt.maveninit.step;

import com.niklasarndt.maveninit.Configuration;
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

    public ExecutionStep(String name, ExecutionStep... children) {
        this(name, List.of(children));
    }

    public ExecutionStep(String name, List<ExecutionStep> children) {
        this.name = name;

        if (children == null || children.isEmpty()) return;
        children.forEach(this::addChild);
    }

    public final void addChild(ExecutionStep child) {
        if (hasChild(child)) {
            throw new IllegalArgumentException("This child has already been registered.");
        }
        children.add(child);
        child.setParent(this, children.size());
    }

    public final boolean hasChild(ExecutionStep step) {
        return children.stream().anyMatch(child -> child.getClass().equals(step.getClass()));
    }

    public final void setParent(ExecutionStep parent, int index) {
        if (this.parent != null) {
            throw new IllegalStateException("The parent step has already been set.");
        }
        if (parent == null) {
            throw new IllegalArgumentException("The parent parameter must not be null");
        }
        if (!parent.hasChild(this)) {
            throw new IllegalStateException("Please register a child using the parent's method.");
        }
        setIndex(index);

        this.parent = parent;
    }

    public abstract boolean isExecutable(File runningDirectory);

    public abstract boolean execute(File runningDirectory);

    public final boolean executeWithChildren(File runningDirectory) {
        boolean result = execute(runningDirectory);
        children.forEach(child -> child.execute(runningDirectory));
        return result;
    }

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
            throw new IllegalArgumentException(index +
                    " is an invalid value for an execution step.");
        }
        this.index = index;
    }

    public String getPrintable() {
        return (getParent() == null ? getIndex() + ". " : getParent().getIndex()
                + "." + getIndex() + ". ") + getName();
    }

    public final void print() {
        print(0);
    }

    private void print(int tabs) {
        boolean exec = isExecutable(Configuration.DIR());
        System.out.format("[%s] %s%s\n", exec ? "X" : " ",
                "\t".repeat(tabs), getPrintable());
        if (exec) children.forEach(child -> child.print(tabs + 1));
    }

    public ExecutionStep getParent() {
        return parent;
    }
}
