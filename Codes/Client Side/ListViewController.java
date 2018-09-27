package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.util.ArrayList;

import static javafx.scene.paint.Color.color;

public class ListViewController  {

    public int iterator = 0;
    @FXML
    public   ListView<String> listView;
    public TextField recommendedName;
    public TextField SearchedRestuarant;
    public RadioButton RatingSort;
    public RadioButton NameSort;
    String area;
    String foodType;
    private ArrayList<String> areaList = new ArrayList<>();



    private ArrayList<String> foodList = new ArrayList<>();

    public ArrayList<String> getFoodList() {
        return foodList;
    }
    public TextField getRecommendedName() {
        return recommendedName;
    }

    public TextField getRecommendedAddress() {
        return recommendedAddress;
    }

    public TextField getRecommendedArea() {
        return recommendedArea;
    }

    public TextField getRecommendedFoods() {
        return recommendedFoods;
    }

    public TextField recommendedAddress;
    public TextField recommendedArea;
    public TextField recommendedFoods;


    @FXML
    private ComboBox<String> Area;

    @FXML
    private ComboBox<String> Foodtype;

    public ArrayList<Restaurant> listofres = new ArrayList<Restaurant>();

    public ArrayList<String> nameofRes = new ArrayList<>();
    public ArrayList<String> foods = new ArrayList<>();
    public ArrayList<String> adresses = new ArrayList<>();

    public ArrayList<Integer> getReviews() {
        return reviews;
    }

    public ArrayList<String> getAreaList(){
        return areaList;
    }
    public ArrayList<Integer> getRatings() {
        return ratings;
    }

    public ArrayList<Integer> reviews = new ArrayList<>();
    public ArrayList<Integer> ratings = new ArrayList<>();

    public ArrayList<Restaurant> getListofres() {
        return listofres;
    }

    public ArrayList<String> getNameofRes() {
        return nameofRes;
    }

    public ArrayList<String> getFoods() {
        return foods;
    }

    public ArrayList<String> getAdresses() {
        return adresses;
    }

    public ObservableList<String> getNames() {
        return names;
    }

    public ListView<String> getListView() {
        return listView;
    }

    public int numOfItem;
    private Main main;
    final  ObservableList<String> names = FXCollections.observableArrayList();


    public void initialize() {
        System.out.println(Area.getValue()+ "  "+Foodtype.getValue());
        MySQLConnect oc=new MySQLConnect("localhost","test","root","");
        try
        {
            String query = "select * from foodiesdhaka";
            System.out.println(query);
            ResultSet rs = oc.searchDB(query);
            int i = 0;
            areaList.add("All");
            foodList.add("All");
            while(rs.next())
            {
                nameofRes.add(rs.getString("Name"));
                adresses.add(rs.getString("Address"));
                foods.add(rs.getString("FoodType"));
                reviews.add(rs.getInt("reviews"));
                ratings.add(rs.getInt("rating"));
                if(!areaList.contains(rs.getString("Area"))) areaList.add(rs.getString("Area"));
                i++;
            }
            numOfItem = i;
            new MakeFoodList(this);
        }
        catch(Exception e)
        {
            System.out.println("Exception in listProducts: " + e);
        }
        finally
        {
            oc.close();
        }
      //  listofres.add(new Restaurant(null,null,null,null,null));
        for(int j=0;j<numOfItem;j++) {
            listofres.add(new Restaurant(nameofRes.get(j), adresses.get(j), foods.get(j), reviews.get(j),ratings.get(j)));
        }
        names.addAll(nameofRes);
        listView.setItems(names);
        if(iterator<names.size()) {
            listView.setCellFactory(new Callback<ListView<String>, ListCell<String>>() {
                @Override
                public ListCell<String> call(ListView<String> param) {
                    return new InsertPane(listofres, iterator++);
                }
            });
        }
       // listView.itemsProperty().bind(names);
        Area.setItems(FXCollections.observableArrayList(areaList));
        Foodtype.setItems(FXCollections.observableArrayList(foodList));
        Area.setPromptText("Where area you want to see???");
        Foodtype.setPromptText("What food you want to seek??");


        Area.setOnAction(e -> {
            if (Area.getValue() != null) area = Area.getValue();
            else area = "NA";
            if (Foodtype.getValue() != null) foodType = Foodtype.getValue();
            else foodType = "NA";
            new SortByName(this, area, foodType);
        });

        Foodtype.setOnAction(e ->{
            if (Area.getValue() != null) area = Area.getValue();
            else area = "NA";
            if (Foodtype.getValue() != null) foodType = Foodtype.getValue();
            else foodType = "NA";
            new SortByName(this, area,foodType);
    });
        Area.setEditable(true);
        Foodtype.setEditable(true);

    }


    public void setMain(Main main) { 
        this.main = main;
    }


    public void searchByName(ActionEvent actionEvent) {
        new SearchByName(SearchedRestuarant.getText(),this);
    }

    public void recommend(ActionEvent actionEvent) {
        new Recommend(this);
    }

    public void sortByRating(ActionEvent actionEvent) {
        if(Area.getValue()!=null) area = Area.getValue();
        else area = "NA";
        if(Foodtype.getValue()!=null) foodType = Foodtype.getValue();
        else foodType ="NA";
        new SortByRating(this,area,foodType);
    }

    public void sortByName(ActionEvent actionEvent)
    {
        if(Area.getValue()!=null) area = Area.getValue();
        else area = "NA";
        if(Foodtype.getValue()!=null) foodType = Foodtype.getValue();
        else foodType ="NA";
        new SortByName(this,area,foodType);
    }
}
