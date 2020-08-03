package com.niklasarndt.maveninit.util;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Created by Niklas on 2020/08/03.
 */
class LogTest {

    @Test
    void error() {
        assertEquals("[ERROR] Test", Log.error("Test"));
        assertEquals("[ERROR] Test Message", Log.error("Test %s", "Message"));
    }

    @Test
    void warn() {
        assertEquals("[WARN] Test", Log.warn("Test"));
        assertEquals("[WARN] Test Message", Log.warn("Test %s", "Message"));
    }

    @Test
    void info() {
        assertEquals("Test", Log.info("Test"));
        assertEquals("Test Message", Log.info("Test %s", "Message"));
    }

    @Test
    void debug() {
        assertEquals("", Log.debug("Test"));
        assertEquals("", Log.debug("Test %s", "Message"));
    }
}