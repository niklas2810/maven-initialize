package com.niklasarndt.maveninit.step;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.Test;
import java.io.File;

/**
 * Created by Niklas on 2020/08/03.
 */
class ExecutionStepTest {

    @Test
    public void testParentChild() {
        ExecutionStep parent = new ExecutionStep("parent") {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return true;
            }

            @Override
            public boolean execute(File runningDirectory) {
                return true;
            }
        };
        ExecutionStep child = new ExecutionStep("child") {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return true;
            }

            @Override
            public boolean execute(File runningDirectory) {
                return true;
            }
        };

        assertEquals("parent", parent.getName());
        assertThrows(IllegalArgumentException.class, () -> parent.setIndex(0));
        parent.setIndex(1);
        assertThrows(IllegalStateException.class, () -> parent.setIndex(1));

        assertThrows(IllegalArgumentException.class, () -> child.setParent(null, 1));
        assertThrows(IllegalStateException.class, () -> child.setParent(parent, 1));
        parent.addChild(child);
        assertThrows(IllegalArgumentException.class, () -> parent.addChild(child));

        assertEquals(parent, parent.getChildren().get(0).getParent());
        assertEquals(parent, child.getParent());

        assertEquals(1, parent.getChildren().size());
        assertEquals("1.1. child", parent.getChildren().get(0).getPrintable());

        ExecutionStep secondChild = new ExecutionStep("child2") {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return true;
            }

            @Override
            public boolean execute(File runningDirectory) {
                return true;
            }
        };
        ExecutionStep secondParent = new ExecutionStep("parent2", secondChild) {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return true;
            }

            @Override
            public boolean execute(File runningDirectory) {
                return true;
            }
        };

        assertEquals(secondParent, secondChild.getParent());
        assertEquals(1, secondParent.getChildren().size());
    }

}