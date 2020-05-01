package com.tma.apa.training.device.mgmt.vo;

import java.util.List;

public class DevicePagingVO {

    private List<DeviceVO> m_deviceVOS;

    private Long total;

    public DevicePagingVO() {
    }

    public DevicePagingVO(List<DeviceVO> deviceVOS, Long total) {
        m_deviceVOS = deviceVOS;
        this.total = total;
    }

    public List<DeviceVO> getDeviceVOS() {
        return m_deviceVOS;
    }

    public void setDeviceVOS(List<DeviceVO> deviceVOS) {
        m_deviceVOS = deviceVOS;
    }

    public Long getTotal() {
        return total;
    }

    public void setTotal(Long total) {
        this.total = total;
    }
}
