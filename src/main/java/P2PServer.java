import java.io.IOException;
import java.net.ServerSocket;
import java.util.concurrent.ExecutorService;

public class P2PServer extends Thread {

    private ExecutorService service;
    private int port = 9000;

    public P2PServer(ExecutorService service) {
        this.service = service;
    }

    @Override
    public void run() {


        try {
            ServerSocket serverSocket = new ServerSocket(port);
            while (true) {
                Client client = new Client(serverSocket.accept());
                service.submit(client);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
