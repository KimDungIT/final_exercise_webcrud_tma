package com.tma.apa.training.device.mgmt.impl.service;

import com.tma.apa.training.device.mgmt.dao.DeviceDAO;
import com.tma.apa.training.device.mgmt.dao.DeviceHolderDAO;
import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.exception.DeviceException;
import com.tma.apa.training.device.mgmt.exception.DeviceHolderException;
import com.tma.apa.training.device.mgmt.impl.util.ConvertUtils;
import com.tma.apa.training.device.mgmt.service.DeviceService;
import com.tma.apa.training.device.mgmt.vo.DevicePagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceVO;
import org.apache.cxf.common.util.StringUtils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DeviceServiceImpl implements DeviceService {

    private DeviceDAO m_deviceDAO;
    private DeviceHolderDAO m_deviceHolderDAO;

    public DeviceServiceImpl(DeviceDAO deviceDAO, DeviceHolderDAO deviceHolderDAO) {
        m_deviceDAO = deviceDAO;
        m_deviceHolderDAO = deviceHolderDAO;
    }
    @Override
    public List<DeviceVO> getAll() {
        List<DeviceVO> deviceVOS = new ArrayList<DeviceVO>();
        List<Device> devices = m_deviceDAO.getAll();

        devices.forEach(device -> {
            deviceVOS.add(ConvertUtils.toDeviceVO(device));
        });
        return deviceVOS;
    }

    @Override
    public DevicePagingVO getByDeviceHolder(String name, int page, int limit) {
        if (page < 1) {
            page = 1;
        }
        if (limit < 1 || limit > 1000) {
            limit = 20;
        }

        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(name);
        if(deviceHolder == null) {
            throw new DeviceHolderException("Device holder " + name + " does not exists");
        }
        Long total = m_deviceDAO.countRowsByDeviceHolder(deviceHolder);
        List<Device> devices = m_deviceDAO.getByDeviceHolder(deviceHolder, page, limit);
        List<DeviceVO> deviceVOS = new ArrayList<DeviceVO>();
        for (Device device : devices) {
            deviceVOS.add(ConvertUtils.toDeviceVO(device));
        }
        return new DevicePagingVO(deviceVOS, total);
    }

    @Override
    public DeviceVO getByName(String name) {
        Device device = m_deviceDAO.getByName(name);
        return device == null ? null : ConvertUtils.toDeviceVO(device);
    }

    @Override
    public void add(DeviceVO deviceVO) throws DeviceException {
        if(deviceVO == null || StringUtils.isEmpty(deviceVO.getName())) {
            throw new DeviceException("Device name is null");
        }

        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(deviceVO.getDeviceHolderName());
        if(deviceHolder == null) {
            throw new DeviceHolderException("Device holder " + deviceVO.getDeviceHolderName() + " does not exists");
        }

        Device device = m_deviceDAO.getByName(deviceVO.getName());
        if(device != null) {
            throw new DeviceException("Device " + deviceVO.getName() + " already exists");
        }

        if (StringUtils.isEmpty(deviceVO.getHardwareType())
                || StringUtils.isEmpty(deviceVO.getInterfaceVersion())
                || StringUtils.isEmpty(deviceVO.getConnectionMechanism())) {
            throw new DeviceException("Insert device " + deviceVO.getName() + " failed due to hardwareType, interfaceVersion or connectionMechanism are missing");
        } else {
            m_deviceDAO.add(ConvertUtils.toDevice(deviceVO, deviceHolder));
        }
    }

    @Override
    public void update(String name, DeviceVO deviceVO) throws DeviceException {
        Device device = m_deviceDAO.getByName(name);
        if(device == null) {
            throw new DeviceException("Device " + name + " doest not exists");
        }
        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(deviceVO.getDeviceHolderName());
        if(deviceHolder == null) {
            throw new DeviceHolderException("Device holder " + deviceVO.getDeviceHolderName() + " doest not exists");
        }
        if (deviceVO.getHardwareType() == null || deviceVO.getHardwareType().isEmpty()
                || deviceVO.getInterfaceVersion() == null || deviceVO.getInterfaceVersion().isEmpty()
                || deviceVO.getConnectionMechanism() == null || deviceVO.getConnectionMechanism().isEmpty()) {
            throw new DeviceException("Update device " + name + " fail due to missing mandatory fields");
        } else {
            m_deviceDAO.update(device, ConvertUtils.toDevice(deviceVO, deviceHolder));
        }

    }

    @Override
    public void delete(String name) throws DeviceException{
        Device device = m_deviceDAO.getByName(name);
        if(device == null) {
            throw new DeviceException("Device " + name + " does not exists");
        }
        m_deviceDAO.delete(name);
    }

    @Override
    public void deleteByDeviceHolder(String name) throws DeviceException{
        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(name);
        if(deviceHolder == null) {
            throw new DeviceHolderException("Device holder " + name + " does not exists");
        }
        m_deviceDAO.deleteByDeviceHolder(deviceHolder);
    }

    @Override
    public void deleteAll() {
        m_deviceDAO.deleteAll();
    }


}
