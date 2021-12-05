package org.example.abner.disruptor;

import com.lmax.disruptor.ExceptionHandler;

public class LogEventExceptionHandler<LogEvent> implements ExceptionHandler<LogEvent> {

    @Override
    public void handleEventException(final Throwable throwable, final long sequence, final LogEvent event) {
        try {
            // Careful to avoid allocation in case of memory pressure.
            // Sacrifice performance for safety by writing directly
            // rather than using a buffer.
            System.err.print("AsyncLogger error handling event seq=");
            System.err.print(sequence);
            System.err.print(", value='");
            try {
                System.err.print(event);
            } catch (final Throwable t) {
                System.err.print("ERROR calling toString() on ");
                System.err.print(event.getClass().getName());
                System.err.print(": ");
                System.err.print(t.getClass().getName());
                System.err.print(": ");
                System.err.print(t.getMessage());
            }
            System.err.print("': ");
            System.err.print(throwable.getClass().getName());
            System.err.print(": ");
            System.err.println(throwable.getMessage());
            // Attempt to print the full stack trace, which may fail if we're already
            // OOMing We've already provided sufficient information at this point.
            throwable.printStackTrace();
        } catch (final Throwable ignored) {
            // LOG4J2-2333: Not much we can do here without risking an OOM.
            // Throwing an error here may kill the background thread.
        }
    }

    @Override
    public void handleOnStartException(final Throwable throwable) {
        System.err.println("AsyncLogger error starting:");
        throwable.printStackTrace();
    }

    @Override
    public void handleOnShutdownException(final Throwable throwable) {
        System.err.println("AsyncLogger error shutting down:");
        throwable.printStackTrace();
    }
}