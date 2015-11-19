package resources;

import services.HitCounter;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;

@Path("/hello")
public class HelloResource {

    @GET
    @Produces("text/html")
    public String handleGreeting() {
        return "<h1>Hello World</h1><br>This page has been hit " + HitCounter.getInstance().incr() + " times";
    }
    
}
