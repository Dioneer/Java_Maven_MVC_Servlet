package pegas;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.CharBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

class Server {
    private final static int SIZE = 256;
    private AsynchronousServerSocketChannel server;

    public Server(HttpHandler httpHandler) {
        this.httpHandler = httpHandler;
    }

    private final HttpHandler httpHandler;

    public void begin() {

        try {
            server = AsynchronousServerSocketChannel.open();
            server.bind(new InetSocketAddress("127.0.0.1", 8081));
            while (true) {
                Future<AsynchronousSocketChannel> future = server.accept();
                handleRequest(future);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } catch (TimeoutException e) {
            throw new RuntimeException(e);
        }
    }

    private void handleRequest(Future<AsynchronousSocketChannel> future) throws InterruptedException, ExecutionException, TimeoutException, IOException {
        System.out.println("new client connection");
        AsynchronousSocketChannel channel = future.get();
        while (channel != null && channel.isOpen()) {
            ByteBuffer buffer = ByteBuffer.allocate(SIZE);
            StringBuilder builder = new StringBuilder();
            boolean keep = true;
            while (keep) {
                int result = channel.read(buffer).get();
//                int position = buffer.position();
                keep = result == SIZE;
                buffer.flip();
//                byte[] array = keep ? buffer.array() : Arrays.copyOfRange(buffer.array(), 0, position);
                CharBuffer charBuffer = StandardCharsets.UTF_8.decode(buffer);
                builder.append(charBuffer);

                buffer.clear();
            }
            HttpRequest httpRequest = new HttpRequest(builder.toString());
            HttpResponse httpResponse = new HttpResponse();
            if(httpHandler != null){
                try {
                    String body = this.httpHandler.handler(httpRequest,httpResponse);
                    if(body !=null && !body.isEmpty()){
                        if(httpResponse.getHeaders().get("Content-Type")==null){
                            httpResponse.addHeader("Content-Type","text/html; charset=utf-8");
                        }
                        httpResponse.setBody(body);
                    }
//            String body = "<html><body><h1>Hello, naive</h1></body></html>";
//                String page = String.format(HEADERS, body.length()) + body;
                } catch (Exception e) {
                    e.printStackTrace();
                    httpResponse.setStatusCode(500);
                    httpResponse.setStatus("internal server error");
                    httpResponse.addHeader("Content-Type","text/html; charset=utf-8");
                    httpResponse.setBody("<html><body><h1>Error happens</h1></body></html>");
                }

            }else{
                httpResponse.setStatusCode(404);
                httpResponse.setStatus("not found");
                httpResponse.addHeader("Content-Type","text/html; charset=utf-8");
                httpResponse.setBody("<html><body><h1>Resource not found</h1></body></html>");
            }
            ByteBuffer resp = ByteBuffer.wrap(httpResponse.getBytes());
            channel.write(resp);
            channel.close();
        }
    }
}
