package org.example.abner.disruptor;

import com.lmax.disruptor.EventTranslatorOneArg;

public class LogEventTranslator implements EventTranslatorOneArg<LogEvent, String> {


    @Override
    public void translateTo(LogEvent event, long sequence, String msg) {
        event.setMsg(msg);
    }
}
