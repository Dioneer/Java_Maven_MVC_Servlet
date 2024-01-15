package pegas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketServer {
    public static void main(String[] args) {
        try(ServerSocket serverSocket = new ServerSocket(8082)) {
            while(true){
            Runnable runnable =()->{
                try(Socket socket = serverSocket.accept(); DataInputStream dis = new DataInputStream(socket.getInputStream());
                    DataOutputStream dos = new DataOutputStream(socket.getOutputStream())){
                    while(true){
                        String st = dis.readUTF();
                        if(st.equals("close\t\n"))break;
                        System.out.println("Client: "+ st);
                        dos.writeUTF("Send");
                        dos.flush();
                    }
                }catch(IOException e)
                {throw new RuntimeException(e);}
            };
                Thread tr = new Thread(runnable);
                tr.start();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
