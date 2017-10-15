package io.orten.nano.business;

import io.orten.nano.impl.OrganizationService;
import io.orten.nano.model.Organization;

import javax.ws.rs.*;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

// end point for all Organization operations

@Path("/org")
public class OrganizationAPI {

    //a method to save an organization object in the database

    @POST
    @Path("/saveorg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveOrganization(Organization org)
    {

        return new OrganizationService().save(org);
    }

    //a method to update an organization object that already saved in the database

    @PUT
    @Path("/updateorg")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response updateOrg(Organization org)
    {
      return new OrganizationService().update(org);
    }

    //a method to retrieve an organization object from the database based on the organizationID

    @GET
    @Path("/getorg")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getOrg(@QueryParam("orgID") String orgID){
        return new OrganizationService().get(orgID);
    }

    //a method to retrieve allorgnizations objects that are  already saved in the database

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getall")
    public Response getAllOrg(){
        return new io.orten.nano.impl.OrganizationService().getAll();
    }

    //a method to delete an organization object from the database
    @DELETE
    @Consumes
    @Path("/deleteorg")
    @Produces(MediaType.TEXT_PLAIN)
    public Response deleteOrg(@QueryParam("orgID") String orgID){
        return new io.orten.nano.impl.OrganizationService().delete(orgID);
    }
}
