package com.tma.apa.training.device.mgmt.vo;

import com.tma.apa.training.device.mgmt.entity.DeviceHolder;

public class DeviceHolderVO {

    private String deviceHolderName;
    private int noOfDevices = 0;

    public DeviceHolderVO() {
    }

    public DeviceHolderVO(DeviceHolder deviceHolder) {
        this.deviceHolderName = deviceHolder.getDeviceHolderName();
        this.noOfDevices = deviceHolder.getDevices() == null ? 0 : deviceHolder.getDevices().size();
    }

    public String getDeviceHolderName() {
        return deviceHolderName;
    }

    public void setDeviceHolderName(String deviceHolderName) {
        this.deviceHolderName = deviceHolderName;
    }

    public int getNoOfDevices() {
        return noOfDevices;
    }

    public void setNoOfDevices(int noOfDevices) {
        this.noOfDevices = noOfDevices;
    }

    @Override
    public String toString() {
        return "DeviceHolderVO{" +
                "deviceHolderName='" + deviceHolderName + '\'' +
                '}';
    }
}