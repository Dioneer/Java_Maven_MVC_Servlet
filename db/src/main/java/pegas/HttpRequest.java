package pegas;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class HttpRequest {
    private final static String DELIMITER = "\r\n\r\n";
    private final static String NEW_LINE = "\r\n";
    private final static String HEADER_DELIMITER = ":";
    private final String message;
    private final HttpMethod method;
    private final String url;
    private final String body;
    private final Map<String, String> headers;
    public HttpRequest(String message){
        this.message = message;
        String[] parts = message.split(DELIMITER);
        String head = parts[0];
        String[] headerLines = head.split(NEW_LINE);
        String[] firstLine = headerLines[0].split(" ");
        method = HttpMethod.valueOf(firstLine[0]);
        url = firstLine[1];
        this.headers = Collections.unmodifiableMap(
          new HashMap<>(){{
              for(int i=1; i<headerLines.length; i++){
                  String[] headerParts = headerLines[i].split(HEADER_DELIMITER, 2);
                  put(headerParts[0].trim(),headerParts[1].trim());
              }
          }}
        );
        String bodyLength = this.headers.get("Content-length");
        int length = bodyLength != null ?Integer.parseInt(bodyLength):0;
        this.body = parts.length>1?parts[1].trim().substring(0, length):"";
    }

    public HttpMethod getMethod() {
        return method;
    }

    public String getUrl() {
        return url;
    }

    public String getBody() {
        return body;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }
}
