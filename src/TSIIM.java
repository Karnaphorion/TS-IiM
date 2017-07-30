
public class TSIIM {
	
	private static HttpServer httpserver;
	private static Serveur serv;
	public static void main(String[] args) {
		httpserver = new HttpServer();
		serv = new Serveur(httpserver);
		HttpServer.addMessage("Patate");
	}
}
