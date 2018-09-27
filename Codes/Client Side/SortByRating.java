package sample;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.ResultSet;

/**
 * Created by USER on 12/13/2015.
 */
public class SortByRating {
    int iterator;
    int numOfItem;
    String area ;
    String foodtype ;
    String query;
    ListViewController listViewController = new ListViewController();

    public SortByRating(ListViewController listViewController,String area, String foodType){
        this.listViewController = listViewController;
        this.area = area;
        this.foodtype = foodType;
        this.sortByRating();
    }

    private void sortByRating() {
        iterator = 0;
        MySQLConnect oc=new MySQLConnect("localhost","test","root","");

            if ((area.equals("All") || area.equals("NA")) && (foodtype.equals("NA")||foodtype.equals("All"))) {
                query = "select * from foodiesDhaka order by rating DESC";
                System.out.println(query);
            }
            else if ((foodtype.equals("NA")||foodtype.equals("All")) && !(area.equals("All") || area.equals("NA"))) {
                query = "select * from foodiesDhaka where area='" + area + "'order by rating DESC";
                System.out.println(query);
            }
            else if(!(foodtype.equals("NA")||foodtype.equals("All")) && (area.equals("All") || area.equals("NA"))){
                query = "select * from foodiesDhaka where foodType like '" + foodtype + "%' OR " + "'%" + foodtype + "' order by rating DESC";
            }
            else{
                query = "select * from foodiesDhaka where foodType like '" + foodtype + "%' OR " + "'%" + foodtype + "' AND area='"+area+"' order by rating DESC";
            }
        this.listViewController.getNameofRes().clear();
        this.listViewController.getAdresses().clear();
        this.listViewController.getFoods().clear();
        this.listViewController.getListofres().clear();
        try{
            ResultSet rs = oc.searchDB(query);
            int i = 0;
            while(rs.next())
            {
                this.listViewController.getNameofRes().add(rs.getString("Name"));
                this.listViewController.getAdresses().add(rs.getString("Address"));
                this.listViewController.getFoods().add(rs.getString("FoodType"));
                i++;
            }
            numOfItem = i;
        } catch (Exception e) {
           // e.printStackTrace();
        }
        finally
        {
            oc.close();
        }
        //listofres.add(new Restaurant(0,null,null,null));
        for(int j=0;j<numOfItem;j++) {

            this.listViewController.getListofres().add(new Restaurant(this.listViewController.getNameofRes().get(j), this.listViewController.getAdresses().get(j), this.listViewController.getFoods().get(j), this.listViewController.getReviews().get(j), this.listViewController.getRatings().get(j)));
        }
        this.listViewController.getNames().clear();
        this.listViewController.getNames().addAll(this.listViewController.getNameofRes());
        this.listViewController.getListView().setItems(this.listViewController.getNames());

        if(iterator<this.listViewController.getNames().size()) {
            this.listViewController.getListView().setCellFactory(new Callback<ListView<String>, ListCell<String>>() {

                @Override
                public ListCell<String> call(ListView<String> param) {

                    return new InsertPane(listViewController.getListofres(), iterator++);


                }
            });
        }

    }

}



