package sample;

import java.io.Serializable;

/**
 * Created by USER on 12/12/2015.
 */
public class InfosSendToServer implements Serializable {

    private static final long serialVersionUID = 1L;
    private String flag;
    private String name;
    private String gmail;
    private String foodType ;
    private String address ;
    private int serviceRating;
    private int priceRating;
    private int envirRating;

    public String getArea() {
        return area;
    }

    private String area;

    public String getComments() {
        return comments;
    }

    private String comments;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGmail() {
        return gmail;
    }

    public void setGmail(String gmail) {
        this.gmail = gmail;
    }

    public int getServiceRating() {
        return serviceRating;
    }

    public void setServiceRating(int serviceRating) {
        this.serviceRating = serviceRating;
    }

    public int getPriceRating() {
        return priceRating;
    }

    public void setPriceRating(int priceRating) {
        this.priceRating = priceRating;
    }

    public int getEnvirRating() {
        return envirRating;
    }

    public void setEnvirRating(int envirRating) {
        this.envirRating = envirRating;
    }

    public InfosSendToServer(String flag, String name, String gmail, int serviceRating, int priceRating, int envirRating, String comments){
        this.flag = flag;
        this.name = name;
        this.gmail = gmail;
        this.serviceRating = serviceRating;
        this.priceRating = priceRating;
        this.envirRating = envirRating;
        this.comments = comments;
    }
    public InfosSendToServer(String flag, String name, String foodType, String address, String area){
        this.flag = flag;
        this.name = name;
        this.foodType = foodType;
        this.address = address;
        this.area = area;
    }

    public InfosSendToServer(String flag){
        this.flag = flag;
    }
    public String getFlag() {
        return flag;
    }

    public String getFoodType() {
        return foodType;
    }

    public String getAddress() {
        return address;
    }
    public void print(){
        System.out.println(flag);
    }


}
