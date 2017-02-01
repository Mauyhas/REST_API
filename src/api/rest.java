package api;

import java.nio.channels.ConnectionPendingException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import jdbc.ConnectionPoolingBean;

@Path("/api")
public class rest {
	ConnectionPoolingBean CPE;
	
		
	@POST
	@Path("/json")
	@Consumes(MediaType.APPLICATION_JSON)
	@Produces(MediaType.APPLICATION_JSON)
	public Response handleJson(String data) throws Exception{
		System.out.println("data : " + data);

		
		return Response.status(200).entity(data).build();
		//TODO
	}
	
}
