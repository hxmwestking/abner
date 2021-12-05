package org.example.abner.disruptor;

public class LogEvent {

    private String msg;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public void clear() {
        this.msg = null;
    }
}
