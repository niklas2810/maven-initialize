package com.niklasarndt.maveninit;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import org.junit.jupiter.api.Test;

class BuildInfoTest {

    @Test
    public void verifyBuildInfo() {
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.NAME);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.DESCRIPTION);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.VERSION);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.TARGET_JDK);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.TIMESTAMP);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.URL);
        assertNotEquals(BuildInfo.DEFAULT_VALUE, BuildInfo.MAIN_CLASS);

        System.out.println("NAME: " + BuildInfo.NAME);
        System.out.println("DESCRIPTION: " + BuildInfo.DESCRIPTION);
        System.out.println("VERSION: " + BuildInfo.VERSION);
        System.out.println("TARGET_JDK: " + BuildInfo.TARGET_JDK);
        System.out.println("TIMESTAMP: " + BuildInfo.TIMESTAMP);
        System.out.println("URL: " + BuildInfo.URL);
        System.out.println("MAIN_CLASS: " + BuildInfo.MAIN_CLASS);
    }
}