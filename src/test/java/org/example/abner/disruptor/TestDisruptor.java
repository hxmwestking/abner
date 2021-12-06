package org.example.abner.disruptor;

import com.lmax.disruptor.RingBuffer;
import com.lmax.disruptor.SleepingWaitStrategy;
import com.lmax.disruptor.TimeoutException;
import com.lmax.disruptor.dsl.Disruptor;
import com.lmax.disruptor.dsl.ProducerType;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

public class TestDisruptor {

    private Disruptor<LogEvent> disruptor;
    private LogService logService;

    @Before
    public void before() {
        logService = new LogService();

        LogContext.init(logService);

        LogEventFactory eventFactory = new LogEventFactory();
        ThreadFactory factory = new LogEventThreadFactory();
        disruptor = new Disruptor<>(eventFactory, 1024 * 1024, factory, ProducerType.MULTI,
                new SleepingWaitStrategy());
        LogEventExceptionHandler<Object> exceptionHandler = new LogEventExceptionHandler<>();
        disruptor.setDefaultExceptionHandler(exceptionHandler);
        LogEventHandler logEventHandler = new LogEventHandler();
        disruptor.handleEventsWith(logEventHandler);
        disruptor.start();
    }

    @After
    public void after() {
        try {
            disruptor.shutdown(20, TimeUnit.SECONDS);
        } catch (TimeoutException e) {
            e.printStackTrace();
        }
        LogContext.shutdown(logService);
    }

    @Test
    public void testDisruptor() {
        List<Thread> producers = new ArrayList<>(8);

        LogEventTranslator translator = new LogEventTranslator();
        for (int i = 0; i < 4; i++) {
            int val = i;
            Thread thread = new Thread(() -> {
                for (int j = val * 2500; j < (val + 1) * 2500; j++) {
//                    System.out.println("producer: " + j);
                    try {
                        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
                        ringBuffer.publishEvent(translator, "msg-" + j);
                        Thread.sleep(1);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            });
            producers.add(thread);
            thread.start();
        }
        long start = System.currentTimeMillis();
        producers.forEach(producer -> {
            try {
                producer.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });
        System.out.println("cost:" + (System.currentTimeMillis() - start));
    }

    @Test
    public void test1() {
        LogEventFactory eventFactory = new LogEventFactory();
        ThreadFactory factory = Executors.defaultThreadFactory();
        disruptor = new Disruptor<>(eventFactory, 1024 * 1024, factory, ProducerType.MULTI,
                new SleepingWaitStrategy());
        LogEventExceptionHandler<Object> exceptionHandler = new LogEventExceptionHandler<>();
        disruptor.setDefaultExceptionHandler(exceptionHandler);
        LogEventHandler logEventHandler = new LogEventHandler();
        disruptor.handleEventsWith(logEventHandler);
        disruptor.start();
/*
        // 第一种
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
        long seq = ringBuffer.next();
        try {
            LogEvent event = ringBuffer.get(seq);
            event.setMsg("data");
        }finally {
            ringBuffer.publish(seq);
        }
*/
        // 第二种
        LogEventTranslator translator = new LogEventTranslator();
        RingBuffer<LogEvent> ringBuffer = disruptor.getRingBuffer();
        ringBuffer.publishEvent(translator, "msg");
    }
}