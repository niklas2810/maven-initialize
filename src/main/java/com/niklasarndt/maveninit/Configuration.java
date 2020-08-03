package com.niklasarndt.maveninit;

import java.io.File;

/**
 * Created by Niklas on 2020/08/03.
 */
public class Configuration {

    public static boolean USE_GIT = true;
    private static File DIR;

    public static boolean isDebug() {
        return false; //TODO: Implement local debug mode
    }

    public static void DIR(File directory) {
        if (DIR != null) return;
        DIR = directory;
    }

    public static File DIR() {
        return DIR;
    }
}
