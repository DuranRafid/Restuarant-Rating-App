package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import javafx.util.Callback;

import java.io.*;
import java.net.Socket;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class AdminViewController implements Runnable{

    @FXML
    private TableView<Restaurant> RecommendTable;

    private ArrayList<String> Names = new ArrayList<>();
    private ArrayList<String> Addresses = new ArrayList<>();
    private ArrayList<String> FoodTypes = new ArrayList<>();
    private ArrayList<String> Areas = new ArrayList<>();

    private ObjectInputStream objectInputStream;



    private ObjectOutputStream objectOutputStream;

    public ArrayList<String> getNames() {
        return Names;
    }

    public void setNames(ArrayList<String> names) {
        Names = names;
    }

    public ArrayList<String> getAddresses() {
        return Addresses;
    }

    public void setAddresses(ArrayList<String> addresses) {
        Addresses = addresses;
    }

    public ArrayList<String> getFoodTypes() {
        return FoodTypes;
    }

    public void setFoodTypes(ArrayList<String> foodTypes) {
        FoodTypes = foodTypes;
    }

    public ArrayList<String> getAreas() {
        return Areas;
    }

    public void setAreas(ArrayList<String> areas) {
        Areas = areas;
    }

    @FXML
    private TableColumn<Restaurant,String> Name;
    @FXML
    private TableColumn<Restaurant,String> Address;
    @FXML
    private TableColumn<Restaurant,String> Area;
    @FXML
    private TableColumn<Restaurant,String> FoodType;
    @FXML
    private TableColumn<Restaurant,String> Add ;
    @FXML
    private TableColumn<Restaurant,String> Ignore;
    ObservableList<Restaurant> List = FXCollections.observableArrayList();



    public void initialize(){
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


        Add.setCellValueFactory(new PropertyValueFactory<Restaurant, String>("button"));
        Callback<TableColumn<Restaurant, String>, TableCell<Restaurant, String>> cellFactory =
                new Callback<TableColumn<Restaurant, String>, TableCell<Restaurant, String>>() {
                    @Override
                    public TableCell call( final TableColumn<Restaurant, String> param ) {
                        final TableCell<Restaurant, String> cell = new TableCell<Restaurant, String>() {
                            final Button btn = new Button("ADD");


                            @Override
                            public void updateItem(String item, boolean empty) {
                                // action of 'Ignore' button click
                                btn.setOnAction( ( javafx.event.ActionEvent event ) -> {
                                            Restaurant temp = getTableView().getItems().get( getIndex() );
                                            new AddInDatabase(temp);
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
        Add.setCellFactory(cellFactory);
        RecommendTable.setItems(List);
    }

    private Socket socket;
    private AdminMain main;

    public AdminViewController(){
        Thread t = new Thread(this);
        t.start();
    }

    public void run(){
        try {
            socket = new Socket("localhost", 5555);
            objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            InfosSendToServer infosSendToServer = new InfosSendToServer("Admin");
            objectOutputStream.writeObject(infosSendToServer);
            System.out.println("Written to server");
            objectInputStream = new ObjectInputStream(socket.getInputStream());

            new ReadThread(this, objectInputStream);
        }
        catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    void AddAction(ActionEvent event) throws IOException {
         main.addItem();
    }

    @FXML
    void DeleteAction(ActionEvent event) throws IOException{
        main.deleteItem();
    }


    public void setMain(AdminMain main) {
        this.main = main;
    }

    public void logOut(ActionEvent actionEvent) throws Exception {
        new AdminLogOut(this.main,this.objectOutputStream);
    }
}
