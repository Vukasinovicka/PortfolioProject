package utlilities;

import java.util.Date;

public class DataUtils {

    public static  String getTimeStamp(){
        Date data = new Date();
        return data.toString().replaceAll(" : ", "_" ).replaceAll(" ", "_");
    }
}
