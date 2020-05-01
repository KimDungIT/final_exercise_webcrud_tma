package com.tma.apa.training.device.mgmt.rest.resource;

import com.tma.apa.training.device.mgmt.vo.DeviceHolderVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/device-holder")
public interface DeviceHolderRestResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDeviceHolders(@DefaultValue("1") @QueryParam("page") int page,
                              @DefaultValue("20") @QueryParam("limit") int limit);

    @GET
    @Path("/{deviceHolderName}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDeviceHolder(@PathParam("deviceHolderName") String name);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes((MediaType.APPLICATION_JSON))
    Response insertDeviceHolder(DeviceHolderVO deviceHolderVO);

    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteDeviceHolders();

}
