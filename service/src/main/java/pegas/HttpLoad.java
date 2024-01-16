package pegas;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public class HttpLoad {
    private static final String url = "https://mail.ru";

    public static void main(String[] args){

        try{
            URL url = new URL(HttpLoad.url);
            HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
            int responseCode = httpURLConnection.getResponseCode();
            if(responseCode==HttpURLConnection.HTTP_OK){
                System.out.println(1);
                try(DataInputStream dis = new DataInputStream(httpURLConnection.getInputStream());
                    DataOutputStream dos = new DataOutputStream(new FileOutputStream("test.txt"))){
                    byte[] buffer = new byte[1024];
                    int read = -1;
                    while ((read = dis.read(buffer))!=-1){
                        System.out.println(new String(buffer, 0, read));
                        dos.write(buffer, 0, read);
                    }

                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
