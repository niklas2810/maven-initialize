package com.niklasarndt.maveninit;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

/**
 * Created by Niklas on 2020/08/03.
 */
class ConfigurationTest {

    @Test
    public void testConfiguration() {
        assertFalse(Configuration.isDebug());
    }

}