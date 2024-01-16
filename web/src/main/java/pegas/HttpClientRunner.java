package pegas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpClientRunner {
    public static void main(String[] args) throws IOException, InterruptedException, ExecutionException {
        HttpClient httpClient = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder(URI.create("http://127.0.0.1:8082"))
                .header("content-type","application/json")
                .POST(ofFile(Path.of("web/src/main/resources/test.json")))
                .build();
        CompletableFuture<HttpResponse<String>> response1 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
//        CompletableFuture<HttpResponse<String>> response2 = httpClient.sendAsync(request, HttpResponse.BodyHandlers.ofString());
        System.out.println(response1.get().headers());
        System.out.println(response1.get().body());
//        System.out.println(response2.get().headers());
//        System.out.println(response2.get().body());;
    }
}
