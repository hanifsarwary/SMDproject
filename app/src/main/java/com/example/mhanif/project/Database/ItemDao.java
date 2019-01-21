package com.example.mhanif.project.Database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import java.util.List;

/**
 * Created by M.hanif on 4/17/2018.
 */
@Dao
public interface ItemDao {

    @Query("SELECT * FROM Item")
    List<Item> getall();

    @Insert
    void insertall(Item ...items);

    @Delete
    void deleteuser(Item item);

    @Query("SELECT * FROM Item where seller_id like:user_id")
    List<Item> getuser(int user_id);

    @Query("Select COUNT(*) FROM item")
    int countuser();
}
