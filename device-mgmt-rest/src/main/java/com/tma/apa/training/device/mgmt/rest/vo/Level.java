package com.tma.apa.training.device.mgmt.rest.vo;

public enum Level {

    DeviceHolder("DeviceHolder"),
    Device("Device");

    private final String m_level;

    Level(String level) {
        this.m_level = level;
    }

    public String getLevel() {
        return m_level;
    }

    public static Level from(Exception e) {
        switch (e.getClass().getSimpleName()) {
            case "DeviceHolderException" :
                return Level.DeviceHolder;
            case "DeviceException" :
                return Level.Device;
            default:
                return null;
        }
    }

}
