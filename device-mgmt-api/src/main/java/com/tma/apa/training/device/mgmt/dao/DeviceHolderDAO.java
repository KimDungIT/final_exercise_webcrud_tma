package com.tma.apa.training.device.mgmt.dao;

import com.tma.apa.training.device.mgmt.entity.DeviceHolder;

import java.util.List;

public interface DeviceHolderDAO {

    List<DeviceHolder> getAll(int page, int limit);

    DeviceHolder getByName(String name);

    void add(DeviceHolder deviceHolder);

    void deleteAll();

    Long countRows();

}