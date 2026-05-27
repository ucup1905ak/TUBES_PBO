package utility;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 *
 * @author farel
 */
public class Log {

    private static final String LOG_FILE = "syslog.txt";
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    public static synchronized void create(String msg) {
        String timestamp = LocalDateTime.now().format(FORMATTER);

        String callerInfo = getCallerInfo();

        String logEntry = String.format("[%s] : %s [%s]", timestamp, msg, callerInfo);

        System.out.println(logEntry);

        try (PrintWriter out = new PrintWriter(new FileWriter(LOG_FILE, true))) {
            out.println(logEntry);
        } catch (IOException e) {
            System.err.println("Failed to write to log file: " + e.getMessage());
        }
    }

    private static String getCallerInfo() {
        StackTraceElement[] stackTrace = Thread.currentThread().getStackTrace();

        if (stackTrace.length >= 4) {
            StackTraceElement caller = stackTrace[3];

            String fullClassName = caller.getClassName();
            String simpleClassName = fullClassName.substring(fullClassName.lastIndexOf('.') + 1);

            return simpleClassName + "." + caller.getMethodName() + "()";
        }
        return "UnknownCaller";
    }
}
