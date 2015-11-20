package services;

import com.sun.jersey.spi.container.servlet.ServletContainer;
import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;

public class ServerFactory {

    public static Server getServer() {
        org.eclipse.jetty.server.Server server = new org.eclipse.jetty.server.Server(Integer.valueOf(System.getenv("PORT")));
        ServletContextHandler context = new ServletContextHandler(ServletContextHandler.SESSIONS);
        context.setContextPath("/");
        server.setHandler(context);
        ServletHolder h = new ServletHolder(new ServletContainer());
        h.setInitParameter("com.sun.jersey.config.property.packages", "resources");
        context.addServlet(h, "/*");
        return server;
    }
}
