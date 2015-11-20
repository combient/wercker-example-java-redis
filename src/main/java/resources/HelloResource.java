package resources;

import services.HitCounter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

    public static final String RESPONSE_FORMAT = "<h1>Hello World</h1><br>This page has been hit %d times";

    @GET
    @Produces("text/html")
    public String handleGreeting() {
        return String.format(RESPONSE_FORMAT, HitCounter.getInstance().incr());
    }
    
}
