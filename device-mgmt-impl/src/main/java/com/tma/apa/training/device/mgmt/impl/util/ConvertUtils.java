package com.tma.apa.training.device.mgmt.impl.util;

import com.tma.apa.training.device.mgmt.entity.ConnectionMechanism;
import com.tma.apa.training.device.mgmt.entity.Device;
import com.tma.apa.training.device.mgmt.entity.DeviceHolder;
import com.tma.apa.training.device.mgmt.exception.DeviceException;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;
import com.tma.apa.training.device.mgmt.vo.DeviceVO;

public class ConvertUtils {

    public static DeviceHolderVO toDeviceHolderVO(DeviceHolder deviceHolder) {
        return new DeviceHolderVO(deviceHolder);
    }

    public static DeviceVO toDeviceVO(Device device) {
        return new DeviceVO(device.getName(), device.getHardwareType(), device.getInterfaceVersion(),
                device.getConnectionMechanism().getKey(), device.getDeviceHolder().getDeviceHolderName());
    }

    public static DeviceHolder toDeviceHolder(DeviceHolderVO deviceHolderVO) {
        return new DeviceHolder(deviceHolderVO.getDeviceHolderName());
    }

    public static Device toDevice(DeviceVO deviceVO, DeviceHolder deviceHolder) {
        ConnectionMechanism connectionMechanism = null;
        if (deviceVO.getConnectionMechanism() != null) {
            connectionMechanism = ConnectionMechanism.from(deviceVO.getConnectionMechanism());
        }

        if (connectionMechanism != null && deviceVO.getName() != null
                && deviceVO.getHardwareType() != null
                && deviceVO.getInterfaceVersion()!= null
                && deviceHolder != null
            ) {
            return new Device(deviceVO.getName(), deviceVO.getHardwareType(),
                    deviceVO.getInterfaceVersion(), connectionMechanism, deviceHolder);
        } else {
            throw new DeviceException("Can not convert to device");
        }
    }
}
