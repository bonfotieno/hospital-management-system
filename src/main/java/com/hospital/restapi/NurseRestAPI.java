package com.hospital.restapi;

import com.hospital.controllers.NurseBeanI;
import com.hospital.model.Nurse;
import com.hospital.rest.ResponseWrapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/nurse")
public class NurseRestAPI {

    @EJB
    private NurseBeanI nurseBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Nurse nurse) {
        try {
            nurseBean.save(nurse);
            return Response.status(Response.Status.OK).entity(new ResponseWrapper()).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseWrapper(false, ex.getMessage())).build();
        }

    }

    @Path("/list/{id}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") Long id) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>The id is " + id);
        return Response.status(Response.Status.OK).entity(nurseBean.getList()).build();
    }


}
