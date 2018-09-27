package sample;

import javafx.event.Event;
import javafx.scene.control.Label;

import java.util.Timer;

/**
 * Created by USER on 12/23/2015.
 */
public class startController {

    public Label clickToContinue = new Label();
    private Main main;


    public void load(Event event) {
        try {
            main.showStartPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }
}
