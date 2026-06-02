package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author Farelino Alexander Kim / 240713000
 */

public class Log {

    private static final DateTimeFormatter LOG_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    private static final DateTimeFormatter FILE_DATE_FORMAT
            = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    private static String getLogFileName() {
        return "tubes_log_"
                + LocalDate.now().format(FILE_DATE_FORMAT)
                + ".log";
    }

    private static synchronized void log(String level, String msg, boolean isError) {
        String timestamp = LocalDateTime.now().format(LOG_FORMAT);
        String callerInfo = getCallerInfo();

        String logEntry = String.format(
                "[%s] [%s] : %s [%s]",
                timestamp,
                level,
                msg,
                callerInfo
        );

        // Console output
        if (isError) {
            System.err.println(logEntry);
        } else {
            System.out.println(logEntry);
        }

        // File output
        try (PrintWriter out = new PrintWriter(
                new FileWriter(getLogFileName(), true))) {

            out.println(logEntry);

        } catch (IOException e) {
            System.err.println(
                    "Failed to write to log file: " + e.getMessage());
        }
    }

    public static void create(String msg) {
        log("INFO", msg, false);
    }

    public static void err(String msg) {
        log("ERROR", msg, true);
    }

    private static String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length >= 4) {
            StackTraceElement caller = stackTrace[3];

            String fullClassName = caller.getClassName();
            String simpleClassName
                    = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

            return simpleClassName + "." + caller.getMethodName() + "()";
        }

        return "UnknownCaller";
    }
}
