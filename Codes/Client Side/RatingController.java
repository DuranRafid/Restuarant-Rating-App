package sample;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.awt.*;
import java.util.ArrayList;

/**
 * Created by USER on 12/8/2015.
 */
public class RatingController implements Runnable{
    public TextField gmailID;
    public TextArea comments;
    private int serviceRating;
    private int priceRating;
    private int envirRating;
    private String name;
    private Main main;
    
    private ArrayList<String> gmails = new ArrayList<>();

    
    public void excellentService(ActionEvent actionEvent) {
        serviceRating = 5;
    }

    public void goodService(ActionEvent actionEvent) {
        serviceRating = 4;
    }

    public void averageService(ActionEvent actionEvent) {
        serviceRating = 3;
    }

    public void belowAverageService(ActionEvent actionEvent) {
        serviceRating = 2;
    }

    public void badService(ActionEvent actionEvent) {
        serviceRating = 1;
    }

    public void excellentPrice(ActionEvent actionEvent) {
        priceRating = 5;
    }

    public void goodPrice(ActionEvent actionEvent) {
        priceRating = 4;
    }

    public void averagePrice(ActionEvent actionEvent) {
        priceRating = 3;
    }

    public void belowAveragePrice(ActionEvent actionEvent) {
        priceRating = 2;
    }

    public void badPrice(ActionEvent actionEvent) {
        priceRating = 1;
    }

    public void excellentEnvir(ActionEvent actionEvent) {
        envirRating = 5;
    }

    public void goodEnvir(ActionEvent actionEvent) {
        envirRating = 4;
    }

    public void averageEnvir(ActionEvent actionEvent) {
        envirRating = 3;
    }

    public void badEnvir(ActionEvent actionEvent) {
        envirRating = 2;
    }

    public void belowAverageEnvir(ActionEvent actionEvent) {
        envirRating = 1;
    }

    public static boolean isValidEmailAddress(String email) {
        boolean result = true;
        try {
            InternetAddress emailAddr = new InternetAddress(email);
            emailAddr.validate();
        } catch (AddressException ex) {
            result = false;
        }
        return result;
    }

    public void submit(ActionEvent actionEvent) {
        Thread t = new Thread(this);
        t.start();
    }

    @Override
    public void run() {
        try {
            if(isValidEmailAddress(gmailID.getText())==false){
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        Alert alert = new Alert(Alert.AlertType.WARNING);
                        alert.setHeaderText("ENTER VALID EMAIL ID");
                        alert.setContentText("INVALID EMAIL ADDRESS");
                        alert.showAndWait();
                    }
                });

            }
            else if(gmails.contains(gmailID.getText())){
                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setHeaderText("GMAIL ID ALREADY USED");
                alert.setContentText("PROVIDE ANOTHER GMAIL ID");
                alert.showAndWait();
            }
            else {
                InfosSendToServer info = new InfosSendToServer("Rating", name, gmailID.getText(), serviceRating, priceRating, envirRating, comments.getText());
                new SendToServer(info);
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
                        try {
                            main.showThanksPage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                });
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setGmails(ArrayList<String> gmails) {
        this.gmails = gmails;
        for (int i=0;i<gmails.size();i++){
            System.out.println(gmails.get(i));
        }
    }
}
