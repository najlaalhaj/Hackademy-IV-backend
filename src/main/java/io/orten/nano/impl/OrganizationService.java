package io.orten.nano.impl;

import io.orten.nano.model.Organization;
import io.orten.nano.util.Database;

import javax.ws.rs.core.Response;
import javax.servlet.http.HttpServletResponse;


public class OrganizationService {


        public Response save(Organization org)
        {
            try{
                Database.saveOrganization(org);
                return Response.status(HttpServletResponse.SC_CREATED).build();
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
            return Response.status(HttpServletResponse.SC_FOUND).entity(Database.getOrganization(orgID)).build();
        }
        catch(Exception e) {
            return Response.status(HttpServletResponse.SC_NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    public Response getAll() {
        try {
            return Response.status(HttpServletResponse.SC_FOUND).entity(Database.getAllOrganizations()).build();
        } catch (Exception e) {
            return Response.status(HttpServletResponse.SC_NO_CONTENT).entity(e.getMessage()).build();
        }
    }

    public Response delete(String orgID){
            try{
                return Response.status(HttpServletResponse.SC_GONE).entity(Database.deleteOrganization(orgID)).build();
            }
            catch(Exception e){
                return Response.status(HttpServletResponse.SC_NOT_FOUND).entity(e.getMessage()).build();
        }
    }

    }







