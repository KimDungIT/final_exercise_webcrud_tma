package com.tma.apa.training.device.mgmt.exception;

public class ProducerException extends DeviceMgtException {
    public ProducerException(String message) {
        super(message);
    }

    public ProducerException(Exception e) {
        super(e);
    }
}
