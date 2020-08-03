package com.niklasarndt.maveninit.util;

import com.niklasarndt.maveninit.Configuration;

/**
 * Created by Niklas on 2020/08/03.
 */
public class Log {

    public static String error(String message, Object... args) {
        return log("[ERROR] ", message, args);
    }

    public static String warn(String message, Object... args) {
        return log("[WARN] ", message, args);
    }

    public static String info(String message, Object... args) {
        return log("", message, args);
    }

    public static String debug(String message, Object... args) {
        if (Configuration.isDebug()) return log("[DEBUG] ", message, args);
        return "";
    }

    private static String log(String prefix, String message, Object... args) {
        String out = String.format(prefix + message, args);
        System.out.println(out);
        return out;
    }
}
