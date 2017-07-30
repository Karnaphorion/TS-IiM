import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;

import java.net.*;


public class Client {


    public static void main(String[] zero){

        

        Socket socket;

        try {

        socket = new Socket("localhost",8001);
        
        BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
        String s;
        while ((s = in .readLine()) != null) {
            System.out.println("Le serveur dit : " + s);
            if (s.isEmpty()) {
                break;
            }
        }
        
        out.write("Je suis un putin de lapin.");

        socket.close();

        } catch (IOException e) {

            

            e.printStackTrace();

        }

    }

}