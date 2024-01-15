package pegas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Scanner;

public class SocketClient {
    public static void main(String[] args) {
        try(Socket socket = new Socket("127.0.0.1", 8082);
            DataInputStream dis = new DataInputStream(socket.getInputStream());
            DataOutputStream dos = new DataOutputStream(socket.getOutputStream());
            Scanner scanner = new Scanner(System.in)){
            while (true){
                System.out.println("enter word: ");
                String st= scanner.nextLine();
                dos.writeUTF(st+"\t\n");
                dos.flush();
                System.out.println("Server: "+dis.readUTF());
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
