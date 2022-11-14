package com.hospital.restapi;

import com.hospital.controllers.DepartmentBeanI;
import com.hospital.model.Department;
import com.hospital.rest.ResponseWrapper;

import javax.ejb.EJB;
import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/department")
public class DepartmentRestAPI {

    @EJB
    private DepartmentBeanI departmentBean;

    @Path("/add")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response add(Department department) {
        try {
            departmentBean.add(department);
            return Response.status(Response.Status.OK).entity(new ResponseWrapper()).build();

        } catch (Exception ex) {
            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
                    .entity(new ResponseWrapper(false, ex.getMessage())).build();
        }

    }

    @Path("/list/{id}/{departmentName}")
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Response list(@PathParam("id") Long id,
                         @PathParam("departmentName")  String nameOfDept) {
        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>The id is " + id);
        System.out.println(">>>>>>>>>>>>>>>>>>>>>> company name " + nameOfDept);
        return Response.status(Response.Status.OK).entity(departmentBean.getList()).build();
    }

}
