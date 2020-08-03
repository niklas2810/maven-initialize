package com.niklasarndt.maveninit.step;

import com.niklasarndt.maveninit.util.SysoutInterceptor;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.io.File;
import java.io.PrintStream;

/**
 * Created by Niklas on 2020/08/03.
 */
class ExecutionStepManagerTest {

    public static boolean STEP1 = false;
    public static boolean STEP2 = false;

    @Test
    public void testManager() {
        ExecutionStep step = new ExecutionStep("execute") {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return true;
            }

            @Override
            public boolean execute(File runningDirectory) {
                STEP1 = true;
                return true;
            }
        };
        ExecutionStep step2 = new ExecutionStep("dont-execute") {
            @Override
            public boolean isExecutable(File runningDirectory) {
                return false;
            }

            @Override
            public boolean execute(File runningDirectory) {
                STEP2 = true;
                return true;
            }
        };
        ExecutionStepManager manager = new ExecutionStepManager(step, step2);

        assertEquals(2, manager.getSteps().size());

        PrintStream original = System.out;
        SysoutInterceptor interceptor = new SysoutInterceptor(original);

        System.setOut(new PrintStream(interceptor));
        manager.printSteps();
        System.setOut(original);

        assertEquals("[X] 1. execute\n[ ] 2. dont-execute\n", interceptor.getCollected());


        STEP1 = false;
        STEP2 = false;
        manager.execute();

        assertTrue(STEP1);
        assertFalse(STEP2);
    }

}