package io.orten.nano.impl;

import io.orten.nano.model.Project;
import io.orten.nano.model.User;
import io.orten.nano.util.Database;

import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Response;
import java.util.ArrayList;
import java.util.List;

public class UserService {

    public Response saveUser(User user) {
        try {
            if (Database.saveUser(user)) {
                return Response.status(HttpServletResponse.SC_OK).build();
            }else {
                return Response.status(HttpServletResponse.SC_BAD_REQUEST).build();
            }
        }catch (Exception e){
            return Response.status(HttpServletResponse.SC_INTERNAL_SERVER_ERROR).entity(e.getMessage()).build();
        }
    }
}
