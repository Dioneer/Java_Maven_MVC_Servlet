package pegas;

public interface HttpHandler {
    String handler(HttpRequest request, HttpResponse response);
}
