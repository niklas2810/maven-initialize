package com.niklasarndt.maveninit.util;

import java.security.Permission;

public class PreventExitManager
        extends SecurityManager {

    @Override
    public void checkPermission(Permission perm) {
    }

    @Override
    public void checkPermission(Permission perm, Object context) {
    }

    @Override
    public void checkExit(int status) {
        if (status != 0)
            throw new SecurityException("Trying to exit with status code " + status);
    }
}
