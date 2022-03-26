package com.example.demo;

import java.io.Serializable;

public class HolidayPojo implements Serializable {

    public int id;
    public String name;
    public int day;
    public int month;
    public int length;

    public HolidayPojo(){}
    public HolidayPojo(int id, String name, int day, int month, int length) {
        this.id = id;
        this.name = name;
        this.day = day;
        this.month = month;
        this.length = length;
    }

    @Override
    public String toString() {
        return "HolidayPojo{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", day=" + day +
                ", month=" + month +
                ", length=" + length +
                '}';
    }
}
