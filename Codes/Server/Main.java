package sample;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class Main extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("sample.fxml"));
        Parent root = loader.load();

        Controller controller = loader.getController();
        controller.setMain(this);
        primaryStage.setTitle("Server");
        primaryStage.setScene(new Scene(root, 550, 400));
        primaryStage.setOnCloseRequest(event -> {
            try {
                controller.getServerSocket().close();
            } catch (Exception e) {

            }
        });
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
