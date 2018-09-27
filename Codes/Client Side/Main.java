package sample;

import com.lynden.gmapsfx.GoogleMapView;
import com.lynden.gmapsfx.MapComponentInitializedListener;
import com.lynden.gmapsfx.javascript.object.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {

    private Stage stage;
    public String name;
    public String address;
    public String foods;
    public int review;
    public int rating;
    public ArrayList<String>gmails = new ArrayList<>();
    private Stage anotherStage = new Stage();
    private Stage reviewStage = new Stage();
    private Double Latitude;
    private Double Longitude;
   // private Stage GmapStage = new Stage();
   // private GoogleMapView mapView = new GoogleMapView();
   // private GoogleMap map;

    @Override
    public void start(Stage primaryStage) throws Exception{
        stage = primaryStage;
        showLoadPage();
    }

    public void showLoadPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Starting.fxml"));

        Parent root = loader.load(); // for list view

        startController controller = loader.getController();
        controller.setMain(this);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 692, 670));

        stage.show();
    }
    public void showStartPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("listview.fxml"));

        Parent root = loader.load(); // for list view

        ListViewController listViewController = loader.getController();
        listViewController.setMain(this);
        stage.setTitle("Hello World");
        stage.setScene(new Scene(root, 692, 670));

        stage.show();
    }

    public void showRestuarantPage() throws Exception{
        FXMLLoader anotherLoader = new FXMLLoader();
        anotherLoader.setLocation(getClass().getResource("MADCHEF.fxml"));
        Parent anotherRoot = anotherLoader.load();

        MadchefController controller = anotherLoader.getController();
        controller.setMain(this);
        controller.setName(this.name);
        anotherStage.setTitle("Hello World");
        anotherStage.setScene(new Scene(anotherRoot, 395, 670));
        anotherStage.show();
    }

 /*   public void showGoogleMap(Double latitude,Double longitude){
        this.Latitude=latitude;
        this.Longitude=longitude;
        GoogleMapView mapView = new GoogleMapView();
        mapView.addMapInializedListener(this);
        GmapStage.setScene(new Scene(mapView));
        GmapStage.setTitle("Google Map");
        GmapStage.setHeight(400);
        GmapStage.setWidth(400);
        GmapStage.setX(800);
        GmapStage.show();
    }

    @Override
    public void mapInitialized() {
        //Set the initial properties of the map.
        MapOptions mapOptions = new MapOptions();

        mapOptions.center(new LatLong(Latitude, Longitude))
                .mapType(MapTypeIdEnum.ROADMAP)
                .overviewMapControl(false)
                .panControl(false)
                .rotateControl(false)
                .scaleControl(false)
                .streetViewControl(false)
                .zoomControl(false)
                .zoom(15);

        map = mapView.createMap(mapOptions);

        MarkerOptions markerOptions = new MarkerOptions();

        markerOptions.position( new LatLong(Latitude, Longitude) )
                .visible(Boolean.TRUE)
                .title("My Marker");

        Marker marker = new Marker( markerOptions );

        map.addMarker(marker);
    } */


    public void showRankingPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Rating.fxml"));
        Parent anotherRoot = loader.load();

        RatingController ratingController = loader.getController();
        ratingController.setName(this.name);
        ratingController.setGmails(this.gmails);
        ratingController.setMain(this);
        anotherStage.setScene(new Scene(anotherRoot,395,670));
        anotherStage.show();
    }

    public void showThanksPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("thanks.fxml"));
        Parent anotherRoot = loader.load();

        anotherStage.setScene(new Scene(anotherRoot, 395, 670));
        anotherStage.show();
    }

    public void showReviewPage() throws Exception{
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("Review.fxml"));
        Parent reviewRoot = loader.load();

        ReviewController reviewController = loader.getController();
        reviewController.setMain(this);
        reviewController.setRestuarantName(this.name);

        reviewStage.setScene(new Scene(reviewRoot,350,500));
        reviewStage.show();

    }
    public static void main(String[] args) {
        launch(args);
    }
}
