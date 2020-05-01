package com.tma.apa.training.device.mgmt.exception;

public class DeviceMgtException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public DeviceMgtException(String message) {
        super(message);
    }

    public DeviceMgtException(Exception e) {
        super(e);
    }
}