import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;
public class Server extends Thread {

    private Socket connection;
    private BufferedReader in;
    private BufferedWriter out;
    private Gson gson;

    public Server(Socket connection) {
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

                switch (message.getProtocol()) {
                    case MATCH_ME:
                        System.out.println("Matching IP:" + message.getSenderIp());
                        break;
                    default:
                        break;
                }


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
