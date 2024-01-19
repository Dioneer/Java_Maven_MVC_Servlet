package pegas;

public class Program {
    public static void main(String[] args) {
        new Server((req, resp)-> "<html><body><h1>Hello, Elena</h1></body></html>").begin();
    }

}
