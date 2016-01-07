package com.meljin.meals;

import java.text.SimpleDateFormat;

/**
 * Created by s_jin01 on 11/25/15.
 */
public class Date {

    public String getDateString(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        SimpleDateFormat sdf = new SimpleDateFormat("MMM dd, yyyy");
        return sdf.format(c.getTime());
    }

    public int getCurrentDate(){
        java.util.Calendar c = java.util.Calendar.getInstance();
        return c.get(java.util.Calendar.DATE);
    }
}
