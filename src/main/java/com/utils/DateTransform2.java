package com.utils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateTransform2 {
public static  Date sToD(String s){
    Date d=null;
    String format="yyyy-MM-dd";
    DateFormat df=new SimpleDateFormat(format);
    try {
        d=df.parse(s);
    } catch (java.text.ParseException e) {
        e.printStackTrace();
    }
    return d;
}
public static String dToS(Date d){
    String s=null;
    String format="yyyy-MM-dd";
    DateFormat df=new SimpleDateFormat(format);
    s=df.format(d);
    return s;
}


}
