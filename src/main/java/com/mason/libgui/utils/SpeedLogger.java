package com.mason.libgui.utils;

import com.mason.libgui.components.misc.LoadingMessage;

import java.util.AbstractMap.SimpleEntry;
import java.util.LinkedList;

import static com.mason.libgui.utils.Utils.PERFORMANCE_LOG;

/**
 * Sets benchmarks and records and logs the speed that certain tasks are
 * completed at.
 * @author Adam Whittaker
 */
public class SpeedLogger{


    /**
     * PRECISION: The precision to record the time to.
     * now: The time of the last benchmark.
     * records: The list of actions along with the times they took to complete
     * in milliseconds.
     */
    private final static int PRECISION = 4; //Maximum 16 which is double precision.

    private long now;
    private final LinkedList<SimpleEntry<Long, String>> records = new LinkedList<>();
    private final LoadingMessage loadingMessage;


    public SpeedLogger(LoadingMessage message){
        loadingMessage = message;
    }


    /**
     * Starts the stop-clock.
     */
    public void start(){
        now = System.currentTimeMillis();
    }

    /**
     * Records the time the given action took to complete.
     * @param message The action.
     */
    public void log(String message){
        //Calculates the time elapsed.
        long time = System.currentTimeMillis()-now;
        //Adds it to the records.
        records.add(new SimpleEntry<>(time, message));
        //Prints the message out.
        message = time + " millis: " + message;
        dualPrintln(message);
        now = System.currentTimeMillis();
    }

    /**
     * Gives a speed report of the amount of time each action took to complete,
     * along with the total run time and fraction of the total execution time
     * that the different tasks took to complete.
     */
    public void report(){
        //Computes the total runtime.
        long total = records.stream().mapToLong(SimpleEntry::getKey).sum();
        //Prints out this information.
        PERFORMANCE_LOG.dualPrintln("     ---- Speed Report ----");
        PERFORMANCE_LOG.dualPrintln("Total time: " + total + " millis");
        //Prints out the fraction of the total runtime that each task took.
        records.forEach((e) -> {
            String time = ("" + (100D*e.getKey())/total); //Computes the percentage.
            if(time.length()<PRECISION) time += "00000000000000000"; //Over double precision so no error thrown.
            time = time.substring(0,PRECISION);
            PERFORMANCE_LOG.dualPrintln(e.getValue() + ": " + time + "%");
        });
    }

    /**
     * Prints the time report to the performance log and sets the loading
     * message.
     * @param message The speed report.
     */
    private void dualPrintln(String message){
        PERFORMANCE_LOG.dualPrintln(message);
        loadingMessage.setMessage(message);
    }

}
