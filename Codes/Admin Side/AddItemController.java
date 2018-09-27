package sample;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddItemController {


    public TextField AreaOfTheItem;
    private AdminMain main;
    private String name;
    private String address;
    private String area;
    private String foodType;
    private Restaurant res;

    @FXML
    private TextField NameoftheItem;

    @FXML
    private TextField AddressoftheItem;

    @FXML
    private TextField FoodtypesoftheItem;

    @FXML
    private Button AddButton;

    @FXML
    void AddButtonClicked(ActionEvent event) {
        name=NameoftheItem.getText();
        address = AddressoftheItem.getText();
        area = AreaOfTheItem.getText();
        foodType = FoodtypesoftheItem.getText();
       if( name.equals("") || address.equals("") || foodType.equals("")){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("INSUFFICIENT INFORMATION");
            alert.setHeaderText("Not enough informartion");
            alert.setContentText("Enter valid information");
            alert.getDialogPane().setPrefSize(430, 150);
            alert.showAndWait();

        }
        else {
          //  main.AddItemStage.close();
           MySQLConnect oc = new MySQLConnect("localhost","test","root","");
           String query = "INSERT INTO foodiesDhaka(name,address,area,foodType) values('"+name+"','"+address+"','"+area+"','"+foodType+"')";
           String query2 = "CREATE TABLE " + name+"(persons VARCHAR(50) NOT NULL,serviceRating INT(11),priceRating INT(11),envirRating INT(11),Comments VARCHAR(1000) DEFAULT 'No Comments Made')";
           try {
               oc.updateDB(query);
               oc.updateDB(query2);
           } catch (Exception e) {
              // e.printStackTrace();
           }
           finally {
               oc.close();
           }
           NameoftheItem.clear();
           AddressoftheItem.clear();
           FoodtypesoftheItem.clear();
           AreaOfTheItem.clear();
       }
    }




    public void setMain(AdminMain main) {
        this.main = main;
    }
}
