package sample;

import javafx.beans.property.SimpleStringProperty;

/**
 * Created by user on 10-Dec-15.
 */
public class Restaurant {
    private final SimpleStringProperty Name;
    private final SimpleStringProperty Address;
    private final SimpleStringProperty Area;
    private final SimpleStringProperty FoodType;


    Restaurant(String name, String address,String Area, String foodType) {
        this.Name = new SimpleStringProperty(name);
        this.Address = new SimpleStringProperty(address);
        this.Area = new SimpleStringProperty(Area);
        this.FoodType = new SimpleStringProperty(foodType);

    }

    public String getArea() {
        return Area.get();
    }

    public SimpleStringProperty areaProperty() {
        return Area;
    }

    public void setArea(String area) {
        this.Area.set(area);
    }

    public String getName() {
        return Name.get();
    }
    public void setName(String fName) {
        Name.set(fName);
    }

    public String getAddress() {
        return Address.get();
    }
    public void setAddress(String fName)
    {
        Address.set(fName);
    }

    public String getFoodType() {
        return FoodType.get();
    }
    public void setFoodType(String fName) {
        FoodType.set(fName);
    }

    public String toString() {
        return Name + ", " + Address + ", " + FoodType;
    }

}
