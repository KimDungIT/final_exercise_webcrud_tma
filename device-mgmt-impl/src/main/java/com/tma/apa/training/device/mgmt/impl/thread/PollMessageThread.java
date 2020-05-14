package com.tma.apa.training.device.mgmt.impl.thread;

import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerRecords;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class PollMessageThread implements Runnable {

    private volatile Consumer<String, String> m_consumer;

    public PollMessageThread(Consumer<String, String> consumer) {
        m_consumer = consumer;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(60_000);
        } catch (InterruptedException e) {
            System.out.println("Exception when sleep" + e);
        }

        Throwable stop = null;
        while (stop == null) {
            try {
                ConsumerRecords<String, String> consumerRecords = m_consumer.poll(1000);
                System.out.println("Thread " + Thread.currentThread().getName());
                consumerRecords.forEach(record -> {
                    System.out.println("Record with key: " + record.key());
                });

                handleConsumerRecords(consumerRecords);
                // commits the offset of record to broker.
                m_consumer.commitAsync();
            } catch (Exception e) {
                stop = e;
            }
        }
    }

    private void handleConsumerRecords(ConsumerRecords<String, String> consumerRecords) {

        int poolSize = 3;
        Map<String, String> consumerRecordsMap = new HashMap<>();
        consumerRecords.forEach(record -> {
            consumerRecordsMap.put(record.key(), record.value());
        });
        System.out.println("Map consumed: " + consumerRecordsMap);
        ExecutorService es = Executors.newFixedThreadPool(poolSize);
        consumerRecordsMap.forEach((key, value) -> {
            ConsumerThread consumerThread = new ConsumerThread(key, value);
            es.execute(consumerThread);
        });
    }
}
