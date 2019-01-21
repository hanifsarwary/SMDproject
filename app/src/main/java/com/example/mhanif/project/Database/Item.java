package com.example.mhanif.project.Database;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.net.Uri;
import android.support.annotation.NonNull;

/**
 * Created by M.hanif on 4/16/2018.
 */
@Entity(tableName = "Item",foreignKeys = @ForeignKey(entity = User.class,parentColumns = "Email",childColumns = "seller_id"))
public class Item {

public String seller_id;

public String item_name;

@PrimaryKey

@NonNull public String item_id;

public String item_discription;
public String cat;
public float price ;


    public Item() {
    }

    public Item(String item_name, String item_discription){
    this.item_name=item_name;
    this.item_discription=item_discription;
}

    public Item(String seller_id, String item_name, String item_id,String cat, String item_discription, float price) {
        this.seller_id = seller_id;
        this.item_name = item_name;
        this.item_id = item_id;
        this.item_discription = item_discription;
        this.price = price;
        this.cat=cat;
    }
}
