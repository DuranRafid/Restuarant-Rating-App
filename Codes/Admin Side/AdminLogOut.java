package sample;

import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 * Created by USER on 12/22/2015.
 */
public class AdminLogOut implements Runnable{
    private AdminMain main;
    private Thread thread;
    private ObjectOutputStream outputStream;

    public AdminLogOut(AdminMain main,ObjectOutputStream outputStream){
        this.main = main;
        this.outputStream = outputStream;
        thread = new Thread(this);
        thread.start();
    }

    public void run() {
        InfosSendToServer infos = new InfosSendToServer("Disconnecting");
        try {
            new SendToServer(infos);
            Platform.runLater(new Runnable() {
                @Override
                public void run() {
                    try {
                        main.ShowAdminLoginPage();
                    }
                    catch (IOException e){
                        e.printStackTrace();
                    }

                }
            });

        }
        catch (Exception e){
            e.printStackTrace();
        }
    }
}
