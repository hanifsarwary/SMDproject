package com.example.mhanif.project.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

/**
 * Created by M.hanif on 4/16/2018.
 */
@Entity(foreignKeys = @ForeignKey(entity = User.class,parentColumns = "Email",childColumns = "ownerid"))
public class Shop {
    @PrimaryKey
  @NonNull  public String sid;
    public String shop_name;
    public String longitude;
    public String latitude;
    public String Contact;
    public String address;
    public String ownerid;
    public Shop(){

    }

    public Shop(String shop_name,String address){
        this.shop_name=shop_name;
        this.address=address;
    }

    public Shop(String shop_name, String longitude, String latitude, String contact, String address, String ownerid) {
        this.shop_name = shop_name;
        this.longitude = longitude;
        this.latitude = latitude;
        Contact = contact;
        this.address = address;
        this.ownerid = ownerid;
    }

    public Shop(String sid, String shop_name, String longitude, String latitude, String contact, String address, String ownerid) {
        this.sid = sid;
        this.shop_name = shop_name;
        this.longitude = longitude;
        this.latitude = latitude;
        Contact = contact;
        this.address = address;
        this.ownerid = ownerid;
    }
}
