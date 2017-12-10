package com.leafguard;

import java.util.Date;

/**
 * Outputs a consistant message to the console
 * nicely formatted with message level
 * and Datetime
 */

public class Log
{
    private static final String INFO        = "[INFO]";
    private static final String WARNING     = "[WARNING]";
    private static final String CRITICAL    = "[CRITICAL]";

    private static void log(String message, String type) {
        System.out.println(type + " " + new Date() + " - " + message);
    }

    public static void info(String message) {
        log(message, INFO);
    }

    public static void warning(String message) {
        log(message, WARNING);
    }

    public static void critical(String message) {
        log(message, CRITICAL);
    }
}
