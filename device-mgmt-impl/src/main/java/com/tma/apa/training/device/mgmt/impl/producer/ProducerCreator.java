package com.tma.apa.training.device.mgmt.impl.producer;

import com.tma.apa.training.device.mgmt.constant.KafkaConstant;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;

import java.util.Properties;

public class ProducerCreator {
    public static Producer<String, String> createProducer() {

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BROKERS);
        props.put(ProducerConfig.CLIENT_ID_CONFIG, KafkaConstant.CLIENT_ID);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Producer<String, String> m_producer = null;
        try{
            // Set classLoader for DefaultPartitioner to resolve KAFKA3218 issue
            Thread.currentThread().setContextClassLoader(null);
            if (props.get(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG) != null) {
                m_producer = new KafkaProducer<>(props);
                System.out.printf("Initialized Kafka producer with properties: " + props);
            } else {
                System.out.printf("Kafka producer: Missing bootstrap server(s) configuration");
            }
        } catch (Exception e) {
            System.out.printf("Failed to initialize Kafka producer", e);
        } finally {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        return m_producer;
    }
}
