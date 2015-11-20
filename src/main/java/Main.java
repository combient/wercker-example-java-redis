
import java.io.IOException;

import org.eclipse.jetty.server.Server;
import services.ServerFactory;

public class Main {
    public static void main(String[] args) throws IOException {

        Server server = ServerFactory.getServer();
        try {
            server.start();
            server.join();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
