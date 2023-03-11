import com.google.gson.Gson;
import java.io.*;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Map;
import java.util.Objects;

public class Client extends Thread {
    private Socket connection;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;


    public Client(Socket connection) throws UnknownHostException {
        this.connection  = connection;
        this.gson = new Gson();
        try {
            in  = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            out = new BufferedWriter(new OutputStreamWriter(connection.getOutputStream()));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public void run() {
        System.out.println();
        System.out.println("Connection established with: " + connection.getInetAddress().getHostAddress());
        String line;
        try {
            while ((line = in.readLine()) != null) {
                Message message = gson.fromJson(line, Message.class);
                String host = connection.getInetAddress().getHostAddress();

                System.out.println(gson.toJson(message));

            }
        } catch (Exception e) {
            try {
                in.close();
                out.close();
                connection.close();
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
    }

    public void sendMessage(Message message) {
        try {
            // message still needs to be signed, don't understand why but cool
            out.write(gson.toJson(message));
            out.newLine();
            out.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
