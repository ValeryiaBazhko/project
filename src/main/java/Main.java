
import java.io.IOException;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Main {


    public static String serverIp = "192.168.1.14";
    public static int port = 5000;
    public static Server server;


    public static void main(String[] args) throws IOException, InterruptedException {

        ExecutorService service = Executors.newCachedThreadPool();
        P2PServer p2pServer = new P2PServer(service);
        service.submit(p2pServer);

        WebcamHandler webcamHandler = new WebcamHandler();
        service.submit(webcamHandler);

        MainFrame.init();
        Socket connection = new Socket(serverIp, port);
        server = new Server(connection);
        service.submit(server);

        String ip = null;
        try {
            ip = InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException(e);
        }
        server.sendMessage(new Message(Protocol.HELLO, null,ip));
    }

}
