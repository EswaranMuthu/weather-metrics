package data;

import java.util.ArrayList;
import java.util.List;

public class TestDatas {


    public static List<String> multiSaveMeasurement(List<String> dt){
        List<String> newDT = new ArrayList<>();
        List<String> result = new ArrayList<>();
        for(int counter = 4 ; counter < dt.size() ; counter = counter + 4){
            newDT.clear();
            for(int i = 0;i< 4 ; i++ )
                newDT.add(dt.get(i));
            for(int j = counter ; (j < dt.size()) && (j < counter + 4) ; j++)
                newDT.add(dt.get(j));
           result.add(validSaveMeasurement(newDT));
        }
        return result ;
    }


    public static String validSaveMeasurement(List<String> dt){
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("{");
        int counter = (dt.size() / 2);
        for(int i= 0 ; i< counter ; i++){
            stringBuffer.append("\""+dt.get(i)+ "\":");
            stringBuffer.append(dt.get(i+counter));
            if(i < counter -1)
                stringBuffer.append(",");
        }
        stringBuffer.append("}");
        String json = stringBuffer.toString();
     return json;
    }



}
