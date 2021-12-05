package org.example.abner.disruptor;

import com.lmax.disruptor.EventHandler;

public class LogEventHandler implements EventHandler<LogEvent> {

    @Override
    public void onEvent(LogEvent event, long sequence, boolean endOfBatch) throws Exception {
        try {
            System.out.println("event thread: " + Thread.currentThread().getName() + " Event:" + event.getMsg());
            LogContext.putMsg(event.getMsg());
        } finally {
            event.clear();
        }
    }
}
