package sample;

import java.io.ObjectInputStream;
import java.util.ArrayList;

/**
 * Created by USER on 12/22/2015.
 */
public class ReadThread implements Runnable{
    private ObjectInputStream objectInputStream;
    private ArrayList<String> Names;
    private ArrayList<String> Addresses;
    private ArrayList<String> Areas;
    private ArrayList<String> FoodTypes;
    private AdminViewController adminViewController;

    public ReadThread(AdminViewController adminViewController, ObjectInputStream objectInputStream) {
        this.adminViewController = adminViewController;
        this.objectInputStream = objectInputStream;
        Thread thread = new Thread(this);
        thread.start();
    }

    public void run(){
        try {
                Names = (ArrayList<String>) objectInputStream.readObject();
                adminViewController.setNames(Names);
                System.out.println("Accepted Name");
                Addresses = (ArrayList<String>) objectInputStream.readObject();
                adminViewController.setAddresses(Addresses);
                System.out.println("Accepted Addresses");
                Areas = (ArrayList<String>) objectInputStream.readObject();
                adminViewController.setAreas(Areas);
                System.out.println("Accepted Areas");
                FoodTypes = (ArrayList<String>) objectInputStream.readObject();
                adminViewController.setFoodTypes(FoodTypes);
                System.out.println("Accepted FoodTypes");

                System.out.println(Names.size());

            }
            catch(Exception e){

            }
            adminViewController.initialize();

    }
}
