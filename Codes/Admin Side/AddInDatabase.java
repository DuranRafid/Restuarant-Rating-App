package sample;

/**
 * Created by USER on 12/21/2015.
 */
public class AddInDatabase {
    String name;
    String address;
    String area;
    String foods;

    public AddInDatabase(Restaurant temp) {
        name = temp.getName();
        address = temp.getAddress();
        area = temp.getArea();
        foods = temp.getFoodType();
        add();
    }

    private void add() {
        MySQLConnect oc = new MySQLConnect("localhost","test","root","");
        String query = "Insert into foodiesDhaka(name,address,Area,foodType) values('"+name+"','"+address+"','"+area+"','"+foods+"')";
        String query2 = "CREATE TABLE " + name+"(persons VARCHAR(50) NOT NULL,serviceRating INT(11),priceRating INT(11),envirRating INT(11),Comments VARCHAR(1000) DEFAULT 'No Comments Made')";
        System.out.println(query);
        try {
            oc.updateDB(query);
            oc.updateDB(query2);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            oc.close();
        }
    }
}
