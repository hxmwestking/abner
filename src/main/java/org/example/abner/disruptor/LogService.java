package org.example.abner.disruptor;

import java.util.List;

public class LogService {

    public void send(List<String> msgs) {
        if (msgs == null || msgs.size() == 0) {
            return;
        }
        System.out.println("send: " + msgs.size());
    }
}
