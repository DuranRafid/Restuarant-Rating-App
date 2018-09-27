package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Created by user on 16-Dec-15.
 */
public class AdminMain extends Application {
    private Stage stage;
    public Scene scene3;
    public Stage addItemStage = new Stage();
    public Stage deleteItemStage = new Stage();

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        ShowAdminLoginPage();
    }

    public void ShowAdminLoginPage() throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminLogin.fxml"));
        Parent root = loader.load();
        AdminLoginController controller = loader.getController();
        controller.setMain(this);
     //   Scene scene1 = new Scene(root,366,590);
        stage.setScene(new Scene(root,517,600));
        stage.setTitle("Admin Page");
        stage.show();
    }

    public void AdminPage() throws IOException {
        FXMLLoader loader= new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminView.fxml"));
        Parent root = loader.load();
        AdminViewController controller2 = loader.getController();
        controller2.setMain(this);
       // controller2.ConnectServer();
     //   Scene scene1 = new Scene(root,366,590);
        stage.setScene(new Scene(root,760,570));
        stage.setTitle("AdminWindow Page");
        stage.show();
    }

    public void addItem() throws IOException {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AddItem.fxml"));
        Parent root = loader.load();
        AddItemController controller3 = loader.getController();
        controller3.setMain(this);
    //    scene3 = new Scene(root,600,382);
        addItemStage.setScene(new Scene(root,600,430));
        addItemStage.setTitle("AddItem Page");
        addItemStage.show();
    }
    public void deleteItem() throws IOException{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("AdminDelete.fxml"));
        Parent root = loader.load();
        AdminDeleteController controller3 = loader.getController();
        controller3.setMain(this);
        //    scene3 = new Scene(root,600,382);
        deleteItemStage.setScene(new Scene(root,625,500));
        deleteItemStage.setTitle("AddItem Page");
        deleteItemStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
