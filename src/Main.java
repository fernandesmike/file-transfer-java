import clients.Client;
import server.FileServer;

public class Main {
    public static void main(String[] args) {

        Client client  = new Client();
        FileServer server = new FileServer();

        Thread clientThread = new Thread(client);
        Thread serverThread = new Thread(server);

        serverThread.start();
        clientThread.start();
    }
}
