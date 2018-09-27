package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by USER on 12/19/2015.
 */
public class ReviewController implements Runnable{
    public int iterator = 0;
    private int numOfReviews;
    private ArrayList<String> persons = new ArrayList<>();
    private Main main;

    public String getRestuarantName() {
        return restuarantName;
    }

    public void setRestuarantName(String restuarantName) {
        this.restuarantName = restuarantName;
    }

    private String restuarantName ;
    private final ObservableList<String> names = FXCollections.observableArrayList();

    public ListView reviewList ;

    private ArrayList<Review> listOfReviews = new ArrayList<>();

    public void run() {
        MySQLConnect connect = new MySQLConnect("localhost","test","root","");
        if(restuarantName.startsWith(" ")) restuarantName = restuarantName.substring(1,restuarantName.length());
        if(restuarantName.endsWith(" ")) restuarantName = restuarantName.substring(0,restuarantName.length()-1);
        String query = "SELECT *FROM `" + restuarantName +"`";
        System.out.println(query);
        try {
           ResultSet rs = connect.searchDB(query);
        int i = 0;
        while (rs.next()){
            String gmail = rs.getString("persons");
            persons.add(gmail);
            int serviceRating = rs.getInt("serviceRating");
            int priceRating = rs.getInt("priceRating");
            int envirRating = rs.getInt("envirRating");
            String comments = rs.getString("Comments");
            listOfReviews.add(new Review(gmail,serviceRating,priceRating,envirRating,comments));
            i++;
        }
        numOfReviews = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        for (int i=0;i<persons.size();i++){
            System.out.println(persons.get(i));
        }
        names.addAll(persons);
        reviewList.setItems(names);
        if(iterator<numOfReviews-1) {
            reviewList.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new InsertPaneInReview(listOfReviews, iterator++);
                }
            });
        }
    }

    public ReviewController(){
        Thread t = new Thread(this);
        t.start();
    }
    public void setMain(Main main) {
        this.main = main;
    }
}
