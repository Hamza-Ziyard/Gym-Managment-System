package com.jetbrain;

public class Date {

    private final int day;
    private final int month;
    private final int year;

    public Date(int day, int month, int year) {
        this.year=year;
       this.month=month;
       this.day=day;
    }


    //details to be printed after questions answered.
    @Override
    public String toString() {
        return day+"/"+month+"/"+year;
    }

    }




