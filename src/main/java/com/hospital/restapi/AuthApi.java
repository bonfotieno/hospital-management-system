//package com.hospital.restapi;
//
//import com.hospital.controllers.AuthBeanI;
//import com.hospital.model.Auth;
//import com.hospital.model.Admin;
//import com.hospital.rest.ResponseWrapper;
//
//import javax.annotation.security.PermitAll;
//import javax.ejb.EJB;
//import javax.ws.rs.Consumes;
//import javax.ws.rs.POST;
//import javax.ws.rs.Path;
//import javax.ws.rs.Produces;
//import javax.ws.rs.core.MediaType;
//import javax.ws.rs.core.Response;
//
//@Path("/auth")
//public class AuthApi {
//
//    @EJB
//    AuthBeanI authBean;
//
//    @PermitAll
//    @POST
//    @Path("/login")
//    @Consumes(MediaType.APPLICATION_JSON)
//    @Produces(MediaType.APPLICATION_JSON)
//    public Response login(RestLoginWrapper loginWrapper) {
//
//        Auth auth = new Auth();
//        auth.setUsername(loginWrapper.getUsername());
//        auth.setPassword(loginWrapper.getPassword());
//        try {
//            Admin user = authBean.loginAdmin(auth);
//            return null;
////            return Response.status(Response.Status.OK)
////                .entity(new ResponseWrapper("authorized", user.getBearerToken())).build();
//        } catch (Exception ex) {
//            return Response.status(Response.Status.INTERNAL_SERVER_ERROR)
//                .entity(new ResponseWrapper(false, ex.getMessage())).build();
//        }
//
//
//
//    }
//}
