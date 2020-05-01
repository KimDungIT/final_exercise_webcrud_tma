package com.tma.apa.training.device.mgmt.dao;

import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.vo.DeviceVO;

import java.util.List;

public interface DeviceDAO {

    List<Device> getAll();

    List<Device> getByDeviceHolder(DeviceHolder deviceHolder, int page, int limit);

    Device getByName(String name);

    void add(Device device);

    void update(Device device, Device deviceDB);

    void delete(String name);

    void deleteByDeviceHolder(DeviceHolder deviceHolder);

    void deleteAll();

    Long countRowsByDeviceHolder(DeviceHolder deviceHolder);

}

