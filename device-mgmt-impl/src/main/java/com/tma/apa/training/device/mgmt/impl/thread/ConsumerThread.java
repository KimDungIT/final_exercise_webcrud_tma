package com.tma.apa.training.device.mgmt.impl.thread;

public class ConsumerThread implements Runnable {

    private String key;

    private String value;

    public ConsumerThread(String key, String value) {
        this.key = key;
        this.value = value;
    }

    @Override
    public void run() {
        System.out.println("MyThread - START " + Thread.currentThread().getName());
        System.out.println("Record key: " + key);
        System.out.println("Record value: " + value);
//        Thread.sleep(1000);
    }
}
