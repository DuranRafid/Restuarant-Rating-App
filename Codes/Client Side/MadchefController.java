package sample;

import javafx.embed.swing.SwingFXUtils;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.shape.Rectangle;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.net.URL;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.ResourceBundle;

/**
 * Created by USER on 12/11/2015.
 */
public class MadchefController {

    public ImageView ratingImage;
    public Label priceRating;
    public Label environmentRating;
    public Label serviceRating;
    private String restuarantName = new String();
    private ArrayList<String> gmails = new ArrayList<>();
    public Label name = new Label();
    public Label foods = new Label();
    public Label address = new Label();
    public Label reviews = new Label();
    public Label rating = new Label();
    private Main main ;

    

    public void setName(String name){
        restuarantName = name;
        this.name.setText(name);
        initialize();
    }

    public void initialize() {
        MySQLConnect oc = new MySQLConnect("localhost", "test", "root", "");
        if (restuarantName.startsWith(" ")) restuarantName = restuarantName.substring(1, restuarantName.length());
        if (restuarantName.endsWith(" ")) restuarantName = restuarantName.substring(0, restuarantName.length() - 1);
        String query = "SELECT *FROM `" + restuarantName + "`";
        try {
            ResultSet rs = oc.searchDB(query);
            while (rs.next()) {
                gmails.add(rs.getString("persons"));
            }
        } catch (Exception e) {
            // e.printStackTrace();
        }
        String sql = "SELECT *FROM foodiesDhaka WHERE name='" + restuarantName + "'";
        try {
            ResultSet resultSet = oc.searchDB(sql);
            while (resultSet.next()) {
                foods.setText(resultSet.getString("foodType"));
                address.setText(resultSet.getString("address"));
                reviews.setText(resultSet.getString("reviews"));
                rating.setText(resultSet.getString("rating"));
                priceRating.setText(resultSet.getString("priceRating"));
                serviceRating.setText(resultSet.getString("serviceRating"));
                environmentRating.setText(resultSet.getString("envirRating"));
                System.out.println(Double.parseDouble(rating.getText()));
                double rates = Double.parseDouble(rating.getText());
                if(rates>4.5 && rates<=5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>4 && rates<=4.5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\4.5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>3.5 && rates<=4){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\4 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>3 && rates<=3.5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\3.5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>2.5 && rates<=3){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\3 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>2 && rates<=2.5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\2.5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>1.5 && rates<=2){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\2 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>1 && rates<=1.5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\1.5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>0.5 && rates<=1){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\1 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates>0 &&rates<=0.5){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\0.5 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }
                else if(rates==0){
                    BufferedImage bufferedImage;
                    bufferedImage = ImageIO.read(new File("C:\\Users\\USER\\Desktop\\MyJAVAPROJECT\\Client Side\\src\\sample\\5 Star rating\\0 star.png"));
                    Image image = SwingFXUtils.toFXImage(bufferedImage, null);
                    this.ratingImage.setImage(image);
                }

            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    public void rate(ActionEvent actionEvent) {
        try {
            main.gmails = gmails;
            main.showRankingPage();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void setMain(Main main) {
        this.main = main;
    }

    public void readReview(Event event) {
        try {
            main.name = name.getText();
            main.showReviewPage();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void ShowGoogleMap(ActionEvent actionEvent) {
     // main.showGoogleMap(23.77,90.399);

    }
}
