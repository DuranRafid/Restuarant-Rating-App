package sample;

import javafx.scene.control.ListCell;
import javafx.scene.control.ListView;
import javafx.util.Callback;

import java.sql.ResultSet;

/**
 * Created by USER on 12/13/2015.
 */
public class SearchByName {
    int iterator;
    String value;
    int numOfItem;
    ListViewController listViewController = new ListViewController();

    public SearchByName(String value,ListViewController listViewController){
        this.listViewController = listViewController;
        this.value = value;
        this.searchByFood();
    }

    private void searchByFood() {
        String query;
        iterator = 0;
        MySQLConnect oc=new MySQLConnect("localhost","test","root","");
        if(value.equals("All")){
            query = "select *from foodiesDhaka";
        }
        else {
            query ="select * from foodiesDhaka where name ='"+ value+"'";
        }
        System.out.println(query);
        this.listViewController.getNameofRes().clear();
        this.listViewController.getAdresses().clear();
        this.listViewController.getFoods().clear();
        try {
            ResultSet rs = oc.searchDB(query);
            int i = 0;
            while(rs.next())
            {
                this.listViewController.getNameofRes().add(rs.getString("Name"));
                this.listViewController.getAdresses().add(rs.getString("Address"));
                this.listViewController.getFoods().add(rs.getString("FoodType"));
                i++;
                System.out.print(rs.getString("Name"));
                System.out.print("	");
                System.out.print(rs.getString("Address"));
                System.out.print("	");
                System.out.println(rs.getString("FoodType"));
            }
            numOfItem = i;
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally
        {
            oc.close();
        }
        this.listViewController.getListofres().clear();
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


