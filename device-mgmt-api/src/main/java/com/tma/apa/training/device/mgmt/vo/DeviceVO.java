package com.tma.apa.training.device.mgmt.vo;

import com.tma.apa.training.device.mgmt.entity.ConnectionMechanism;
import com.tma.apa.training.device.mgmt.entity.Device;

public class DeviceVO {

    private String name;
    private String hardwareType;
    private String interfaceVersion;
    private String connectionMechanism;
    private String deviceHolderName;

    public DeviceVO() {
    }

    public DeviceVO(Device device) {
        this.name = device.getName();
        this.hardwareType = device.getHardwareType();
        this.interfaceVersion = device.getInterfaceVersion();
        this.connectionMechanism = device.getConnectionMechanism().getKey();
        this.deviceHolderName = device.getDeviceHolder().getDeviceHolderName();
    }


    public DeviceVO(String name, String hardwareType, String interfaceVersion,
                    String connectionMechanism, String deviceHolderName) {
        this.name = name;
        this.hardwareType = hardwareType;
        this.interfaceVersion = interfaceVersion;
        this.connectionMechanism = connectionMechanism;
        this.deviceHolderName = deviceHolderName;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHardwareType() {
        return hardwareType;
    }

    public void setHardwareType(String hardwareType) {
        this.hardwareType = hardwareType;
    }

    public String getInterfaceVersion() {
        return interfaceVersion;
    }

    public void setInterfaceVersion(String interfaceVersion) {
        this.interfaceVersion = interfaceVersion;
    }

    public String getConnectionMechanism() {
        return connectionMechanism;
    }

    public void setConnectionMechanism(String connectionMechanism) {
        this.connectionMechanism = connectionMechanism;
    }

    public String getDeviceHolderName() {
        return deviceHolderName;
    }

    public void setDeviceHolderName(String deviceHolderName) {
        this.deviceHolderName = deviceHolderName;
    }

    @Override
    public String toString() {
        return "DeviceVO{" +
                "name='" + name + '\'' +
                ", hardwareType='" + hardwareType + '\'' +
                ", interfaceVersion='" + interfaceVersion + '\'' +
                ", connectionMechanism='" + connectionMechanism + '\'' +
                ", deviceHolderName='" + deviceHolderName + '\'' +
                '}';
    }
}