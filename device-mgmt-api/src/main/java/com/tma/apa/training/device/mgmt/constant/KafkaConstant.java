package com.tma.apa.training.device.mgmt.constant;

public interface KafkaConstant {
    String KAFKA_BROKERS = "localhost:9092";

    int MESSAGE_COUNT=100;

    String CLIENT_ID="client1";

    String TOPIC_NAME="device-mgmt";

    String GROUP_ID_CONFIG="consumerGroupDevice";

    int MAX_NO_MESSAGE_FOUND_COUNT=10;

    String OFFSET_RESET_LATEST="latest";

    String OFFSET_RESET_EARLIER="earliest";

    int MAX_POLL_RECORDS=5;
}
