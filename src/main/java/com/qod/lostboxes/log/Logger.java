package com.qod.lostboxes.log;

import java.io.PrintStream;
import java.time.LocalTime;

public class Logger {

    public static final int DEBUG = 0x0;
    public static final int INFO = 0x1;
    public static final int WARN = 0x2;
    public static final int ERROR = 0x3;

    public PrintStream[] streams;
    public int[] levels;
    public boolean[] enhanced;

    public Logger(PrintStream... streams) {
        this.streams = streams;
        this.levels = new int[streams.length];
        this.enhanced = new boolean[streams.length];
    }

    public String format(String message, int level, int stream) {
        if(level < levels[stream]) return "";

        // Attributes
        String time = LocalTime.now().toString().substring(0, 12);
        String mode = "", color = "";
        switch(level) {
            case DEBUG:
                if(enhanced[stream]) color = ConsoleColor.RESET.getCode();
                mode = "DEBUG";
                break;
            case INFO:
                if(enhanced[stream]) color = ConsoleColor.BLUE.getCode();
                mode = "INFO ";
                break;
            case WARN:
                if(enhanced[stream]) color = ConsoleColor.YELLOW.getCode();
                mode = "WARN ";
                break;
            case ERROR:
                if(enhanced[stream]) color = ConsoleColor.RED.getCode();
                mode = "ERROR";
                break;
            default:
                System.err.println("Level not Supported. Falling back to Debug.");
                levels[stream] = 0;
                mode = "DEBUG";
                break;
        }
        String thread = Thread.currentThread().getName();

        return String.format("%s[%s] [%s] (%s) - %s\n", color, mode, time, thread, message);
    }

    public void debug(String message) {
        for(int i = 0; i < streams.length; i++) {
            if(streams[i] != null)
                streams[i].print(format(message, DEBUG, i));
        }
    }

    public void info(String message) {
        for(int i = 0; i < streams.length; i++) {
            if(streams[i] != null)
                streams[i].print(format(message, INFO, i));
        }
    }

    public void warn(String message) {
        for(int i = 0; i < streams.length; i++) {
            if(streams[i] != null)
                streams[i].print(format(message, WARN, i));
        }
    }

    public void error(String message) {
        for(int i = 0; i < streams.length; i++) {
            if(streams[i] != null)
                streams[i].print(format(message, ERROR, i));
        }
    }

    public void setLevel(int stream, int level) {
        levels[stream] = level;
    }

    public void enableEnhanced(int stream) {
        enhanced[stream] = true;
    }

    public void disableEnhanced(int stream) {
        enhanced[stream] = false;
    }
}
