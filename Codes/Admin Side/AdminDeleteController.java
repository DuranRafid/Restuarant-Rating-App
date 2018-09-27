package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.sql.ResultSet;
import java.util.ArrayList;

/**
 * Created by USER on 12/23/2015.
 */
public class AdminDeleteController {

    public TableColumn<Restaurant,String> Name;
    public TableColumn<Restaurant,String> Address;
    public TableColumn<Restaurant,String> Area;
    public TableColumn<Restaurant,String> FoodType;
    public TableColumn<Restaurant,String> Action;
    public TableView<Restaurant> choiceTable;

    private ArrayList<String> Names = new ArrayList<>();
    private ArrayList<String> Addresses = new ArrayList<>();
    private ArrayList<String> FoodTypes = new ArrayList<>();
    private ArrayList<String> Areas = new ArrayList<>();

    ObservableList<Restaurant> List = FXCollections.observableArrayList();
    private AdminMain main;

    public void initialize(){
        MySQLConnect oc = new MySQLConnect("localhost","test","root","");
        String query = "SELECT * FROM foodiesDhaka";
        try {
            ResultSet rs = oc.searchDB(query);
            while(rs.next()){
                Names.add(rs.getString("name"));
                Addresses.add(rs.getString("address"));
                Areas.add(rs.getString("Area"));
                FoodTypes.add(rs.getString("foodType"));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        try {
            for (int i = 0; i < Names.size(); i++) {
                List.add(new Restaurant(Names.get(i), Addresses.get(i), Areas.get(i), FoodTypes.get(i)));
            }
        }
        catch (IndexOutOfBoundsException e){

        }
        Name.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Name"));
        Name.setCellFactory(TextFieldTableCell.<Restaurant>forTableColumn());
        Name.setVisible(true);
        Address.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Address"));
        Address.setCellFactory(TextFieldTableCell.<Restaurant>forTableColumn());
        Area.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("Area"));
        Area.setCellFactory(TextFieldTableCell.<Restaurant>forTableColumn());
        FoodType.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("FoodType"));
        FoodType.setCellFactory(TextFieldTableCell.<Restaurant>forTableColumn());


        Action.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("button"));
        Callback<TableColumn<Restaurant, String>, TableCell<Restaurant, String>> cellFactory =
                new Callback<TableColumn<Restaurant, String>, TableCell<Restaurant, String>>() {
                    @Override
                    public TableCell call( final TableColumn<Restaurant, String> param ) {
                        final TableCell<Restaurant, String> cell = new TableCell<Restaurant, String>() {
                            final Button btn = new Button("Delete");


                            @Override
                            public void updateItem(String item, boolean empty) {
                                // action of 'Ignore' button click
                                btn.setOnAction( ( javafx.event.ActionEvent event ) -> {
                                            Restaurant temp = getTableView().getItems().get( getIndex() );
                                            new DeleteInDatabase(temp);
                                            List.remove(temp);
                                        }
                                );
                                if(empty)setGraphic(null);
                                else setGraphic(btn);
                                setText(null);
                            }
                        };
                        return cell;
                    }
                };
        Action.setCellFactory(cellFactory);
        choiceTable.setItems(List);
    }
    public void CompletedWork(ActionEvent actionEvent) {
        main.deleteItemStage.close();
    }

    public void setMain(AdminMain adminMain) { 
        this.main = adminMain;
    }
}
