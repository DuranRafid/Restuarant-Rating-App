package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;

import java.util.ArrayList;

/**
 * Created by USER on 12/19/2015.
 */
public class InsertPaneInReview extends ListCell<String> {
    private final ArrayList<Review> listOfReviews;
    private int iterator;
    Label gmail = new Label();
    Label price = new Label();
    Label service = new Label();
    Label envir = new Label();
    Label comments = new Label();

    StackPane stackpane = new StackPane();

    public String convertFromRating(int rating){
        String rate = new String();
        if(rating==5) rate = "Excellent";
        else if(rating==4) rate = "Good";
        else if(rating==3) rate = "Average";
        else if(rating==2) rate = "Below Average";
        else if(rating<2) rate = "Bad";
        return rate;
    }
    public InsertPaneInReview(ArrayList<Review> listOfReviews, int i) {
        super();
        this.iterator = i;
        this.listOfReviews = listOfReviews;
        stackpane.getChildren().addAll(gmail, price, service, envir,comments);
    }

    @Override
    public void updateItem(String item, boolean empty){
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {

            setGraphic(null);
        } else {
            try {
                System.out.println(iterator);
                gmail.setText(listOfReviews.get(iterator).getGmail());
                gmail.setTextFill(Color.BROWN);
                gmail.setVisible(true);
                stackpane.setAlignment(gmail, Pos.TOP_LEFT);

                service.setText("Service: " + convertFromRating(listOfReviews.get(iterator).getServiceRating()));
                service.setTextFill(Color.BLACK);
                service.setVisible(true);
                stackpane.setAlignment(service, Pos.CENTER_LEFT);

                price.setText("Price: " + convertFromRating(listOfReviews.get(iterator).getPriceRating()));
                price.setTextFill(Color.BLACK);
                stackpane.setAlignment(price, Pos.CENTER);

                envir.setText("Environment: " + convertFromRating(listOfReviews.get(iterator).getEnvirRating()));
                envir.setTextFill(Color.BLACK);
                stackpane.setAlignment(envir, Pos.CENTER_RIGHT);

                comments.setText(listOfReviews.get(iterator).getComments());
                comments.setTextFill(Color.BLUE);
               // comments.setVisible(true);
                stackpane.setAlignment(comments, Pos.BOTTOM_LEFT);
            }
            catch (IndexOutOfBoundsException e){

            }

            setGraphic(stackpane);
        }
        setPrefHeight(80);
    }
}
