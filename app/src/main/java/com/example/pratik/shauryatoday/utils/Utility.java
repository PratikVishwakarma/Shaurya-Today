package com.example.pratik.shauryatoday.utils;

/**
 * Created by prati on 22-May-16.
 */
public class Utility {

    public static final String IMAGE_URL_PATH = "http://shauryatoday.com/";

    public String AdjustDate(String date){
        String updateDate = null;
        String[] splitDate = date.split("-");
        int month = Integer.parseInt(splitDate[1]);
        switch (month){
            case 1:
                updateDate = "Jan";
                break;
            case 2:
                updateDate = "Feb";
                break;
            case 3:
                updateDate = "Mar";
                break;
            case 4:
                updateDate = "Apr";
                break;
            case 5:
                updateDate = "May";
                break;
            case 6:
                updateDate = "June";
                break;
            case 7:
                updateDate = "July";
                break;
            case 8:
                updateDate = "Aug";
                break;
            case 9:
                updateDate = "Sep";
                break;
            case 10:
                updateDate = "Oct";
                break;
            case 11:
                updateDate = "Nov";
                break;
            case 12:
                updateDate = "Dec";
                break;
            default:
                break;
        }
        String datereplace = splitDate[0];
        if(splitDate[0].charAt(0) == '0'){
             datereplace = splitDate[0].replace('0', ' ');
        }
        return datereplace+" "+updateDate;
    }

}
