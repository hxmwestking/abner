package org.example.abner.disruptor;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LogEventThread extends Thread {

    private ArrayBlockingQueue<String> queue;
    private final List<String> list = new ArrayList<>(256);
    private final LogService logService;

    public LogEventThread(LogService logService, String name) {
        super(name);
        this.logService = logService;
    }

    @Override
    public void run() {
        while (true) {
            String name = Thread.currentThread().getName();
            queue = LogContext.getQueue(name);
            try {
                String msg = queue.poll(1L, TimeUnit.SECONDS);
                System.out.println("thread: " + name + " consumer: " + msg);
                if (msg == null) {
                    send();
                } else {
                    list.add(msg);
                    if (list.size() >= 128) {
                        send();
                    }
                }
            } catch (InterruptedException e) {
                System.err.println("interrupt: " + e.getMessage());
                send();
                break;
            }

        }
    }

    private void send() {
        logService.send(new ArrayList<>(list));
        list.clear();
    }

    public void shutdown() {
        list.addAll(queue);
        send();
        interrupt();
    }

}
