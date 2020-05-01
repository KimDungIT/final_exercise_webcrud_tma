package com.tma.apa.training.device.mgmt.rest.vo;

public class DeviceResponse {

    private String status;
    private String message;
    private Level level;

    public DeviceResponse() {
    }

    public DeviceResponse(Status status, String message) {
        this.status = status.toString();
        this.message = message;
    }

    public DeviceResponse(Status status, String message, Level level) {
        this.status = status.toString();
        this.message = message;
        this.level = level;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Level getLevel() {
        return level;
    }

    public void setLevel(Level level) {
        this.level = level;
    }

    @Override
    public String toString() {
        return "DeviceResponse{" +
                "status='" + status + '\'' +
                ", message='" + message + '\'' +
                ", level='" + level.name() + '\'' +
                '}';
    }
}
