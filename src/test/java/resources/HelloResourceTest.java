package resources;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.server.ServerConnector;
import org.eclipse.jetty.util.component.LifeCycle;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import services.HitCounter;
import services.ServerFactory;
import testutil.SimpleTest;

import java.net.URI;


public class HelloResourceTest {
    URI uri;
    SimpleTest test;
    Server server;

    @Before
    public void setUp() throws Exception {

        server = ServerFactory.getServer();
        try {
            server.start();
        } catch (Exception e) {
            e.printStackTrace();
        }

        Thread.sleep(6000);
        String uriString = "http://127.0.0.1:" + System.getenv("PORT");
        uri = new URI(uriString);
        test = new SimpleTest(uri);

    }

    @After
    public void tearDown() throws Exception {
        server.stop();
    }

    @Test
    public void testHandleGreeting() throws Exception {
        String response = test.getString("/hello");
        Assert.assertEquals(response, String.format(HelloResource.RESPONSE_FORMAT, 1));
    }
}