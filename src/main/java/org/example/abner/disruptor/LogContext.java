package org.example.abner.disruptor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

public class LogContext {

    private static List<ArrayBlockingQueue<String>> list;
    private static final int SIZE = Runtime.getRuntime().availableProcessors();
    public static final String NAME_PREFIX = "KEFU_LOG_CONSUMER_";
    private static Map<String, ArrayBlockingQueue<String>> queueMap;
    private static long num = 0L;
    private static List<LogEventThread> consumers;

    public static void init(LogService logService) {
        list = new ArrayList<>(SIZE);
        queueMap = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(256);
            list.add(queue);
            queueMap.put(NAME_PREFIX + i, queue);
        }
        consumers = new ArrayList<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            LogEventThread thread = new LogEventThread(logService, LogContext.NAME_PREFIX + i);
            consumers.add(thread);
            thread.start();
        }
    }

    public static ArrayBlockingQueue<String> getQueue(int i) {
        return list.get(i);
    }

    public static void shutdown(LogService logService) {
        consumers.forEach(LogEventThread::shutdown);
        for (ArrayBlockingQueue<String> queue : list) {
            logService.send(new ArrayList<>(queue));
            queue.clear();
        }
    }

    public static void putMsg(String msg) throws InterruptedException {
        num++;
        int index = (int) (num % SIZE);
        boolean offer = list.get(index).offer(msg, 200, TimeUnit.MILLISECONDS);
        if (!offer) {
            System.err.println("putMsg timeout 200ms");
        }
    }

    public static ArrayBlockingQueue<String> getQueue(String name) {
        return queueMap.get(name);
    }

}