package sample;

import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 * Created by USER on 12/18/2015.
 */
public class MakeFoodList {

    public MakeFoodList(ListViewController listViewController) {
        for(int i=0;i<listViewController.getFoods().size();i++) {
            String Results[] = listViewController.getFoods().get(i).split(",");
            for(int j=0;j<Results.length;j++) {
                String tobeAdded = prepareToBeEnlisted(Results[j]);
                if(!listViewController.getFoodList().contains(tobeAdded)) {
                    listViewController.getFoodList().add(tobeAdded);
                }

            }
        }

    }

    private String prepareToBeEnlisted(String result) {
        if(result.startsWith(" ")) result = result.substring(1,result.length());
        if(result.endsWith(" ")) result = result.substring(0,result.length()-1);
        if(result.endsWith("s")) result = result.substring(0,result.length()-1);
        return result;
    }
}
