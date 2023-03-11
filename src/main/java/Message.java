import com.google.gson.Gson;

public class Message {

    private Protocol protocol;
    private String senderIp;
    private byte[] videoData;

    public String body;
    public String signature;

    public String signer;


    public Message(Protocol protocol, byte[] videoData, String senderIp) {
        this.protocol = protocol;
        this.senderIp = senderIp;
        this.videoData = videoData;
    }

    public String toJson() {
        return new Gson().toJson(this);
    }

    public void print() {
        System.out.println(new Gson().toJson(this));
    }

    public void printProtocol() {
        System.out.println(protocol);
    }


    public Protocol getProtocol() {
        return protocol;
    }


    public byte[] getData() {
        return videoData;
    }

    public void setProtocol(Protocol protocol) {
        this.protocol = protocol;
    }

    public String getSenderIp() {
        return senderIp;
    }

    public void setSenderIp(String senderIp) {
        this.senderIp = senderIp;
    }

    public byte[] getVideoData() {
        return videoData;
    }

    public void setVideoData(byte[] videoData) {
        this.videoData = videoData;
    }
}
