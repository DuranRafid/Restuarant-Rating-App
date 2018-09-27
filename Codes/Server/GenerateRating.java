package sample;

import java.sql.ResultSet;

/**
 * Created by USER on 12/14/2015.
 */

/* Server Side */
public class GenerateRating {
    String restuarantName;
    String gmail;
    int serviceRating;
    int priceRating;
    int envirRating;
    float averageServiceRating;
    float averagePriceRating;
    float averageEnvirRating;
    int count;
    int sumofPriceRating=0;
    int sumofServiceRating=0;
    int sumofEnvirRating=0;
    float rating;
    String comments;

    public GenerateRating(InfosSendToServer infosSendToServer) {
        restuarantName = infosSendToServer.getName();
        if(restuarantName.endsWith(" ")) restuarantName = restuarantName.substring(0,restuarantName.length()-1);
        gmail = infosSendToServer.getGmail();
        serviceRating = infosSendToServer.getServiceRating();
        priceRating = infosSendToServer.getPriceRating();
        envirRating = infosSendToServer.getEnvirRating();
        comments = infosSendToServer.getComments();
        generate(restuarantName);
    }

    public void generate(String name){
        MySQLConnect connection = new MySQLConnect("localhost","test","root","");

        try {
            String query = "INSERT INTO `" + restuarantName +"` VALUES('"+ gmail +"',"+ serviceRating+"," + priceRating +"," + envirRating+",'"+comments+"')";
            System.out.println(query);
            connection.updateDB(query);
            String query1 ="select *from `"+restuarantName+"`";
            System.out.println(query1);
            ResultSet rs =connection.searchDB(query1);
            while(rs.next()){
                sumofServiceRating += rs.getInt("serviceRating");
                sumofPriceRating += rs.getInt("priceRating");
                sumofEnvirRating +=rs.getInt("envirRating");
                count++;
            }
            averageServiceRating = sumofServiceRating/count;
            averagePriceRating = sumofPriceRating/count;
            averageEnvirRating = sumofEnvirRating/count;
            rating = (averageEnvirRating+averagePriceRating+averageServiceRating)/3;
            System.out.println(averageEnvirRating+","+averageServiceRating+","+averagePriceRating+","+rating+","+count);

            String query2 = "update foodiesDhaka set serviceRating="+averageServiceRating+",priceRating="+averagePriceRating+",envirRating="+averageServiceRating+",rating="+rating+",reviews="+count+" where name='"+name+"'";
            System.out.println(query2);
            connection.updateDB(query2);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
