package de.bethibande.utils.logger;

import de.bethibande.utils.io.MultiOutputStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.util.Date;

/**
 * A very simple Logger class, responsible for logging console messages and writing log files.<br>
 * This logger supports the default minecraft colors codes: §r §0-§9 §a-§f<br>
 * <br>
 * The log format and default loglevel can be defined here too
 * <br><br>
 * Log format variables:<br>
 * {time} = hh:mm<br>
 * {logLevel} = the log level provided<br>
 * {thread} = name of the thread writing the message (may only be used in log level)<br>
 * {message} = the message to write<br><br>
 *
 * Default format:<br>
 * {time} [{logLevel}] {message}<br><br>
 *
 * Default log level:<br>
 * Thread/{thread}
 */
public class Logger {

    public static String DEFAULT_FORMAT = "{time} [{logLevel}] {message}";
    public static String DEFAULT_LOG_LEVEL = "Thread/{thread}";

    private static Logger instance;

    public static Logger getLogger() {
        return instance;
    }

    private String format = DEFAULT_FORMAT;

    private String logLevel = DEFAULT_LOG_LEVEL;
    private String errorLogLevel = DEFAULT_LOG_LEVEL;

    private File logFile;

    private final PrintStream consoleOut = System.out;
    private final PrintStream consoleError = System.err;


    public Logger() {
        Logger.instance = this;
    }

    /**
     * Set log format, default format
     * @param format the new log format
     */
    public void setFormat(String format) {
        this.format = format;
    }

    /**
     * Set the log level which will be used for .logMessage(String) messages
     * @param logLevel the new log level
     */
    public void setLogLevel(String logLevel) {
        this.logLevel = logLevel;
    }

    /**
     * Set the log level which will be used for .logError(String) messages
     * @param errorLogLevel the new log level
     */
    public void setErrorLogLevel(String errorLogLevel) {
        this.errorLogLevel = errorLogLevel;
    }

    /**
     * Get the currently used log format
     * @return the log format
     */
    public String getFormat() {
        return format;
    }

    /**
     * Get the currently used default log level
     * @return the log level
     */
    public String getLogLevel() {
        return logLevel;
    }

    /**
     * Get the currently used error log level
     * @return the error log level
     */
    public String getErrorLogLevel() {
        return errorLogLevel;
    }

    /**
     * Stop the logger, will set System.out and System.err back to their default values
     */
    public void close() {
        System.setOut(consoleOut);
        System.setErr(consoleError);
    }

    /**
     * Initialize a log file and set System.out and System.err to custom streams
     * @param logFile log file to be written to
     */
    public void init(File logFile) {
        this.logFile = logFile;
        if(this.logFile.exists()) this.logFile.delete(); // clear old log
        
        try {
            FileOutputStream sOut = new FileOutputStream(logFile);

            System.setOut(new PrintStream(new MultiOutputStream(System.out, sOut)));
            System.setErr(new PrintStream(new MultiOutputStream(System.err, sOut)));
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Log a message with the provided log level<br>
     * The message will written as an error to System.err if the err parameter is true
     * @param logLevel the log level
     * @param message the message to write
     * @param err write message as error
     */
    public void logMessage(String logLevel, String message, boolean err) {
        Date d = new Date();
        String time = String.format("%02d", d.getHours()) + ":" + String.format("%02d", d.getMinutes());
        var msg = format.replace("{time}", time).replace("{logLevel}", logLevel).replace("{message}", message);
        if (msg.contains("§")) {
            msg = msg.replace("§r", ConsoleColors.RESET);
            msg = msg.replace("§a", ConsoleColors.GREEN_BRIGHT);
            msg = msg.replace("§b", ConsoleColors.CYAN_BRIGHT);
            msg = msg.replace("§c", ConsoleColors.RED_BRIGHT);
            msg = msg.replace("§d", ConsoleColors.PURPLE_BRIGHT);
            msg = msg.replace("§e", ConsoleColors.YELLOW_BRIGHT);
            msg = msg.replace("§f", ConsoleColors.WHITE_BRIGHT);
            msg = msg.replace("§0", ConsoleColors.BLACK);
            msg = msg.replace("§1", ConsoleColors.BLUE);
            msg = msg.replace("§2", ConsoleColors.GREEN);
            msg = msg.replace("§3", ConsoleColors.CYAN);
            msg = msg.replace("§4", ConsoleColors.RED);
            msg = msg.replace("§5", ConsoleColors.PURPLE);
            msg = msg.replace("§6", ConsoleColors.YELLOW);
            msg = msg.replace("§7", ConsoleColors.BLACK_BRIGHT);
            msg = msg.replace("§8", ConsoleColors.BLACK_BRIGHT);
            msg = msg.replace("§9", ConsoleColors.BLUE_BRIGHT);
            msg += ConsoleColors.RESET;
        }
        if (err) {
            System.err.println(msg);
            System.err.flush();
        } else {
            System.out.println(msg);
            System.out.flush();
        }
    }

    /**
     * Log a message with the default log level<br>
     * This will write a message to the System.out stream
     * @param message the message to log
     */
    public void logMessage(String message) {
        logMessage(logLevel.replace("{thread}", Thread.currentThread().getName()), message, false);
    }

    /**
     * Log an error with the default error log level<br>
     * This will write a message to the System.err stream
     * @param error the error message
     */
    public void logError(String error) {
        logMessage(errorLogLevel.replace("{thread}", Thread.currentThread().getName()), error, true);
    }

}
