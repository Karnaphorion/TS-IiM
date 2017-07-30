import java.io.*;
import java.net.*;

public class HttpServer {

   private static String message = "<h1>Server Online and Running</h1>";
   private static Thread t;
   
   public HttpServer() {
        try {
            System.out.println("[Demarrage]");
            ServerSocket server = new ServerSocket(8000);
            server.getInetAddress();
			setMessage(getMessage()+"<br/>Server address : "+InetAddress.getLocalHost()+" : "+server.getLocalPort());
            t = new Thread(new acceptHttpClient(server));
            t.start();
        } catch (Exception e) {}
   }
   
   public static String getMessage(){
	   return message;
   }
   public static void setMessage(String s){
	   message = s;
   }
   public static void addMessage(String s){
	   message = message + "<br/>" + s;
   }
   public static void closeHttpServer(){
	   //DO SOMETHING
   }
}

class acceptHttpClient implements Runnable {

    private ServerSocket server;
    private Socket client;
    public acceptHttpClient(ServerSocket sock) {
        server = sock;
    }

    public void run() {

        try {
            while (true) {
                client = server.accept();
                BufferedReader in = new BufferedReader(new InputStreamReader(client.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(client.getOutputStream()));
                String s;
                while ((s = in .readLine()) != null) {
                    System.out.println("Le client dit : " + s);
                    if (s.isEmpty()) {
                        break;
                    }
                }
                System.out.println("[Serveur répond : "+HttpServer.getMessage()+"]");
                out.write("HTTP/1.0 200 OK\r\n");
                out.write("Content-Type: text/html\r\n");
                out.write("\r\n");
                out.write("<TITLE>TSIIM</TITLE>");
                out.write(HttpServer.getMessage());
                System.out.println("[Fin de connection]");
                out.close(); in .close();
                client.close();
            }

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}