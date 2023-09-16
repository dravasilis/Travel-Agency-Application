package com.example.travelagency.AboutDatabase;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(tableName = "Package",
        foreignKeys = {
        @ForeignKey(entity = Agency.class,
        parentColumns = "id",
        childColumns = "aid",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE),
        @ForeignKey(entity= Trip.class,
        parentColumns = "id",
        childColumns = "tid",
        onDelete = ForeignKey.CASCADE,
        onUpdate = ForeignKey.CASCADE
        )})
public class Package {
    @PrimaryKey
    private int id;
    private int aid;
    private int tid;
    private int duration;
    private String date;
    private int price;
    private String triptype;


    public Package(){};
    public Package(int id, int aid, int tid,int duration, String date, int price,String triptype) {
        this.id = id;
        this.aid = aid;
        this.tid = tid;
        this.date = date;
        this.duration = duration;
        this.price = price;
        this.triptype=triptype;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getAid() {
        return aid;
    }

    public void setAid(int aid) {
        this.aid = aid;
    }

    public int getTid() {
        return tid;
    }

    public void setTid(int tid) {
        this.tid = tid;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTriptype() {
        return triptype;
    }

    public void setTriptype(String triptype) {
        this.triptype = triptype;
    }
}
