package com.niklasarndt.maveninit;

import com.niklasarndt.maveninit.util.PreventExitManager;
import static org.junit.jupiter.api.Assertions.assertThrows;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


class BootstrapTest {

    @BeforeAll
    public static void setSecurityManager() {
        System.setSecurityManager(new PreventExitManager());
    }

    @Test
    public void testMain() {
        assertThrows(SecurityException.class, () -> Bootstrap.main(
                new String[0]));
        assertThrows(SecurityException.class, () -> Bootstrap.main(
                new String[]{"/path/does/not/exist"}));
    }

}