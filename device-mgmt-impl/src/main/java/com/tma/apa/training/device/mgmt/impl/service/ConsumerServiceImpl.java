package com.tma.apa.training.device.mgmt.impl.service;

import com.tma.apa.training.device.mgmt.exception.ConsumerException;
import com.tma.apa.training.device.mgmt.impl.consumer.ConsumerCreator;
import com.tma.apa.training.device.mgmt.impl.thread.PollMessageThread;
import com.tma.apa.training.device.mgmt.service.ConsumerService;
import org.apache.kafka.clients.consumer.Consumer;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConsumerServiceImpl implements ConsumerService{

    private Consumer<String, String> m_consumer;

    public void init() {
        System.out.println("Init service");

        if(m_consumer == null) {
            m_consumer = ConsumerCreator.createConsumer();
            if (m_consumer == null) {
                throw new ConsumerException("Can not create m_consumer");
            }
        }
        pollMessage();
    }

    public void destroy() {
        if (m_consumer != null) {
            m_consumer.close();
        }
    }

    @Override
    public void pollMessage() throws ConsumerException {
        ExecutorService es = Executors.newSingleThreadExecutor();
        PollMessageThread pollMessageThread = new PollMessageThread(m_consumer);
        es.execute(pollMessageThread);
    }

}
