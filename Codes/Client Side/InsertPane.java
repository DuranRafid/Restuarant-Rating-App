package sample;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;

import java.util.ArrayList;

/**
 * Created by USER on 12/11/2015.
 */
public class InsertPane extends ListCell<String> {
    Main main = new Main();
    Label name = new Label("(empty)");
    Label Area = new Label();
    Label Address = new Label();
    Label Foodtype = new Label();
    Label rating = new Label();
    Label review = new Label();

    StackPane stackpane = new StackPane();
    private ArrayList<Restaurant> listofres;
    private int iterator ;
    public InsertPane(ArrayList<Restaurant> restaurants,int iterator) {
        super();
        Address.setLayoutY(244);
        this.listofres = restaurants;
        this.iterator = iterator;
        stackpane.getChildren().addAll(name, Area, Address, Foodtype);

    }

    @Override
    public void updateItem(String item, boolean empty) {
        super.updateItem(item, empty);
        setText(null);  // No text in label of super class
        if (empty) {

            setGraphic(null);
        } else {
            try {
                Address.setText(listofres.get(iterator).getAddress());
                Address.setTextFill(Color.BROWN);

                stackpane.setAlignment(Address, Pos.BOTTOM_CENTER);

                name.setText(listofres.get(iterator).getName());
                name.setFont(Font.font("Amble Cn", FontWeight.BOLD, 18));
                name.setTextFill(Color.RED);
                // stackpane.setAlignment(Address, Pos.BOTTOM_LEFT);
                Foodtype.setText(listofres.get(iterator).getFoodType());
                Foodtype.setVisible(false);
                //  stackpane.setAlignment(Foodtype, Pos.BOTTOM_RIGHT);
                review.setText(listofres.get(iterator).getReview());
                review.setVisible(false);
                rating.setText(listofres.get(iterator).getRating());
                rating.setVisible(false);
            }
            catch (IndexOutOfBoundsException e){

            }
            stackpane.setStyle("-fx-background-color: #FFFFFF;");
            setGraphic(stackpane);
            stackpane.setOnMousePressed(event -> {
                        try {
                            main.name = name.getText();
                            main.foods = Foodtype.getText();
                            main.address = Address.getText();
                            main.review = Integer.valueOf((review.getText()));
                            main.rating = Integer.valueOf(rating.getText());
                            main.showRestuarantPage();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }

                    }
            );
            setPrefHeight(60);


        }
    }
}
