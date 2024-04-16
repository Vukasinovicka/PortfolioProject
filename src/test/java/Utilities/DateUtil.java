package Utilities;

import java.util.Date;

public class DateUtil {

    public static  String getTimeStamp() {
        Date data = new Date();
        return data.toString().replaceAll(" : ", "_").replaceAll(" ", "_");
    }
}
