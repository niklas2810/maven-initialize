package com.niklasarndt.maveninit;


import com.niklasarndt.maveninit.util.Log;
import java.io.File;

public class Bootstrap {

    private final File directory;

    public Bootstrap(File directory) {
        this.directory = directory;

        if (!directory.exists() || !directory.isDirectory()) {
            Log.error("The running directory \"%s\" is invalid. " +
                    "It either does not exist or isn't a directory.", directory.getAbsolutePath());
            System.exit(1);
        }

        Log.info("Running directory: %s", directory.getAbsolutePath());
    }

    public static void main(String[] args) {
        Log.info("Please submit any issues " +
                "on GitHub: https://github.com/niklas2810/maven-initialize.");

        new Bootstrap(new File(String.join(" ", args)));
    }

}
