package com.tma.apa.training.device.mgmt.rest.resource;

import com.tma.apa.training.device.mgmt.vo.DeviceVO;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.DefaultValue;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/device")
public interface DeviceRestResource {

    @GET
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDevices(@QueryParam("name") String name);

    @GET
    @Path("/{deviceHolderName}")
    @Produces(MediaType.APPLICATION_JSON)
    Response getDevicesByDeviceHolder(@PathParam("deviceHolderName") String deviceHolderName,
                                      @DefaultValue("1") @QueryParam("page") int page,
                                      @DefaultValue("20") @QueryParam("limit") int limit);

    @POST
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response insertDevice(DeviceVO deviceVO);

    @PUT
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    Response updateDevice(@PathParam("name") String name, DeviceVO deviceVO);

    @DELETE
    @Path("/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteDevice(@PathParam("name") String name);

    @DELETE
    @Path("/")
    @Produces(MediaType.APPLICATION_JSON)
    Response deleteDevices(@QueryParam("deviceHolderName") String deviceHolderName);
}