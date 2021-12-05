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
    public static final String namePrefix = "KEFU_LOG_CONSUMER_";
    private static Map<String, ArrayBlockingQueue<String>> queueMap;
    private static long num = 0L;

    public static void init() {
        list = new ArrayList<>(SIZE);
        queueMap = new HashMap<>(SIZE);
        for (int i = 0; i < SIZE; i++) {
            ArrayBlockingQueue<String> queue = new ArrayBlockingQueue<>(256);
            list.add(queue);
            queueMap.put(namePrefix + i, queue);
        }
    }

    public static ArrayBlockingQueue<String> getQueue(int i) {
        return list.get(i);
    }

    public static void shutdown(LogService logService) {
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
