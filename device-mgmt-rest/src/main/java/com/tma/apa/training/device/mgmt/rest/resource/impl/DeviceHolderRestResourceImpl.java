package com.tma.apa.training.device.mgmt.rest.resource.impl;

import com.tma.apa.training.device.mgmt.rest.resource.DeviceHolderRestResource;
import com.tma.apa.training.device.mgmt.rest.util.RestUtil;
import com.tma.apa.training.device.mgmt.service.DeviceHolderService;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderPagingVO;
import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;

import javax.ws.rs.core.Response;
import java.util.List;

public class DeviceHolderRestResourceImpl implements DeviceHolderRestResource {

    private DeviceHolderService m_deviceHolderService;

    public DeviceHolderRestResourceImpl(DeviceHolderService deviceHolderService) {
        m_deviceHolderService = deviceHolderService;
    }

    @Override
    public Response getDeviceHolders(int page, int limit) {
        DeviceHolderPagingVO result = m_deviceHolderService.getAll(page, limit);
        return Response.status(200).entity(result).build();
    }

    @Override
    public Response getDeviceHolder(String name) {
        DeviceHolderVO result = m_deviceHolderService.getByName(name);
        // FIXME: result maybe null
        return Response.status(200).entity(result).build();
    }

    @Override
    public Response insertDeviceHolder(DeviceHolderVO deviceHolderVO) {
        try {
            m_deviceHolderService.add(deviceHolderVO);
            return RestUtil.printPassResponse("Inserted device holder successfully");
        } catch (Exception e) {
            return RestUtil.printFailResponse("Insert device holder fail", e);
        }
    }

    @Override
    public Response deleteDeviceHolders() {
        try {
            m_deviceHolderService.deleteAll();
            return RestUtil.printPassResponse("Deleted device holders successfully");
        } catch (Exception e) {
            e.printStackTrace();
            return RestUtil.printFailResponse("Delete device holders fail ", e);
        }
    }
}