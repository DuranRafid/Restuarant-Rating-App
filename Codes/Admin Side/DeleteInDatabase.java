package sample;

/**
 * Created by USER on 12/23/2015.
 */
public class DeleteInDatabase {
    private Restaurant rest;
    private String name;
    public DeleteInDatabase(Restaurant temp) {
        this.rest = temp;
        this.name = rest.getName();
        delete();
    }

    private void delete() {
        MySQLConnect oc = new MySQLConnect("localhost","test","root","");
        String query = "DELETE FROM foodiesDhaka WHERE name ='"+name+"'";
        try {
            oc.updateDB(query);
        } catch (Exception e) {
            e.printStackTrace();
        }
        finally {
            oc.close();
        }
    }
}
