package sample;

/**
 * Created by USER on 12/18/2015.
 */
public class Recommend {
    private String name;
    private String address;
    private String foodType;
    private String area;
    public Recommend(ListViewController listViewController) {
        this.name = listViewController.getRecommendedName().getText();
        this.address = listViewController.getRecommendedAddress().getText();
        this.foodType = listViewController.getRecommendedFoods().getText();
        this.area  = listViewController.getRecommendedArea().getText();
        listViewController.getRecommendedName().clear();
        listViewController.getRecommendedAddress().clear();
        listViewController.getRecommendedArea().clear();
        listViewController.getRecommendedFoods().clear();
        InfosSendToServer recommending = new InfosSendToServer("Recommendation",name,foodType,address,area);
        try {
            new SendToServer(recommending);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
