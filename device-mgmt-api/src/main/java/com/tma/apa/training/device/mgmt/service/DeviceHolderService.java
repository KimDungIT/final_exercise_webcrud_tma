package com.tma.apa.training.device.mgmt.service;

import com.tma.apa.training.device.mgmt.exception.DeviceHolderException;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderPagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;

import java.util.List;

public interface DeviceHolderService {

    DeviceHolderPagingVO getAll(int page, int limit);

    DeviceHolderVO getByName(String name);

    void add(DeviceHolderVO deviceHolderVO) throws DeviceHolderException;

    void deleteAll() throws DeviceHolderException;

}