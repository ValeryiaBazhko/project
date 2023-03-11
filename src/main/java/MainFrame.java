import com.github.sarxos.webcam.Webcam;
import com.github.sarxos.webcam.WebcamPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class MainFrame {

    private static JFrame frame;
    private static JPanel videoPanel;
    private static JPanel controlsPanel;
    private static JPanel infoPanel;
    private static JButton matchBtn;
    private static JPanel myCameraView;



    public static void init() throws InterruptedException {
        frame = new JFrame("ChatBox Client");
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(new Dimension(900,600));
        frame.setLayout(new BorderLayout());

        videoPanel = new JPanel();
        videoPanel.setPreferredSize(new Dimension(640, 480));
        videoPanel.setBackground(Color.RED);

        controlsPanel = new JPanel();
        controlsPanel.setLayout(new FlowLayout());
        controlsPanel.setPreferredSize(new Dimension(200, 480));
        controlsPanel.setBackground(Color.GREEN);

        infoPanel = new JPanel();
        infoPanel.setPreferredSize(new Dimension(640, 120));


        Webcam webcam = Queue.panelQueue.take();
        WebcamPanel panel = new WebcamPanel(webcam);
        panel.setMirrored(true);
        panel.setPreferredSize(new Dimension(160, 120));
        controlsPanel.add(panel);


        matchBtn = new JButton("Match");
        matchBtn.setPreferredSize(new Dimension(160, 40));
        matchBtn.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String ip = null;
                try {
                    ip = InetAddress.getLocalHost().getHostAddress();
                } catch (UnknownHostException ew) {
                    throw new RuntimeException(ew);
                }
                Message message = new Message(Protocol.MATCH_ME,null,ip);
                Main.server.sendMessage(message);
            }
        });
        controlsPanel.add(matchBtn);


        frame.add(infoPanel, BorderLayout.SOUTH);
        frame.add(controlsPanel, BorderLayout.EAST);
        frame.add(videoPanel, BorderLayout.CENTER);
        frame.pack();
    }
}
