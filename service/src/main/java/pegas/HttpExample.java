package pegas;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Path;

import static java.net.http.HttpRequest.BodyPublishers.ofFile;

public class HttpExample {
    public static void main(String[] args) throws IOException, InterruptedException {
        HttpClient httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();
//        HttpRequest httpRequest = HttpRequest
//                .newBuilder()
//                .uri(URI.create("https://ya.ru/"))
//                .GET()
//                .build();
        HttpRequest httpRequest = HttpRequest
                .newBuilder()
                .uri(URI.create("https://ya.ru/"))
                .header("content-type","application/json")
                .POST(ofFile(Path.of("web/src/main/resources/test.json")))
                .build();
        HttpResponse<String> httpResponse1 = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());
        System.out.println(httpResponse1.headers());
        System.out.println(httpResponse1.body());
    }
}
