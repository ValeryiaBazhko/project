

import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamResolution;

import java.net.InetAddress;
import java.net.UnknownHostException;

public class WebcamHandler extends Thread {


    @Override
    public void run() {


        Webcam webcam = Webcam.getDefault();
        webcam.setViewSize(WebcamResolution.VGA.getSize());
        Queue.panelQueue.add(webcam);

        while (true) {
            String ip = null;
            try {
                ip = InetAddress.getLocalHost().getHostAddress();
            } catch (UnknownHostException e) {
                throw new RuntimeException(e);
            }
            Message message = new Message(Protocol.VIDEO_MESSAGE, webcam.getImageBytes().array(),ip);
            MessageQueue.messageQueue.add(message);
        }

    }



}
