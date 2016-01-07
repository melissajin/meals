package com.meljin.meals;

/**
 * Created by s_jin01 on 11/25/15.
 */
public class Foods {

    int id;
    String date;
    String food;

    public Foods(){
    }

    public Foods(int id, String date, String food){
        this.id = id;
        this.date = date;
        this.food = food;
    }

    public Foods(String date, String food){
        this.date = date;
        this.food = food;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getFood() {
        return food;
    }

    public void setFood(String food) {
        this.food = food;
    }

}
