package com.tma.apa.training.device.mgmt.rest.util;

import com.tma.apa.training.device.mgmt.rest.vo.DeviceResponse;
import com.tma.apa.training.device.mgmt.rest.vo.Level;
import com.tma.apa.training.device.mgmt.rest.vo.Status;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

public class RestUtil {

    public static Response printPassResponse(String message) {
        return Response
                .ok()
                .entity(new DeviceResponse(Status.Pass, message))
                .build();
    }

    public static Response printFailResponse(String message, Exception e) {
        return Response
                .status(Response.Status.BAD_REQUEST)
                .type(MediaType.APPLICATION_JSON_TYPE)
                .entity(new DeviceResponse(Status.Fail, message + ". " + e.getMessage(), Level.from(e)))
                .build();
    }
}
