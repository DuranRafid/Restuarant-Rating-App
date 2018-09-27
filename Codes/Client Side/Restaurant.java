package sample;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

/**
 * Created by user on 10-Dec-15.
 */
public class Restaurant {
    private final SimpleStringProperty name;
    private final SimpleStringProperty address;
    private final SimpleStringProperty foodType;
    private final SimpleStringProperty review;
    private final SimpleStringProperty rating;

    Restaurant(String name, String address, String foodType, Integer review, Integer rating) {
        this.name = new SimpleStringProperty(name);
        this.address = new SimpleStringProperty(address);
        this.foodType = new SimpleStringProperty(foodType);
        this.review = new SimpleStringProperty(Integer.toString(review));
        this.rating = new SimpleStringProperty(Integer.toString(rating));
    }

    public String getReview() {
        return review.get();
    }

    public SimpleStringProperty reviewProperty() {
        return review;
    }

    public void setReview(String review) {
        this.review.set(review);
    }

    public String getRating() {
        return rating.get();
    }

    public SimpleStringProperty ratingProperty() {
        return rating;
    }

    public void setRating(String rating) {
        this.rating.set(rating);
    }

    public String getName() {
        return name.get();
    }
    public void setName(String fName) {
        name.set(fName);
    }

    public String getAddress() {
        return address.get();
    }
    public void setAddress(String fName)
    {
        address.set(fName);
    }

    public String getFoodType() {
        return foodType.get();
    }
    public void setFoodType(String fName) {
        foodType.set(fName);
    }

    public String toString() {
        return name + ", " + address + ", " + foodType;
    }

}
