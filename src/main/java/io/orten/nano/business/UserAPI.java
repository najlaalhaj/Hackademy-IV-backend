package io.orten.nano.business;

import io.orten.nano.impl.UserService;
import io.orten.nano.model.User;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 * represent an end point for a donor operations
 */
@Path("/")
public class UserAPI {

    /**
     * saves a donor to the batabase
     */

    @Path("/saveuser")
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response saveUser(User user){
        return new UserService().saveUser(user);
    }
}
