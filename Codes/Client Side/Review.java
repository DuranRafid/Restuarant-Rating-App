package sample;

/**
 * Created by USER on 12/19/2015.
 */
public class Review {
    private String gmail;
    private int serviceRating;

    public String getComments() {
        return comments;
    }

    public void setComments(String comments) {
        this.comments = comments;
    }

    private String comments;

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

    private int priceRating;
    private int envirRating;

    public Review(String gmail,int serviceRating,int priceRating,int envirRating,String comments){
        this.gmail = gmail;
        this.serviceRating = serviceRating;
        this.priceRating = priceRating;
        this.envirRating = envirRating;
        this.comments = comments;
    }
}
