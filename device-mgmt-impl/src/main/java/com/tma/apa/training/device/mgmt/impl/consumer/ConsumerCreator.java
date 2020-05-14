package com.tma.apa.training.device.mgmt.impl.consumer;

import com.tma.apa.training.device.mgmt.constant.KafkaConstant;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.KafkaConsumer;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.util.Collections;
import java.util.Properties;


public class ConsumerCreator {
    public static Consumer<String, String> createConsumer() {
        Properties props = new Properties();
        props.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, KafkaConstant.KAFKA_BROKERS);
        props.put(ConsumerConfig.GROUP_ID_CONFIG, KafkaConstant.GROUP_ID_CONFIG);
        props.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        props.put(ConsumerConfig.MAX_POLL_RECORDS_CONFIG, KafkaConstant.MAX_POLL_RECORDS);
        props.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, "false");
//        props.put(ConsumerConfig.AUTO_COMMIT_INTERVAL_MS_CONFIG, "5000");
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, KafkaConstant.OFFSET_RESET_LATEST);
        ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
        Consumer<String, String> consumer = null;
        try{
            // Set classLoader for DefaultPartitioner to resolve KAFKA3218 issue
            Thread.currentThread().setContextClassLoader(null);
            if (props.get(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG) != null) {
                consumer = new KafkaConsumer<>(props);
                consumer.subscribe(Collections.singletonList(KafkaConstant.TOPIC_NAME));
                System.out.printf("Initialize Kafka consumer with properties: " + props);
            } else {
                System.out.printf("Kafka consumer: Missing bootstrap server(s) configuration");
            }
        } catch (Exception e) {
            System.out.printf("Failed to initialize Kafka consumer ", e);
        } finally {
            Thread.currentThread().setContextClassLoader(classLoader);
        }
        return consumer;
    }
}
