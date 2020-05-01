package com.tma.apa.training.device.mgmt.exception;

public class DeviceException extends DeviceMgtException {

    public DeviceException(String message) {
        super(message);
    }

    public DeviceException(Exception e) {
        super(e);
    }
}

