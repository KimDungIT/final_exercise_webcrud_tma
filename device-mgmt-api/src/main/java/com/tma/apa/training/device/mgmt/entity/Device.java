package com.tma.apa.training.device.mgmt.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity (name = "Device")
@Table(name = "Device")
public class Device implements Serializable {

    @Id
    @Column(name = "name")
    private String name;

    @Column(name = "hardware_type", nullable = false)
    private String hardwareType;

    @Column(name = "interface_version", nullable = false)
    private String interfaceVersion;

    @Column(name = "connection_mechanism", nullable = false)
    private ConnectionMechanism connectionMechanism;

    @ManyToOne (fetch = FetchType.LAZY)
    @JoinColumn(name = "deviceHolder", nullable = false)
    private DeviceHolder deviceHolder;

    public Device() {
    }

    public Device(String name, String hardwareType, String interfaceVersion, ConnectionMechanism connectionMechanism, DeviceHolder deviceHolder) {
        this.name = name;
        this.hardwareType = hardwareType;
        this.interfaceVersion = interfaceVersion;
        this.connectionMechanism = connectionMechanism;
        this.deviceHolder = deviceHolder;
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

    public ConnectionMechanism getConnectionMechanism() {
        return connectionMechanism;
    }

    public void setConnectionMechanism(ConnectionMechanism connectionMechanism) {
        this.connectionMechanism = connectionMechanism;
    }

    public DeviceHolder getDeviceHolder() {
        return deviceHolder;
    }

    public void setDeviceHolder(DeviceHolder deviceHolder) {
        this.deviceHolder = deviceHolder;
    }
}
