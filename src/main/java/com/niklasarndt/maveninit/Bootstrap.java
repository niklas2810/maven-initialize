package com.niklasarndt.maveninit;


import com.niklasarndt.maveninit.step.ExecutionStepManager;
import com.niklasarndt.maveninit.step.impl.GitStep;
import com.niklasarndt.maveninit.util.Input;
import com.niklasarndt.maveninit.util.Log;
import java.io.File;

public class Bootstrap {

    private final File directory;

    public Bootstrap(File directory) {
        this.directory = directory;
        Configuration.DIR(this.directory);

        if (!this.directory.exists() || !this.directory.isDirectory()) {
            Log.error("The running directory \"%s\" is invalid. " +
                    "It either does not exist or isn't a directory.", directory.getAbsolutePath());
            System.out.println(this.directory.exists());
            System.out.println(this.directory.isDirectory());
            System.exit(1);
        }

        Log.info("Running directory: %s", directory.getAbsolutePath());
        performInit();
    }

    public static void main(String[] args) {
        Log.info("\n\nPlease submit any issues " +
                "on GitHub: %s.", BuildInfo.URL);

        if (args.length == 0) {
            Log.error("Please specify the directory which " +
                    "should be used in the parameters.");
            System.exit(1);
        }

        new Bootstrap(new File(String.join(" ", args)));
    }

    private void performInit() {
        System.out.println("\n----------- SETUP ---------------\n");
        Configuration.USE_GIT = Input.yesNo("Do you want to setup GitHub in your repository?",
                true);

        executeSteps();
        shutdown();
    }

    private void executeSteps() {
        System.out.println("\n\n----------- REVIEW ---------------\n");

        ExecutionStepManager manager = new ExecutionStepManager(new GitStep());

        System.out.println("All steps which will be executed are marked with an \"X\".\n");
        manager.printSteps();
        if (!Input.yesNo("\nExecute now?", false)) {
            Log.info("Goodbye!");
            return;
        }

        manager.execute();
    }

    private void shutdown() {
        Input.shutdown();
    }

}
