package io.orten.nano.impl;

import io.orten.nano.model.Organization;
import io.orten.nano.model.Project;
import io.orten.nano.util.Database;
import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletResponse;
import javax.xml.crypto.Data;
import java.util.ArrayList;
import java.util.List;

public class OrganizationService {

    public Response save(Organization org)
    {
        try{
            if (Database.saveOrganization(org)== true) {
                return Response.status(HttpServletResponse.SC_CREATED).build();
            } else {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
            }
        }
        catch(Exception e) {
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response update(Organization org)
    {
        try{
            Database.updateOrganization(org);
            return Response.status(HttpServletResponse.SC_OK).build();
        }
        catch(Exception e){
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response get(String orgID)
    {
        try {
            Organization org = Database.getOrganization(orgID);
            if (org!=null) {
                return Response.status(HttpServletResponse.SC_OK).entity(org).build();
            }else {
                return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
            }
        } catch(Exception e) {
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }

    public Response getAll() {
        try {
            List<Organization> organizations = new ArrayList<>();
            organizations = Database.getAllOrganizations();
            if (!(organizations.isEmpty())){
            return Response.status(HttpServletResponse.SC_FOUND).entity(organizations).build();
            } else {
                return Response.status(HttpServletResponse.SC_NOT_FOUND).build();
            }
        } catch (Exception e) {
            return Response.status(HttpServletResponse.SC_NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    public Response delete(String orgID){
        try{
            if (Database.deleteOrganization(orgID)== true) {
                return Response.status(HttpServletResponse.SC_OK).build();
            }
            else {return Response.status(HttpServletResponse.SC_NOT_FOUND).build();}
        }
        catch(Exception e){
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}







