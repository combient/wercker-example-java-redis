package testutil;

import org.eclipse.jetty.util.IO;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URI;


public class SimpleTest {
    private URI baseUri;

    public SimpleTest(URI serverURI) {
        this.baseUri = serverURI;
    }

    public String getString(String relativePath) throws IOException {
        URI uri = this.baseUri.resolve(relativePath);
        System.out.println("GET (String): " + uri.toASCIIString());

        InputStream in = null;
        InputStreamReader reader = null;
        HttpURLConnection connection;

        try {
            connection = (HttpURLConnection) uri.toURL().openConnection();
            connection.connect();
            if (HttpURLConnection.HTTP_OK != connection.getResponseCode()) {
                String body = getPotentialBody(connection);
                String err = String.format("GET request failed (%d %s) %s%n%s", connection.getResponseCode(), connection.getResponseMessage(),
                        uri.toASCIIString(), body);
                throw new IOException(err);
            }
            in = connection.getInputStream();
            reader = new InputStreamReader(in);
            StringWriter writer = new StringWriter();
            IO.copy(reader, writer);
            return writer.toString();
        } finally {
            IO.close(reader);
            IO.close(in);
        }
    }

    private String getPotentialBody(HttpURLConnection connection) {
        InputStream in = null;
        InputStreamReader reader = null;
        try {
            in = connection.getInputStream();
            reader = new InputStreamReader(in);
            StringWriter writer = new StringWriter();
            IO.copy(reader, writer);
            return writer.toString();
        } catch (IOException e) {
            return "<no body:" + e.getMessage() + ">";
        } finally {
            IO.close(reader);
            IO.close(in);
        }
    }
}