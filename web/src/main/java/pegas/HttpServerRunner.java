package pegas;

import java.io.IOException;

public class HttpServerRunner {
    public static void main(String[] args) throws IOException {
        HttpServer httpServer = new HttpServer(8082,3);
        httpServer.run();
    }
}
