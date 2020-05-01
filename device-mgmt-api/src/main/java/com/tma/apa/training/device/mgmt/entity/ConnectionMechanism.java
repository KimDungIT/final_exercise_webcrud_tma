package com.tma.apa.training.device.mgmt.entity;

public enum ConnectionMechanism {

    call_home("Call home", 0),
    non_call_home("Non Callhome", 1);

    private final String key;
    private final Integer value;

    ConnectionMechanism(String key, Integer value) {
        this.key = key;
        this.value = value;
    }

    public String getKey() {
        return key;
    }

    public Integer getValue() {
        return value;
    }

    public static ConnectionMechanism from(String key) {
        switch (key) {
            case "Call home":
                return ConnectionMechanism.call_home;
            case "Non Callhome":
                return ConnectionMechanism.non_call_home;
            default:
                return null;
        }
    }

    public static ConnectionMechanism from(Integer value) {
        switch (value) {
            case 0:
                return ConnectionMechanism.call_home;
            case 1:
                return ConnectionMechanism.non_call_home;
            default:
                return null;
        }
    }
}
