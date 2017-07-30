import java.io.*;
import java.net.*;

public class Serveur {

   private static Thread t;
   private static HttpServer httpserver;
   
   public Serveur(HttpServer serv) {
	   httpserver=serv;
        try {
            ServerSocket server = new ServerSocket(8001);
            t = new Thread(new acceptClient(server));
            t.start();
        } catch (Exception e) {}
   }
   
   public static void closeServeur(){
	   //DO SOMETHING
   }
   public static HttpServer getHttpServer(){
	   return httpserver;
   }
}

class acceptClient implements Runnable {

    private ServerSocket server;
    private Socket client;
    public acceptClient(ServerSocket sock) {
        server = sock;
    }

    public void run() {

        try {
            while (true) {
                client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String s;
                String s2 ="";
                while ((s = in .readLine()) != null) {
                    System.out.println("Le client dit : " + s);
                    s2 = s2 +s;
                    if (s.isEmpty()) {
                        break;
                    }
                }
                Serveur.getHttpServer();
				HttpServer.addMessage(s2);
                out.write("bien reçu, terminé");
                out.close();
                in.close();
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}