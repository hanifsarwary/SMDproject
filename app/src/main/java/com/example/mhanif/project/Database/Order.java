package com.example.mhanif.project.Database;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.ForeignKey;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import java.util.List;

/**
 * Created by M.hanif on 4/16/2018.
 */

@Entity(tableName = "Order",foreignKeys =@ForeignKey(entity = User.class,parentColumns = "Email",childColumns = "buyer_id") )
public class Order {
public float total_price;
@PrimaryKey
@NonNull public String order_id;

public String buyer_id;

public String item_id;

    public Order() {
    }

    public Order(float total_price, String order_id, String buyer_id, String item_id) {
        this.total_price = total_price;
        this.order_id = order_id;
        this.buyer_id = buyer_id;
        this.item_id = item_id;
    }
}
