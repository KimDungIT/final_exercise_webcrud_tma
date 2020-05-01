package com.tma.apa.training.device.mgmt.impl.service;

import com.tma.apa.training.device.mgmt.dao.DeviceHolderDAO;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.exception.DeviceHolderException;
import com.tma.apa.training.device.mgmt.impl.util.ConvertUtils;
import com.tma.apa.training.device.mgmt.service.DeviceHolderService;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderPagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;
import org.apache.cxf.common.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

public class DeviceHolderServiceImpl implements DeviceHolderService {

    private DeviceHolderDAO m_deviceHolderDAO;

    public DeviceHolderServiceImpl(DeviceHolderDAO deviceHolderDAO) {
        m_deviceHolderDAO = deviceHolderDAO;
    }

    @Override
    public DeviceHolderPagingVO getAll(int page, int limit) {
        if (page < 1) {
            page = 1;
        }
        if (limit < 1 || limit > 1000) {
            limit = 20;
        }
        Long total = m_deviceHolderDAO.countRows();

        List<DeviceHolder> deviceHolders = m_deviceHolderDAO.getAll(page, limit);
        List<DeviceHolderVO> deviceHolderVOS = new ArrayList<DeviceHolderVO>();
        for(DeviceHolder deviceHolder : deviceHolders) {
            deviceHolderVOS.add(ConvertUtils.toDeviceHolderVO(deviceHolder));
        }
        return new DeviceHolderPagingVO(deviceHolderVOS, total);
    }

    @Override
    public DeviceHolderVO getByName(String name) {
        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(name);
        return deviceHolder == null ? null : ConvertUtils.toDeviceHolderVO(deviceHolder);
    }

    @Override
    public void add(DeviceHolderVO deviceHolderVO) throws DeviceHolderException {
        if (deviceHolderVO == null || StringUtils.isEmpty(deviceHolderVO.getDeviceHolderName())) {
            throw new DeviceHolderException("Device holder is empty");
        }

        DeviceHolder deviceHolder = m_deviceHolderDAO.getByName(deviceHolderVO.getDeviceHolderName());
        if(deviceHolder != null) {
            throw new DeviceHolderException("Device holder " + deviceHolderVO.getDeviceHolderName()
                    + " already exists");
        }
        m_deviceHolderDAO.add(ConvertUtils.toDeviceHolder(deviceHolderVO));
    }

    @Override
    public void deleteAll() throws DeviceHolderException{
        m_deviceHolderDAO.deleteAll();
    }

}
