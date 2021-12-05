package org.example.abner.disruptor;

import java.util.concurrent.ThreadFactory;
import java.util.concurrent.atomic.AtomicInteger;

public class LogEventThreadFactory implements ThreadFactory {

    private final String namePrefix = "KEFU_LOG_THREAD_";
    private final AtomicInteger threadNum = new AtomicInteger(1);


    public Thread newThread(Runnable r) {
        String name = namePrefix + threadNum.getAndIncrement();
        System.out.println("newThread: " + name);
        return new Thread(r, name);
    }
}