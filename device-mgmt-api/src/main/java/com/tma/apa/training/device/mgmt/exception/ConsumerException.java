package com.tma.apa.training.device.mgmt.exception;

public class ConsumerException extends DeviceMgtException {
    public ConsumerException(String message) {
        super(message);
    }

    public ConsumerException(Exception e) {
        super(e);
    }
}
