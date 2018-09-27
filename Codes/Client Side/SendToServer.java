package sample;

import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * Created by USER on 12/18/2015.
 */
public class SendToServer {
    private Socket socket;
    public SendToServer(InfosSendToServer recommending) throws Exception{
        socket = new Socket("localhost",5555);
        ObjectOutputStream outputStream = new ObjectOutputStream(socket.getOutputStream());
        outputStream.writeObject(recommending);
    }
}
