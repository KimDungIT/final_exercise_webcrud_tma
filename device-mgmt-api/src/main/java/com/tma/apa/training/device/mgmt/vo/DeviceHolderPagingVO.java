package com.tma.apa.training.device.mgmt.vo;

import java.util.List;

public class DeviceHolderPagingVO {

    private List<DeviceHolderVO> m_deviceHolderVOS;

    private Long total;

    public DeviceHolderPagingVO() {
    }

    public DeviceHolderPagingVO(List<DeviceHolderVO> deviceHolderVOS, Long total) {
        m_deviceHolderVOS = deviceHolderVOS;
        this.total = total;
    }

    public List<DeviceHolderVO> getDeviceHolderVOS() {
        return m_deviceHolderVOS;
    }

    public void setDeviceHolderVOS(List<DeviceHolderVO> deviceHolderVOS) {
        m_deviceHolderVOS = deviceHolderVOS;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }


}
