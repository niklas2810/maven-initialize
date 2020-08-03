package com.niklasarndt.maveninit.util;

import java.io.IOException;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

/**
 * Created by Niklas on 2020/08/03.
 */
public class Input {

    private static final ExecutorService service = Executors.newSingleThreadExecutor();

    public static boolean yesNo(String question, boolean defaultValue) {
        char c = getResult(yesNo(question), defaultValue ? 'y' : 'n');
        return c == 'y' || c == 'Y';
    }


    public static boolean yesNo(String question, boolean defaultValue, int timeoutInSeconds) {
        return yesNo(question, defaultValue, timeoutInSeconds, TimeUnit.SECONDS);
    }

    public static boolean yesNo(String question, boolean defaultValue, int timeout, TimeUnit unit) {
        char c = getResult(yesNo(question), timeout, unit, defaultValue ? 'y' : 'n');
        return c == 'y' || c == 'Y';
    }

    public static Future<Character> yesNo(String question) {
        return service.submit(() -> {
            System.out.print(question + " [y/n] ");
            System.in.skip(Integer.MAX_VALUE);
            try {
                byte[] out = new byte[1];
                System.in.read(out);
                return (char) out[0];
            } catch (IOException e) {
                return 'n';
            }
        });
    }

    public static <T> T getResultWithTimeout(Future<T> future, T defaultValue) {
        return getResult(future, 30, defaultValue);
    }

    public static <T> T getResult(Future<T> future, int seconds, T defaultValue) {
        return getResult(future, seconds, TimeUnit.SECONDS, defaultValue);
    }

    public static <T> T getResult(Future<T> future, T defaultValue) {
        try {
            return future.get();
        } catch (InterruptedException | ExecutionException e) {
            System.out.print(defaultValue + "\n");
            return defaultValue;
        }
    }

    public static <T> T getResult(Future<T> future, int duration, TimeUnit unit, T defaultValue) {
        try {
            return future.get(duration, unit);
        } catch (InterruptedException | ExecutionException | TimeoutException e) {
            System.out.print(defaultValue + "\n");
            return defaultValue;
        }
    }

    public static void shutdown() {
        service.shutdown();
    }
}
